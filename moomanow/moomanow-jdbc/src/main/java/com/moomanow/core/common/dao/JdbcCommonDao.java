package com.moomanow.core.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.moomanow.core.common.bean.BeanLang;
import com.moomanow.core.common.bean.Criteria;
import com.moomanow.core.common.bean.EntityBean;
import com.moomanow.core.common.bean.PagingBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

@Transactional
public interface JdbcCommonDao {
	
//  basic

	public <T> T getByStatusAndPkValue(Class<T> clazz, String status, Serializable pkValue);
	
	public <T> List<T> getListByStatusAndPkValue(Class<T> clazz, String status, Serializable pkValue);

	public <T> List<T> findAll(Class<T> class1);

	public <T> T get(Serializable target, String lang, Class<T> clazz);

	public <T> T get(Serializable target,  Class<T> clazz);

	public Integer getCount(String sql, Object... params);
//  basic	
//	modify
	public <T> T updateOnlyNotNullBasic(T target);
	
	public <T> T save(T target);
	
	public <T> T save(T target, String langCode3);

	public <T> T saveOrUpdate(T t);
	
	public <T> T saveOrUpdate(T target, boolean tableLang, String code, Long idLang);
	
	public <T> T update(T target);

	public <T> Collection<T> saveOrUpdateAll(Collection<T> entities);

	public <T> T updateStatusDelete(T target);

	public <T> T delete(T target);

	public <T> List<T> findAllEntityOnechild(List<T> list);

	public <T> T save(T target, boolean tableLang, String langCode);

	public <T> T update(T target, boolean tableLang, String langCode, Long idLang);

	public <T> List<T> saveMergeList(Class<T> clazz, List<T> newList, List<T> oldList);

	public <T> List<T> saveMergeList(Class<T> clazz, List<T> newList, List<T> oldList, String subListColumnName);

	public <T> BeanLang<T> saveOrUpdate(BeanLang<T> beanLang);
//	modify
//	Native

	public <T> T nativeQueryOneRowForObject(String sql, Class<T> requiredType, Object... args);

	public <T> T nativeQueryOneRowForObject(String sql, Class<T> requiredType,Map<String, Object> args);

	public KeyHolder executeNativeSQLGetIdKeyHolder(String sql,Map<String, Object> params);

	public KeyHolder executeNativeSQLGetIdKeyHolder(String sql, EntityBean params);

	public KeyHolder executeNativeSQLGetIdKeyHolder(String sql, Object... params);

	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm, Map<String, Object> params);

	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm, Object... params);
	
	public <T extends Object> T nativeQueryOneRow(String sql, RowMapper<T> rm);

	public <T extends Object> List<T> nativeQuery(String sql, RowMapper<T> rm, Map<String, Object> params);

	public <T extends Object> List<T> nativeQuery(String sql, RowMapper<T> rm, Object... params);
	
	public <T extends Object> List<T> nativeQuery(String sql, RowMapper<T> rm);
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Object... params);
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm, Map<String, Object> params);
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, RowMapper<T> rm);

	public int executeNativeSQL(String sql, Map<String, Object> params);

	public int executeNativeSQL(String sql, Object... params);

	public int executeNativeSQL(String sql);

	public Number executeNativeSQLGetId(String sql, Object... params);

	public Number executeNativeSQLGetId(String sql, Map<String, Object> params);

	public Number executeNativeSQLGetId(String sql, EntityBean object);

	public <T extends Object> T nativeQueryOneRow(String sql, Class<T> class1, Map<String, Object> params);

	public <T extends Object> T nativeQueryOneRow(String sql, Class<T> class1, Object... params);
	
	public <T extends Object> T nativeQueryOneRow(String sql, Class<T> class1);

	public <T extends Object> List<T> nativeQuery(String sql, Class<T> class1, Map<String, Object> params);

	public <T extends Object> List<T> nativeQuery(String sql, Class<T> class1, Object... params);
	
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> class1);
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1, Object... params);
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1, Map<String, Object> params);
	
	public  <T extends Object> List<T> nativeQuery(String sql, PagingBean pagingBean, Class<T> class1);
//	Native


//	findByColumn

	public <T> T findByColumnOneRow(Class<T> clazz, List<Criteria> criteriaList);

	public <T> T findByColumnOneRow(Class<T> clazz, List<Criteria> criteriaList,boolean like);
	
	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criterias);

	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criterias,PagingBean pagingBean, boolean like);
	
	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criterias, PagingBean pagingBean, boolean like,String langCode3);

	public <T> List<T> findByColumn(Class<T> clazz, String propertyName, Object value);

	public <T> List<T> findByColumn(Class<T> clazz, String propertyName, Object value,PagingBean pagingBean);
	
	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criteriaList,PagingBean pagingBean);
	
	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criteriaList, String langCode3);
	
	public <T> List<T> findByColumn(Class<T> clazz, List<Criteria> criteriaList,PagingBean pagingBean, String langCode3);
	
	public <T> List<T> findByColumnMap(Class<T> clazz, Map<String, Object> columnMap);
	
	public <T> List<T> findByColumnMap(Class<T> clazz, Map<String, Object> columnMap, PagingBean pagingBean);
	
	public <T> T findByColumnOneRow(Class<T> clazz, List<Criteria> criteriaList,String langCode3);

	public <T> List<T> findByPropertyWithStatusAndLang(Class<T> clazz, String propertyName, Object value, String status,String LangCode3);

	public <T> List<T> findByPropertyWithStatus(Class<T> clazz, String propertyName, Object value, String status);

//	findByColumn
	
	public <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value);
	
	public <T> List<T> findByProperty( Class<T> clazz, String propertyName, final Object value, PagingBean pagingBean );
	
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList);
	
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList, String langCode);
	
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList, PagingBean pagingBean);



}
