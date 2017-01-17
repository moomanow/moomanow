package com.moomanow.core.common.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.moomanow.core.common.bean.Criteria;
import com.moomanow.core.common.bean.PagingBean;
import com.moomanow.core.common.exception.RollBackException;

@Transactional
public interface CommonDao {


	public static final String ENTITY_MODEL_ALIAS = "model";
	public void save(Object entity) throws RollBackException;
	public <T extends Object> T update(T entity) throws RollBackException;
	
	public int update(final String jpql) throws RollBackException;
	
	public int update(final String jpql, Object... params) throws RollBackException;
	
	public void delete(Object entity) throws RollBackException;
	
	public <T extends Object> T findById(Class<T> clazz, Object id) throws RollBackException;
	
	public <T extends Object> List<T> findByProperty( Class<T> clazz, final String propertyName, final Object value ) throws RollBackException;
	public <T extends Object> List<T> findByProperty( Class<T> clazz, String propertyName, final Object value, PagingBean pagingBean ) throws RollBackException;
	public <T extends Object> List<T> findByProperty(Class<T> class1, List<Criteria> criterias) throws RollBackException;
	public <T extends Object> List<T> findByProperty(Class<T> class1, List<Criteria> criterias,PagingBean value) throws RollBackException;
	
	public <T extends Object> List<T> findByExample(final T example) throws RollBackException;
	
	public <T extends Object> List<T> findByExample(final T example, final String extraWhereClause) throws RollBackException;
	
	public <T extends Object> List<T> findByExample(final T example, PagingBean pagingBean) throws RollBackException;
	
	public <T extends Object> List<T> findByExample(final T example, PagingBean pagingBean, final String extraWhereClause) throws RollBackException;
	
	public <T extends Object> List<T> findByExampleLike( final T example ) throws RollBackException;

	public <T extends Object> List<T> findByExampleLike( final T example, final String extraWhereClause ) throws RollBackException;

	public <T extends Object> List<T> findByExampleLike( final T example, PagingBean pagingBean ) throws RollBackException;

	public <T extends Object> List<T> findByExampleLike( final T example, PagingBean pagingBean, final String extraWhereClause ) throws RollBackException;
	
	public <T extends Object> List<T> findAll(Class<T> clazz) throws RollBackException;
	public <T extends Object> List<T> findAll(Class<T> clazz, PagingBean pagingBean) throws RollBackException;
	
	public <T extends Object> List<T> query(String jpql) throws RollBackException;
	
	public <T extends Object> List<T> query(String jpql, final Object... params) throws RollBackException;
	
	public <T extends Object> List<T> query(String jpql, String jpqlCount, PagingBean pagingBean, Object... params) throws RollBackException;

	public <T extends Object> List<T> query(String jpql, final List<Criteria> list) throws RollBackException;
	
	public <T extends Object> List<T> query(String jpql, String jpqlCount, PagingBean pagingBean, final List<Criteria> criteriaList) throws RollBackException;
	
	public <T extends Object> List<T> queryLike(String jpql, final List<Criteria> criteriaList ) throws RollBackException;
	
	public <T extends Object> List<T> queryLike(String jpql, String jpqlCount, final List<Criteria> criteriaList, PagingBean pagingBean ) throws RollBackException;

	public Object querySingleResult(String jpql) throws RollBackException;
	
	public Object querySingleResult(String jpql, Object... params) throws RollBackException;
	
	public Object querySingleResult(String jpql, final List<Criteria> list) throws RollBackException;
	
	public int executeBatch(String sql) throws RollBackException;
	
	public int executeNativeSQL(String sql) throws RollBackException;
	
	public int executeNativeSQL(String sql, Object... params) throws RollBackException;
	
	public <T extends Object> List<T> nativeQuery(String sql) throws RollBackException ;

	public <T extends Object> List<T> nativeQuery(String sql, Object... params) throws RollBackException ;
	
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz) throws RollBackException;
	
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz, Object... params) throws RollBackException;
	
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz, PagingBean pagingBean) throws RollBackException;

	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz, PagingBean pagingBean, Object... params) throws RollBackException;
	
	public Object nativeQuerySingleResult(String sql) throws RollBackException;
	
	public Object nativeQuerySingleResult(String sql, Object... params) throws RollBackException;
	
	public void flush() throws RollBackException;

	public void refresh(Object entity) throws RollBackException;

	
	
}
