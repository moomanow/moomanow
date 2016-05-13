package com.moomanow.core.common.dao;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.moomanow.core.common.bean.BeanLang;
import com.moomanow.core.common.bean.ClassMapper;
import com.moomanow.core.common.bean.Criteria;
import com.moomanow.core.common.bean.EntityBean;
import com.moomanow.core.common.bean.PagingBean;
import com.moomanow.core.common.constant.CommonMessageCode;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.exception.RollBackTechnicalException;
import com.moomanow.core.common.service.CheckService;
import com.moomanow.core.common.util.ClassUtil;
import com.moomanow.core.common.util.JPAUtil;

@Transactional
public class CommonJdbcDaoImpl extends JdbcCommonDaoImpl implements CommonDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CommonJdbcDaoImpl.class);

	@Autowired(required=false)
	private CheckService checkService;
	
	@Override
	public <T extends Object> T delete(T target)throws RollBackException ,NonRollBackException{
		return target;
	}

	@Override
	public <T extends Object> List<T> nativeQuery( String sql, Class<T> class1 ,Object... params)throws RollBackException ,NonRollBackException{
		return nativeQuery(sql, JPAUtil.getRm(class1), params);
	}

	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1) throws RollBackException ,NonRollBackException{
		return nativeQuery(sql, JPAUtil.getRm(class1));
	}
	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> class1, PagingBean pagingBean, Object... params) throws RollBackException ,NonRollBackException{
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1), params);
	}
	@Override
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> class1, PagingBean pagingBean) throws RollBackException ,NonRollBackException{
		return nativeQuery(sql, pagingBean, JPAUtil.getRm(class1));
	}

	@Override
	public <T> T delete(T entity, String langCode3) throws RollBackException ,NonRollBackException {
		return null;
	}
	
	@Override
	public int update(String jpql) throws RollBackException ,NonRollBackException {
		return 0;
	}
	@Override
	public int update(String jpql, Object... params) throws RollBackException ,NonRollBackException {
		return 0;
	}
	@Override
	public <T> T findById(Class<T> clazz, Serializable id) throws RollBackException ,NonRollBackException {
		return get(id, clazz);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value) throws RollBackException ,NonRollBackException {
		return findByColumn(clazz, propertyName, value);
	}
	@Override
	public <T> List<T> findByPropertyWithStatus(Class<T> clazz, String propertyName, Object value, String status) throws RollBackException ,NonRollBackException {
		List<Criteria> criteria = new LinkedList<Criteria>();
		criteria.add(new Criteria(propertyName, value));
		criteria.add(new Criteria("STATUS", status));
		return findByProperty(clazz, criteria,(PagingBean)null);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList) throws RollBackException ,NonRollBackException {
		return findByColumn(clazz, criteriaList, (PagingBean)null);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList, PagingBean pagingBean) throws RollBackException ,NonRollBackException {
		return findByColumn(clazz, criteriaList, pagingBean);
	}
	@Override
	public <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value, PagingBean pagingBean) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example, String extraWhereClause) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example, PagingBean pagingBean) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> findByExample(T example, PagingBean pagingBean, String extraWhereClause) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example, String extraWhereClause) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example, PagingBean pagingBean) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> findByExampleLike(T example, PagingBean pagingBean, String extraWhereClause) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> findAll(Class<T> clazz, PagingBean pagingBean) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> query(String jpql, Class<T> clazz) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> query(String jpql, Class<T> clazz, Object... params) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> List<T> query(String jpql, Class<T> clazz, String jpqlCount, PagingBean pagingBean, Object... params) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> T querySingleResult(String jpql, Class<T> clazz) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> T querySingleResult(String jpql, Class<T> clazz, Object... params) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public int executeBatch(String sql) throws RollBackException ,NonRollBackException {
		return 0;
	}
	@Override
	public <T> Object nativeQuerySingleResult(String sql, Class<T> clazz) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public <T> Object nativeQuerySingleResult(String sql, Class<T> clazz, Object... params) throws RollBackException ,NonRollBackException {
		return null;
	}
	@Override
	public void flush() throws RollBackException ,NonRollBackException {
		
	}
	@Override
	public void refresh(Object entity) throws RollBackException ,NonRollBackException {
		
	}
