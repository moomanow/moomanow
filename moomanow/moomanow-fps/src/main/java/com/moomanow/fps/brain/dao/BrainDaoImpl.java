package com.moomanow.fps.brain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.moomanow.core.common.dao.JdbcCommonDaoImpl;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.fps.brain.bean.NeronBean;

public class BrainDaoImpl extends JdbcCommonDaoImpl implements BrainDao {

	private static String sql = new StringBuilder()
			.append(" select n.CODE ,n.CLASS from FPS_M_BRAIN_LINE_NEURON map ")
			.append(" join FPS_M_BRAIN b on b.ID_BRAIN = map.ID_BRAIN and b.CODE = ? and b.STATUS = 'A' ")
			.append(" join FPS_M_NEURON n on n.ID_NEURON = map.ID_NEURON and n.STATUS = 'A'")
			.append(" where map.STATUS ='A'")
			.toString();
	@Override
	public List<NeronBean> getLineNuronByBrainCode(String brainCode) throws RollBackException, NonRollBackException {
		List<NeronBean> neronBeans = nativeQuery(sql, ROW_MAPPER, brainCode);
		return neronBeans;
	}
	
	private static final RowMapper<NeronBean> ROW_MAPPER = new RowMapper<NeronBean>() {

	    public NeronBean mapRow(ResultSet rs, int num)throws SQLException {
	    	NeronBean neronBean = new NeronBean();
	    	neronBean.setCode(rs.getString("CODE"));
	    	neronBean.setClassType(rs.getString("CLASS"));
	        return neronBean;
	    }
    };

}
