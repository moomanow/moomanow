package com.moomanow.web.common.label.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.jdbc.core.RowMapper;

import com.moomanow.core.common.dao.JdbcCommonDaoImpl;
import com.moomanow.web.common.label.bean.Label;
import com.moomanow.web.common.label.bean.LabelDefault;

public class LabelDaoImpl extends JdbcCommonDaoImpl  implements LabelDao {

	@Override
	public Map<String, Map<String, String>> getLabelStrMap(){
		
		Map<String, Map<String, String>> messageStringMap = new ConcurrentHashMap<String, Map<String, String>>();

		Map<String, List<Label>> messageMap   = getLabelMap();
		Map<String, String> labelMapDefault = new ConcurrentHashMap<String, String>();
		for (Label label : messageMap.get(Label.DEFAULT_LANG)) {
			labelMapDefault.put(label.getPage()+"_"+label.getLabel(), label.getDisplayText());
		}
		Set<String> langSet = messageMap.keySet();
		for (String lang : langSet) {
			if(lang != Label.DEFAULT_LANG){
				Map<String, String> labelMap = new ConcurrentHashMap<String, String>(labelMapDefault);
				for (Label label : messageMap.get(lang)) {
					labelMap.put(label.getPage()+"_"+label.getLabel(), label.getDisplayText());
				}
				messageStringMap.put(lang, labelMap );
			}
			
		}
		return messageStringMap;
	}
	public static final String SQL_QUERY_LABEL = 
			" SELECT LABEL, PAGE, DISPLAY_TEXT, LANGUAGE " +
			" FROM SYS_M_LABEL  WHERE STATUS = 'A' ";
	private static final LabelMapper<Label> LABEL_MAPPER = new LabelMapper<Label>();
	public static final class LabelMapper<T extends Label> implements RowMapper<Label> {

	    public Label mapRow(ResultSet rs, int num)throws SQLException {
	    	LabelDefault label = new LabelDefault(); 
	    	label.setLabel(rs.getString("LABEL"));
	    	label.setLanguage(rs.getString("LANGUAGE"));
	    	label.setPage(rs.getString("PAGE"));
	    	label.setDisplayText(rs.getString("DISPLAY_TEXT"));
	        return label;
	    }
    }
	@Override
	public Map<String, List<Label>> getLabelMap() {
		Map<String, List<Label>> messageMap = new ConcurrentHashMap<String, List<Label>>();
		List<Label> labelList = nativeQuery(SQL_QUERY_LABEL, LABEL_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		for (Label label : labelList) {
			List<Label> labels = messageMap.get(label.getLanguage());
			if(labels==null){
				labels = new ArrayList<Label>();
				
			}
			labels.add(label);
			messageMap.put(label.getLanguage(), labels);
		}
		return messageMap;
	}

}