//	@Override
//	public <T extends Object > T saveOrUpdate(T target)throws RollBackException ,NonRollBackException {
//		return super.saveOrUpdate(target);
//	}
	@Override
	public <T extends Object > T saveOrUpdate(T target)throws RollBackException ,NonRollBackException {
		return super.saveOrUpdate(target);
	}

	@Override
	public <T> List<T> saveMergeList(Class<T> clazz, List<T> newList, List<T> oldList) throws RollBackException, NonRollBackException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		return saveMergeList(clazz, newList, oldList, null);
	}

	@Override
	public <T> List<T> saveMergeList(Class<T> clazz, List<T> newList, List<T> oldList, String subListColumnName) throws RollBackException, NonRollBackException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		List<T> resultList = new LinkedList<T>();
		ClassMapper classMapper = JPAUtil.getClassMapper(clazz);
		Method methodGet = classMapper.getPropertyId().getMethodGet();
		Method methodSetStatus = classMapper.getColumn().get("STATUS").get(0).getMethodSet();
		
		Method methodGetSubDetail = null;
		Method methodSetSubDetail = null;
		if(subListColumnName != null){
			methodGetSubDetail = classMapper.getColumn().get(subListColumnName).get(0).getMethodGet();
			methodSetSubDetail = classMapper.getColumn().get(subListColumnName).get(0).getMethodSet();
		}
		if(newList != null && newList.size() > 0 && oldList != null && oldList.size() > 0){
			for (T old : oldList) {
				if(old == null)
					continue;
				boolean have = false;
				Long idOld = (Long) methodGet.invoke(old);
				if(idOld != null && idOld > 0){
					for (T neww : newList) {
						if(neww == null)
							continue;
						Long idNew = (Long) methodGet.invoke(neww);
						if(idNew != null && idNew > 0){
							if(idNew.equals(idOld)){
								have = true;
							}
						}
					}
				}
				if (have == false) {
					methodSetStatus.invoke(old, "I");
					this.update(old);
				}
			}
			for (T neww : newList) {
				if(neww == null)
					continue;
				Long idNew = (Long) methodGet.invoke(neww);
				if(idNew != null && idNew > 0){
					for (T old : oldList) {
						Long idOld = (Long) methodGet.invoke(old);
						if(idOld != null && idOld > 0){
							if(idNew.equals(idOld)){
								if(subListColumnName != null){
									Object subDetail = methodGetSubDetail.invoke(neww);
									Object resultSubDetail = this.saveOrUpdate(subDetail);
									methodSetSubDetail.invoke(neww, resultSubDetail);
								}
								resultList.add(this.update(neww));
							}
						}
					}
				}else{
					if(subListColumnName != null){
						Object subDetail = methodGetSubDetail.invoke(neww);
						Object resultSubDetail = this.save(subDetail);
						methodSetSubDetail.invoke(neww, resultSubDetail);
					}
					resultList.add(this.save(neww));
				}

			}
		}else if(newList != null && newList.size() > 0){
			for (T neww : newList) {
				if(neww != null){
					if(subListColumnName != null){
						Object subDetail = methodGetSubDetail.invoke(neww);
						Object resultSubDetail = this.save(subDetail);
						methodSetSubDetail.invoke(neww, resultSubDetail);
					}
					resultList.add(this.save(neww));
				}
			}
		}else if(oldList != null && oldList.size() > 0){
			for (T old : oldList) {
				if(old != null){
					methodSetStatus.invoke(old, "I");
					resultList.add(this.update(old));
				}
			}
		}
		return resultList;
	}
	
	private boolean checkLangBean(Class<? extends Object> class1) throws RollBackException, NonRollBackException {
		ClassMapper classMapper = JPAUtil.getClassMapper(class1);
		// check have table Lang
		return checkService.checkTableLang(classMapper.getTableName());
	}

	private Long checkLangBean(Class<? extends Object> class1,String columnName, Object idEng, String status, String langCode) throws RollBackException, NonRollBackException {
		ClassMapper classMapper = JPAUtil.getClassMapper(class1);
		
		// check have table Lang
		boolean isHaveLang = checkService.checkTableLang(classMapper.getTableName());
		if(isHaveLang){
			StringBuilder sb = new StringBuilder();
			sb.append("select ");
			sb.append(columnName);
			sb.append("_LANG from ");
			sb.append(classMapper.getTableName());
			sb.append("_LANG ");
			sb.append(" WHERE ");
			sb.append(columnName);
			sb.append(" = :ID_ENG");
			sb.append(" AND LANG_CODE3 = :LANG_CODE3");
			Map<String,  Object>params = new HashMap<String, Object>();
			if(!"".equals(status) && status != null){
//				sb.append(" AND STATUS = :STATUS");
//				params.put("STATUS", status);
				sb.append(" AND STATUS IN ('A','W') ");
			}
			params.put("ID_ENG", idEng);
			params.put("LANG_CODE3", langCode);
			List<Long> conut = nativeQuery(sb.toString(), LONG_MAPPER, params);
			if(conut==null ||conut.size()==0){
				return null;
			}else{
				return conut.get(0);
			}
		}else{
			return null;
		}
	}
	
	private Long checkLangBeanId(Class<? extends Object> class1,String columnName, Object idEng, Object idLang) throws RollBackException, NonRollBackException {
		ClassMapper classMapper = JPAUtil.getClassMapper(class1);
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append(columnName);
		sb.append("_LANG from ");
		sb.append(classMapper.getTableName());
		sb.append("_LANG ");
		sb.append(" WHERE ");
		sb.append(columnName);
		sb.append(" = :ID_ENG");
		sb.append(" AND ");
		sb.append(columnName);
		sb.append("_LANG ");
		sb.append(" = :ID_LANG");
		Map<String,  Object>params = new HashMap<String, Object>();
		params.put("ID_ENG", idEng);
		params.put("ID_LANG", idLang);
		List<Long> conut = nativeQuery(sb.toString(), LONG_MAPPER, params);
		if(conut==null ||conut.size()==0){
			return null;
		}else{
			return conut.get(0);
		}
		
	}

	@Override
	public <T> List<T> nativeQuery(String sql, MapMapper<T> rowMapper, Object[] params)
			throws RollBackTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}


}
