package com.moomanow.core.common.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryHint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.RowMapper;

import com.moomanow.core.common.bean.Criteria;
import com.moomanow.core.common.bean.PagingBean;
import com.moomanow.core.common.bean.PagingBean.Order;
import com.moomanow.core.common.constant.CommonMessageCode;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.exception.RollBackTechnicalException;
import com.moomanow.core.common.util.JPAUtil;

public class CommonDaoImpl implements CommonDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CommonDaoImpl.class);

	protected EntityManager entityManager;

	@Required
	@PersistenceContext
//	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected Query genQueryByExample(Class<?> clazz,String queryString,List<Criteria> criteriaList,List<Order> orderList,boolean like){
		
		Query query = entityManager.createQuery(queryString,clazz);
		if(criteriaList !=null){
			for (Criteria criteria : criteriaList) {
				if(criteria.getValue() instanceof String)
					query.setParameter(criteria.getColumn(), "%"+((String)criteria.getValue()).toUpperCase()+"%");
				else
					query.setParameter(criteria.getColumn(), criteria.getValue());
			}
		}
		return query;
	}
	
	protected Long getTotalRowByExample(Class<?> clazz,List<Criteria> criteriaList,String extraWhereClause,boolean like) {
		StringBuilder countQueryString = new StringBuilder();
		countQueryString.append("select count(*) from ");
		countQueryString.append(clazz.getSimpleName());
		countQueryString.append(AILIAT);
		if(criteriaList !=null && criteriaList.size()>0){
			countQueryString.append(WHERE);
			for (Criteria criteria : criteriaList) {
				if(criteria.getValue() instanceof String && like){
					countQueryString.append(AND_UPPER);
					countQueryString.append(criteria.getColumn());
					countQueryString.append(LIKE);
					countQueryString.append(criteria.getParam());
				}else{
					countQueryString.append(AND);
					countQueryString.append(criteria.getColumn());
					countQueryString.append(EQU);
					countQueryString.append(criteria.getParam());
				}
			}
			
			if(extraWhereClause!=null&&extraWhereClause.length()>0){
				countQueryString.append(extraWhereClause);
			}
		}
		
		Query countQuery = entityManager.createQuery(countQueryString.toString());
		if(criteriaList !=null && criteriaList.size()>0){
			for (Criteria criteria : criteriaList) {
				countQuery.setParameter(criteria.getColumn(), criteria.getValue());
			}
		}
		return (Long)countQuery.getSingleResult();
	}
	
	private static final String LIKE = ") like :"; 
	private static final String AND = " and "; 
	private static final String AND_UPPER = " and UPPER("; 
	private static final String EQU = " = :"; 
	private static final String WHERE = " where 1=1 "; 
	private static final String FROM = "select * from "; 
	private static final String ORDER = " order by "; 
	private static final String AILIAT = " "; 
	
	protected String genQueryStringByExample(Class<?> clazz,List<Criteria> criteriaList, List<Order> orderList,String extraWhereClause,boolean like) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(FROM);
		queryString.append(clazz.getSimpleName());
		queryString.append(AILIAT);
		
		if(criteriaList !=null && criteriaList.size()>0){
			queryString.append(WHERE);
			for (Criteria criteria : criteriaList) {
				
				if(criteria.getValue() instanceof String && like){
					queryString.append(AND_UPPER);
					queryString.append(criteria.getColumn());
					queryString.append(LIKE);
					queryString.append(criteria.getParam());
				}else{
					queryString.append(AND);
					queryString.append(criteria.getColumn());
					queryString.append(EQU);
					queryString.append(criteria.getParam());
				}
			}
		}
		if(extraWhereClause!=null &&extraWhereClause.length()>0){
			if (criteriaList==null||criteriaList.size()>0) {
				queryString.append(extraWhereClause);
			}
		}
		if(orderList!=null&&orderList.size()>0){
			queryString.append(ORDER);
			for (int i = 0; i < orderList.size(); i++) {
				Order order = orderList.get(i);
				queryString.append(" "+order.getOrderBy()+" "+order.getOrderMode()+",");
				queryString.deleteCharAt(queryString.length()-1);
			}
		}
		
		return queryString.toString();
	}
	
	
	@Override
	public <T> T save(T entity) throws RollBackTechnicalException {
		try {
			 entityManager.persist(entity);
			if (logger.isDebugEnabled()) {
				logger.debug( entity.getClass().getSimpleName() + " persist successful"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			return entity;
		} catch (RuntimeException re) {
			logger.error("persist failed", re); //$NON-NLS-1$
			throw new RollBackTechnicalException(CommonMessageCode.COM4994, re);
		}

	}

	@Override
	public Object update(Object entity) throws RollBackTechnicalException {
		try {
			Object result = entityManager.merge(entity);
			if (logger.isDebugEnabled()) {
				logger.debug(entity.getClass().getSimpleName() + " persist successful"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			return result;
		} catch (DataIntegrityViolationException re) {
			logger.error("persist failed", re); //$NON-NLS-1$
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		} catch (RuntimeException re) {
			logger.error("merge failed", re); //$NON-NLS-1$
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		}
	}

	@Override
	public int update(String jpql) throws RollBackTechnicalException {
		try{
			return entityManager.createQuery(jpql).executeUpdate();
		}catch (DataIntegrityViolationException re){
			logger.error("persist failed", re); //$NON-NLS-1$
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		}catch (RuntimeException re) {
			logger.error("merge failed", re); //$NON-NLS-1$
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		}
	}

	@Override
	public int update(String jpql, Object... params) throws RollBackTechnicalException {
		try{
			Query query = entityManager.createQuery(jpql);
			for (int i = 0; i < params.length; i++) {
				Object object = params[i];
				query.setParameter(i+1, params[i]);
			}
			return query.executeUpdate();
		}catch (DataIntegrityViolationException re){
			logger.error("persist failed", re); //$NON-NLS-1$
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		}catch (RuntimeException re) {
			logger.error("merge failed", re); //$NON-NLS-1$
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		}
	}

	@Override
	public <T> T delete(T entity) throws RollBackTechnicalException {
		try{
			entity = entityManager.merge(entity);
			entityManager.remove(entity);
			if(logger.isDebugEnabled()){
				logger.debug(entity.getClass().getSimpleName()+ " remove successful");
			}
			return entity;
		}catch(RuntimeException re){
			logger.error("remove failed", re);
			throw new RollBackTechnicalException(CommonMessageCode.COM4992, re);
		}

	}

	@Override
	public <T> T findById(Class<T> clazz, Serializable id) throws RollBackTechnicalException {
		try{
			T instance = entityManager.find(clazz, id);
			return instance;
			
		}catch(RuntimeException re){
			throw new RollBackTechnicalException(CommonMessageCode.COM4991, re);
		}
		
	}

	@Override
	public <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value) throws RollBackTechnicalException {
		return findByProperty(clazz, propertyName, value,null);
	}

	@Override
	public <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value, PagingBean pagingBean) throws RollBackTechnicalException {
		if(pagingBean==null){
			try{
				List<Criteria> criteriaList = new LinkedList<Criteria>();
				criteriaList.add(new Criteria(propertyName, value));
				String queryString = genQueryStringByExample(clazz, criteriaList, null, null, false);
				Query query = genQueryByExample(clazz , queryString, criteriaList, null, false);
//				Query query = entityManager.createQuery(queryString,clazz);
//				query.setParameter("propertyValue", value);
				
				return query.getResultList();
			}catch(RuntimeException e){
				throw new RollBackTechnicalException(CommonMessageCode.COM4991, e);
			}
			
		}else{
			try{
				List<Criteria> criteriaList = new LinkedList<Criteria>();
				criteriaList.add(new Criteria(propertyName, value));
				
				pagingBean.setTotalRows(getTotalRowByExample(clazz, criteriaList, null, false));
				
				String qureyString = genQueryStringByExample(clazz, criteriaList, null, null, false);
				Query query = genQueryByExample(clazz,qureyString, criteriaList, pagingBean.getOrderList(), false);
				
				query.setFirstResult((int)pagingBean.getOffsetBegin()).setMaxResults(pagingBean.getRowsPerPage());
				
				return query.getResultList();
				
			}catch (RuntimeException re){
				throw new RollBackTechnicalException(CommonMessageCode.COM4991, re);
			}
		}
	}

	@Override
	public <T> List<T> findByExample(T example) throws RollBackTechnicalException {
		return findByExample(example, null, null);
	}

	@Override
	public <T> List<T> findByExample(T example, String extraWhereClause) throws RollBackTechnicalException {
		return findByExample(example, null, extraWhereClause);
	}

	@Override
	public <T> List<T> findByExample(T example, PagingBean pagingBean) throws RollBackTechnicalException {
		return findByExample(example,pagingBean,null);
	}

	@Override
	public <T> List<T> findByExample(T example, PagingBean pagingBean, String extraWhereClause) throws RollBackTechnicalException {
		return findByExampleLike(example, pagingBean, extraWhereClause, false);
	}

	@Override
	public <T> List<T> findByExampleLike(T example) throws RollBackTechnicalException {
		return findByExampleLike(example, null, null);
	}

	@Override
	public <T> List<T> findByExampleLike(T example, String extraWhereClause) throws RollBackTechnicalException {
		return findByExampleLike(example, null, extraWhereClause);
	}

	@Override
	public <T> List<T> findByExampleLike(T example, PagingBean pagingBean) throws RollBackTechnicalException {
		return findByExampleLike(example, pagingBean, null);
	}

	@Override
	public <T> List<T> findByExampleLike(T example, PagingBean pagingBean, String extraWhereClause) throws RollBackTechnicalException {
		return findByExampleLike(example, pagingBean, extraWhereClause, true);
	}
	
	public <T> List<T> findByExampleLike(T example, PagingBean pagingBean, String extraWhereClause,boolean like) throws RollBackTechnicalException {
		if(pagingBean==null){
			List<Criteria> criterias =  JPAUtil.beanToParamterList(example);
			String queryString  = genQueryStringByExample(example.getClass(), criterias, null, extraWhereClause, like);
			Query query = genQueryByExample(example.getClass(),queryString, criterias, null, like);
			return query.getResultList();
		}else{
			List<Criteria> criterias =  JPAUtil.beanToParamterList(example);
			
			pagingBean.setTotalRows(getTotalRowByExample(example.getClass(), criterias, extraWhereClause, like));
			
			String queryString  = genQueryStringByExample(example.getClass(), criterias, null, extraWhereClause, like);
			Query query = genQueryByExample(example.getClass(),queryString, criterias, null, like);
			
			query.setFirstResult((int)pagingBean.getOffsetBegin()).setMaxResults(pagingBean.getRowsPerPage());
			return query.getResultList();
		}
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz) throws RollBackTechnicalException {
		try{
			String queryString = genQueryStringByExample(clazz, null, null, null, false);
			Query query = genQueryByExample(clazz,queryString, null, null, false);
			return query.getResultList();
		}catch(Exception e){
			throw new RollBackTechnicalException(CommonMessageCode.COM4991, e);
		}
		
		
	}

	@Override
	public <T> List<T> findAll(Class<T> clazz, PagingBean pagingBean) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> List<T> query(String jpql, Class<T> clazz) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> List<T> query(String jpql, Class<T> clazz, Object... params) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> List<T> query(String jpql, Class<T> clazz, String jpqlCount, PagingBean pagingBean, Object... params) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> T querySingleResult(String jpql, Class<T> clazz) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> T querySingleResult(String jpql, Class<T> clazz, Object... params) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public int executeBatch(String sql) throws RollBackTechnicalException {
		return 0;
	}

	@Override
	public int executeNativeSQL(String sql) throws RollBackTechnicalException {
		Query query = entityManager.createNativeQuery(sql);
		return query.executeUpdate();
	}

	@Override
	public int executeNativeSQL(String sql, Object... params) throws RollBackTechnicalException {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < params.length; i++) {
			Object object = params[i];
			query.setParameter(i+1, object);
		}
		return query.executeUpdate();
	}

	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> clazz) throws RollBackTechnicalException {
		Query query = entityManager.createNativeQuery(sql, clazz);
		return query.getResultList();
	}

	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> clazz, Object... params) throws RollBackTechnicalException {
		Query query = entityManager.createNativeQuery(sql, clazz);
		for (int i = 0; i < params.length; i++) {
			Object object = params[i];
			query.setParameter(i+1, object);
		}
		return query.getResultList();
	}
	@Override
	public <T> List<T> nativeQuery(String sql, MapMapper<T> mapMapper, Object... params) throws RollBackTechnicalException {
		Query query = entityManager.createNativeQuery(sql);
//		query.setHint(hintName, value);
		for (int i = 0; i < params.length; i++) {
			Object object = params[i];
			query.setParameter(i+1, object);
		}
		List<T> list = new LinkedList<T>();
		List<Map<String, Object>> listMap = query.getResultList();
		for (int i = 0; i < listMap.size(); i++) {
			Map<String, Object> object = listMap.get(i);
			T t = mapMapper.mapRow(object , i+1);
			list.add(t);
		}
		return list;
	}
	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> clazz, PagingBean pagingBean) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> List<T> nativeQuery(String sql, Class<T> clazz, PagingBean pagingBean, Object... params) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> Object nativeQuerySingleResult(String sql, Class<T> clazz) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> Object nativeQuerySingleResult(String sql, Class<T> clazz, Object... params) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public void flush() throws RollBackTechnicalException {
		
	}

	@Override
	public void refresh(Object entity) throws RollBackTechnicalException {
		
	}

	@Override
	public <T> T delete(T entity, String langCode3) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList, PagingBean pagingBean) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> List<T> findByProperty(Class<T> clazz, List<Criteria> criteriaList) throws RollBackTechnicalException {
		return null;
	}

	@Override
	public <T> List<T> findByPropertyWithStatus(Class<T> clazz, String propertyName, Object value, String status)
			throws RollBackException, NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T saveOrUpdate(T target) throws RollBackException, NonRollBackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> saveMergeList(Class<T> clazz, List<T> newList, List<T> oldList) throws RollBackException,
			NonRollBackException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> saveMergeList(Class<T> clazz, List<T> newList, List<T> oldList, String SubListColumnName)
			throws RollBackException, NonRollBackException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
