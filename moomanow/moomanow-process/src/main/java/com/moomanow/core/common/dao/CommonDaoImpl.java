package com.moomanow.core.common.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.moomanow.core.common.bean.Criteria;
import com.moomanow.core.common.bean.PagingBean;
import com.moomanow.core.common.bean.PagingBean.Order;
import com.moomanow.core.common.constant.CommonMessageCode;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.exception.RollBackTechnicalException;

@Transactional
public class CommonDaoImpl implements CommonDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CommonDaoImpl.class);
	
	protected EntityManager entityManager;

	@Required
    @PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
    protected String genQueryStringByExample( Class<?> clazz, List<Criteria> criteriaList, List<Order> orderList ) throws Exception{
    	return genQueryStringByExample( clazz, criteriaList, orderList, null );
    }
    
    protected String genQueryStringByExample( Class<?> clazz, List<Criteria> criteriaList, List<Order> orderList, String extraWhereClause ) throws Exception{
				
		StringBuilder queryString = new StringBuilder();
		queryString.append( "select model from " + clazz.getSimpleName() + " model " );
		if( criteriaList != null && criteriaList.size() > 0 ){
			queryString.append(" where 1 = 1 ");
			for( Criteria criteria : criteriaList ){
				queryString.append(" AND model." + criteria.getColumn() + " = :" + criteria.getParam() );
			}				
		}
		
		if( extraWhereClause != null && extraWhereClause.length() > 0 ){
			if( criteriaList == null || criteriaList.size() <= 0 ){
				queryString.append(" where 1 = 1 ");
			}			
			queryString.append(extraWhereClause);
		}
		
		if( orderList != null && orderList.size() > 0 ){
			queryString.append(" order by ");
			for( Order order : orderList ){
				queryString.append( CommonDao.ENTITY_MODEL_ALIAS + "." + order.getOrderBy() + " " + order.getOrderMode() + ",");
			}
			queryString.deleteCharAt(queryString.length()-1);
		}
		
		return queryString.toString();
	}

    protected String genQueryStringByExampleLike( Class<?> clazz, List<Criteria> criteriaList, List<Order> orderList ) throws Exception{
    	return genQueryStringByExampleLike( clazz, criteriaList, orderList, null );
    }	

    protected String genQueryStringByExampleLike( Class<?> clazz, List<Criteria> criteriaList, List<Order> orderList, String extraWhereClause ) throws Exception{
		
		StringBuilder queryString = new StringBuilder();
		queryString.append( "select model from " + clazz.getSimpleName() + " model " );
		if( criteriaList != null && criteriaList.size() > 0 ){
			queryString.append(" where 1 = 1 ");
			for( Criteria criteria : criteriaList ){
				if( criteria.getValue().getClass().equals(java.lang.String.class) ){
					queryString.append(" AND UPPER(model." + criteria.getColumn() + ") LIKE :" + criteria.getParam() + " ");
				}
				else{
					queryString.append(" AND model." + criteria.getColumn() + " = :" + criteria.getParam() + " ");
				}					
			}				
		}
		
		if( extraWhereClause != null && extraWhereClause.length() > 0 ){
			if( criteriaList == null || criteriaList.size() <= 0 ){
				queryString.append(" where 1 = 1 ");
			}
			queryString.append(extraWhereClause);
		}		
		
		if( orderList != null && orderList.size() > 0 ){
			queryString.append(" order by ");
			for( Order order : orderList ){
				queryString.append( CommonDao.ENTITY_MODEL_ALIAS + "." + order.getOrderBy() + " " + order.getOrderMode() + ",");
			}
			queryString.deleteCharAt(queryString.length()-1);
		}
		
		return queryString.toString();
	}	
	
    protected Query genQueryByExample( String queryString, List<Criteria> criteriaList, List<Order> orderList ) throws Exception{
		
		Query query = entityManager.createQuery(queryString);
		if( criteriaList != null && criteriaList.size() > 0 ){
			for( Criteria param : criteriaList ){
				param.getColumn();
				if (logger.isDebugEnabled()) {
					logger.debug("genQueryByExample() - param="
									+ param.getColumn()+", "+ param.getValue());
				}
				query.setParameter( param.getColumn(), param.getValue());
			}				
		}
		return query;
	}
	
    protected Query genQueryByExampleLike( String queryString, List<Criteria> criteriaList, List<Order> orderList ) throws Exception{
		
		Query query = entityManager.createQuery(queryString.toString());
		if( criteriaList != null && criteriaList.size() > 0 ){
			for( Criteria criteria : criteriaList ){
				if( criteria.getValue().getClass().equals(java.lang.String.class) ){
					query.setParameter( criteria.getColumn(), "%"+((String)criteria.getValue()).toUpperCase()+"%");
				}
				else{
					query.setParameter( criteria.getColumn(), criteria.getValue());
				}
			}				
		}
		return query;
	}	
	
    protected Long getTotalRowsByExample( Class<?> clazz, List<Criteria> criteriaList ){
		StringBuilder countQueryString = new StringBuilder();
		countQueryString.append( "select count(*) from " + clazz.getSimpleName() + " model " );
		if( criteriaList != null && criteriaList.size() > 0 ){
			countQueryString.append(" where 1 = 1 ");			
			for( Criteria param : criteriaList ){
				countQueryString.append(" AND model." + param.getColumn() + " = :" + param.getParam() );
			}				
		}
					
		Query countQuery = entityManager.createQuery(countQueryString.toString());
		if( criteriaList != null && criteriaList.size() > 0 ){
			for( Criteria param : criteriaList ){
				countQuery.setParameter( param.getColumn(), param.getValue());
			}				
		}		
		
		return (Long)countQuery.getSingleResult();
	}	
	
    protected Long getTotalRowsByExample( Class<?> clazz, List<Criteria> criteriaList, String extraWhereClause ){
		StringBuilder countQueryString = new StringBuilder();
		countQueryString.append( "select count(*) from " + clazz.getSimpleName() + " model " );
		if( criteriaList != null && criteriaList.size() > 0 ){
			countQueryString.append(" where 1 = 1 ");			
			for( Criteria param : criteriaList ){
				countQueryString.append(" AND model." + param.getColumn() + " = :" + param.getParam() + " " );
			}				
		}
		
		if( extraWhereClause != null && extraWhereClause.length() > 0 ){
			if( criteriaList == null || criteriaList.size() <= 0 ){
				countQueryString.append(" where 1 = 1 ");
			}
			countQueryString.append(extraWhereClause);
		}	
					
		Query countQuery = entityManager.createQuery(countQueryString.toString());
		if( criteriaList != null && criteriaList.size() > 0 ){
			for( Criteria param : criteriaList ){
				countQuery.setParameter( param.getColumn(), param.getValue());
			}				
		}		
		
		return (Long)countQuery.getSingleResult();
	}	
	
    protected Long getTotalRowsByExampleLike( Class<?> clazz, List<Criteria> criteriaList, String extraWhereClause ){
		StringBuilder countQueryString = new StringBuilder();
		countQueryString.append( "select count(*) from " + clazz.getSimpleName() + " model " );
		if( criteriaList != null && criteriaList.size() > 0 ){
			countQueryString.append(" where 1 = 1 ");			
			for( Criteria param : criteriaList ){
				if( param.getValue().getClass().equals(java.lang.String.class) ){
					countQueryString.append(" AND UPPER(model." + param.getColumn() + ") LIKE :" + param.getParam() + " ");
				}
				else{
					countQueryString.append(" AND model." + param.getColumn() + " = :" + param.getParam() + " ");
				}
			}				
		}
		
		if( extraWhereClause != null && extraWhereClause.length() > 0 ){
			if( criteriaList == null || criteriaList.size() <= 0 ){
				countQueryString.append(" where 1 = 1 ");
			}
			countQueryString.append(extraWhereClause);
		}	
					
		Query countQuery = entityManager.createQuery(countQueryString.toString());
		if( criteriaList != null && criteriaList.size() > 0 ){
			for( Criteria param : criteriaList ){
				if( param.getValue().getClass().equals(java.lang.String.class) ){
					countQuery.setParameter( param.getColumn(), "%"+((String)param.getValue()).toUpperCase()+"%");
				}
				else{
					countQuery.setParameter( param.getColumn(), param.getValue());
				}
			}				
		}		
		
		return (Long)countQuery.getSingleResult();
	}
    
    public void save(Object entity)  {
		try {
			entityManager.persist( entity );
			if(logger.isDebugEnabled()) {
				logger.debug(entity.getClass().getSimpleName()+" persist successful");
			}
		} catch (RuntimeException re) {
			if(logger.isErrorEnabled()) {
				logger.error("persist failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4994, re);
		}
	}
	

    public <T extends Object> T update(T entity) throws RollBackException {
		try {
			T result = entityManager.merge(entity);
			if(logger.isDebugEnabled()) {
				logger.debug(entity.getClass().getSimpleName()+" merge successful");
			}
			return result;
		} catch (DataIntegrityViolationException re) { 
			if(logger.isErrorEnabled()) {
				logger.error("persist failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		} catch (RuntimeException re) {
			if(logger.isErrorEnabled()) {
				logger.error("merge failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		}
	}
    

    public int update(final String jpql)throws RollBackException {
		try {
			return entityManager.createQuery(jpql).executeUpdate();
			
		} catch (DataIntegrityViolationException re) { 
			if(logger.isErrorEnabled()) {
				logger.error("persist failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		} catch (RuntimeException re) {
			if(logger.isErrorEnabled()) {
				logger.error("merge failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		}
	}
    

    public int update(final String jpql, Object... params) throws RollBackException {
		try {
			Query q = entityManager.createQuery(jpql);
			for(int i=0; i<params.length;i++) {
				q.setParameter(i+1, params[i]);
			}
			return q.executeUpdate();
		} 
		catch( DataIntegrityViolationException re ) { 
			if(logger.isErrorEnabled()) {
				logger.error("persist failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		} 
		catch( RuntimeException re ) {
			if(logger.isErrorEnabled()) {
				logger.error("merge failed", re);
			}
			throw new RollBackTechnicalException(CommonMessageCode.COM4993, re);
		}
	}

	public void delete(Object entity) throws RollBackException {
		try{
			entity = entityManager.merge(entity);
			entityManager.remove( entity );
			if(logger.isDebugEnabled()) {
				logger.debug(entity.getClass().getSimpleName()+" remove successful");
			}
		} 
		catch (RuntimeException re) {
			if(logger.isErrorEnabled()) {
				logger.error(CommonMessageCode.COM4992, re);
			}
			throw re;
		}
	}

	public <T extends Object> T findById(Class<T> clazz, Object id) throws RollBackException {
		try{
			T instance = entityManager.find(clazz,id);
			return instance;
		} 
		catch (RuntimeException re) {
			re.printStackTrace();
			throw new RollBackTechnicalException(CommonMessageCode.COM4991, re);
		}
	}

	public <T extends Object > List<T> findByProperty( Class<T> clazz, String propertyName, final Object value ) throws RollBackException {
		return findByProperty( clazz, propertyName, value, null ); 
	}	

	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> findByProperty( Class<T> clazz, String propertyName, final Object value, PagingBean pagingBean ) throws RollBackException{

		if( pagingBean == null ){
			try {
				final String queryString = "select model from " + clazz.getSimpleName() + " model where model."
						+ propertyName + "= :propertyValue";
				Query query = entityManager.createQuery(queryString);
				query.setParameter("propertyValue", value);
				return query.getResultList();
			} 
			catch (RuntimeException e) {
				throw new RollBackTechnicalException(CommonMessageCode.COM4991, e);
			}
		} 
		else{
			Query query;			
			
			try{			
				
				List<Criteria> criteriaList = new LinkedList<Criteria>();
				criteriaList.add(new Criteria(propertyName,value));
				
				pagingBean.setTotalRows( getTotalRowsByExample( clazz, criteriaList ) );
				
				String queryString = genQueryStringByExample( clazz, criteriaList, pagingBean.getOrderList() );	
				query = genQueryByExample( queryString, criteriaList, pagingBean.getOrderList() );			
				
				query.setFirstResult( (int)pagingBean.getOffsetBegin() )
					 .setMaxResults( pagingBean.getRowsPerPage() );
			}
			catch( Exception e ){
				throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
			}			
			
			return query.getResultList();
		}
	}
	
	public <T extends Object> List<T> findByExample( final T example ) throws RollBackException{	
		return findByExample( example, null, null );
	}	

	public <T extends Object> List<T> findByExample( final T example, final String extraWhereClause ) throws RollBackException{	
		return findByExample( example, null, extraWhereClause );
	}		

	public <T extends Object> List<T> findByExample( final T example, PagingBean pagingBean ) throws RollBackException{	
		return findByExample( example, pagingBean, null );
	}		

    @SuppressWarnings("unchecked")
	public <T extends Object> List<T> findByExample( final T example, PagingBean pagingBean, final String extraWhereClause ) throws RollBackException {

		try {			
		
			if( pagingBean == null ){
				
				List<Criteria> parameterList = QueryHelper.beanToParameterList( example );
				
				String queryString = genQueryStringByExample( example.getClass(), parameterList, null, extraWhereClause );						
				Query query = genQueryByExample(queryString, parameterList, null);
					
				return query.getResultList();
			} else {
				
				List<Criteria> parameterList = QueryHelper.beanToParameterList( example );
							
				pagingBean.setTotalRows( getTotalRowsByExample(example.getClass(), parameterList) );
				
				String queryString = genQueryStringByExample( example.getClass(), parameterList, pagingBean.getOrderList(), extraWhereClause );						
				Query query = genQueryByExample(queryString, parameterList, pagingBean.getOrderList() );			
				
				query.setFirstResult( (int)pagingBean.getOffsetBegin() )
					 .setMaxResults( pagingBean.getRowsPerPage() );
				
				return query.getResultList();
			}			
		} catch( Exception e ) {
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		} 		
	}		

	public <T extends Object > List<T> findByExampleLike( final T example ) throws RollBackException{
		return findByExampleLike( example, null, null );
	}	

	public <T extends Object> List<T> findByExampleLike( final T example, final String extraWhereClause ) throws RollBackException{
		return findByExampleLike( example, null, extraWhereClause );
	}	

	public <T extends Object> List<T> findByExampleLike( final T example, PagingBean pagingBean ) throws RollBackException{
		return findByExampleLike( example, pagingBean, null );
	}	

    @SuppressWarnings("unchecked")
	public <T extends Object> List<T> findByExampleLike( final T example, PagingBean pagingBean, final String extraWhereClause ) throws RollBackException {

		try {			
		
			if( pagingBean == null ) {
				List<Criteria> parameterList = QueryHelper.beanToParameterList( example ); 
					
				String queryString = genQueryStringByExampleLike( example.getClass(), parameterList, null, extraWhereClause );						
				Query query = genQueryByExampleLike(queryString, parameterList, null);
				return query.getResultList();
			} else {
				List<Criteria> parameterList = QueryHelper.beanToParameterList( example );
							
				pagingBean.setTotalRows( getTotalRowsByExampleLike(example.getClass(), parameterList, extraWhereClause) );
				
				String queryString = genQueryStringByExampleLike( example.getClass(), parameterList, pagingBean.getOrderList(), extraWhereClause );						
				Query query = genQueryByExampleLike(queryString, parameterList, pagingBean.getOrderList() );			
				
				query.setFirstResult( (int)pagingBean.getOffsetBegin() )
					 .setMaxResults( pagingBean.getRowsPerPage() );
				
				return query.getResultList();
			}			
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		} 		
	}	

    @SuppressWarnings("unchecked")
	public <T extends Object>List<T> findAll( Class<T> clazz ) throws RollBackException{
		
		try{
			String queryString = genQueryStringByExample( clazz, null, null );						
			Query query = genQueryByExample( queryString, null, null );	
			return query.getResultList();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		}
	}

    @SuppressWarnings("unchecked")
	public <T extends Object> List<T> findAll( Class<T> clazz, PagingBean pagingBean ) throws RollBackException{

		if( pagingBean == null ){
			return findAll( clazz );
		} else {
			Query query;			
			
			try{			
				pagingBean.setTotalRows( getTotalRowsByExample( clazz, null ) );
				
				String queryString = genQueryStringByExample( clazz, null, pagingBean.getOrderList() );	
				query = genQueryByExample( queryString, null, null );			
				
				query.setFirstResult( (int)pagingBean.getOffsetBegin() )
					 .setMaxResults( pagingBean.getRowsPerPage() );
			}
			catch( Exception e ){
				throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
			}			
			
			return query.getResultList();
		}
	}

    @SuppressWarnings("unchecked")
	public List<? extends Object> query(String jpql)throws RollBackException {
	    try{	
			return entityManager.createQuery(jpql).getResultList();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		}	
	}

    @SuppressWarnings("unchecked")
	public List<? extends Object> query(String jpql, final Object... params) throws RollBackException {
		try{
	    	Query q = entityManager.createQuery(jpql);
			for(int i=0; i<params.length;i++) {
				if (logger.isDebugEnabled()) {
					logger.debug(i+":"+params[i]);
				}
				q.setParameter(i+1, params[i]);
			}
			return q.getResultList();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		}	
	}

    @SuppressWarnings("unchecked")
	public List<? extends Object> query(String jpql, String jpqlCount, PagingBean pagingBean, Object... params) throws RollBackException {
		try{				
			
			if( pagingBean == null ){
				return query(jpql, params);
			} else {
				Query countQuery = entityManager.createQuery(jpqlCount);
				for(int i=0; i<params.length;i++) {
					countQuery.setParameter(i+1, params[i]);
				}				
				pagingBean.setTotalRows( (Long)countQuery.getSingleResult() );		
				
				Query query = entityManager.createQuery(jpql);						
				for(int i=0; i<params.length;i++) {
					if (logger.isDebugEnabled()) {
						logger.debug(i+":"+params[i]);
					}
					query.setParameter(i+1, params[i]);
				}									
				query.setFirstResult( (int)pagingBean.getOffsetBegin() )
					 .setMaxResults( pagingBean.getRowsPerPage() );

				return query.getResultList();					
			}
		}
		catch( Exception e ){			
			throw new RollBackTechnicalException(CommonMessageCode.COM4991, e);
		}
	}
    

    @SuppressWarnings("unchecked")
	public <T extends Object> List<T> query(String jpql, List<Criteria> list) throws RollBackException {
		
		try{				
					
			String orderBy = "";		
			int k = jpql.toUpperCase().indexOf("ORDER BY");
			if(  k > -1) {
				orderBy = " " + jpql.substring(k, jpql.length());
				jpql = jpql.substring(0, k);
				
			}

			if( jpql.toUpperCase().indexOf("WHERE") == -1 ){
				jpql += " WHERE 1 = 1 ";
			}
			
			String criteriaString = QueryHelper.genCriteriaString(list); 
			
			Query query = entityManager.createQuery(jpql + criteriaString.toString() + orderBy);						
			query = QueryHelper.setParameter(query, list, null);												

			return query.getResultList();			
		}
		catch( Exception e ){			
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		}
	}	

    @SuppressWarnings("unchecked")
	public <T extends Object> List<T> query( String jpql, String jpqlCount, PagingBean pagingBean, List<Criteria> criteriaList ) throws RollBackException {
		
		try{				
					
			if( pagingBean == null ){
				return query( jpql, criteriaList );
			} else {

				if( jpql.indexOf("WHERE") == -1 ){
					jpql += " WHERE 1 = 1 ";
				}
				if( jpqlCount.indexOf("WHERE") == -1 ){
					jpqlCount += " WHERE 1 = 1 ";
				}
				
				String criteriaString = QueryHelper.genCriteriaString(criteriaList); 
				String orderString = QueryHelper.genOrderString(pagingBean.getOrderList());

				Query countQuery = entityManager.createQuery(jpqlCount + criteriaString.toString());
				countQuery = QueryHelper.setParameter(countQuery, criteriaList );				
				pagingBean.setTotalRows( (Long)countQuery.getSingleResult() );		
				
				Query query = entityManager.createQuery(jpql + criteriaString.toString() + orderString);						
				query = QueryHelper.setParameter(query, criteriaList, pagingBean.getOrderList());												
				query.setFirstResult( (int)pagingBean.getOffsetBegin() ).setMaxResults( pagingBean.getRowsPerPage() );

				return query.getResultList();					
			}
		}
		catch( Exception e ){			
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		}
	}

    @SuppressWarnings("unchecked")
	public <T extends Object> List<T> queryLike( String jpql, List<Criteria> criteriaList ) throws RollBackException {
		
		try{				
					
			if( jpql.indexOf("WHERE") == -1 ){
				jpql += " WHERE 1 = 1 ";
			}
			
			String criteriaString = QueryHelper.genCriteriaStringLike(criteriaList); 

			Query query = entityManager.createQuery(jpql + criteriaString.toString());						
			query = QueryHelper.setParameterLike(query, criteriaList, null);												
			
			return query.getResultList();			
		}
		catch( Exception e ){			
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		}
	}		

    @SuppressWarnings("unchecked")
	public < T extends Object> List<T> queryLike( String jpql, String jpqlCount, List<Criteria> criteriaList, PagingBean pagingBean ) throws RollBackException {
		
		try{				
					
			if( pagingBean == null ){
				return queryLike( jpql, criteriaList );
			}
			else{

				if( jpql.indexOf("WHERE") == -1 ){
					jpql += " WHERE 1 = 1 ";
				}
				if( jpqlCount.indexOf("WHERE") == -1 ){
					jpqlCount += " WHERE 1 = 1 ";
				}
				
				String criteriaString = QueryHelper.genCriteriaStringLike(criteriaList); 
				String orderString = QueryHelper.genOrderString(pagingBean.getOrderList());
				Query countQuery = entityManager.createQuery(jpqlCount + criteriaString.toString());
				countQuery = QueryHelper.setParameterLike(countQuery, criteriaList );				
				pagingBean.setTotalRows( (Long)countQuery.getSingleResult() );		
				
				Query query = entityManager.createQuery(jpql + criteriaString.toString() + orderString);						
				query = QueryHelper.setParameterLike(query, criteriaList, pagingBean.getOrderList());												
				query.setFirstResult( (int)pagingBean.getOffsetBegin() )
					 .setMaxResults( pagingBean.getRowsPerPage() );

				return query.getResultList();					
			}
		}
		catch( Exception e ){			
			throw new RollBackTechnicalException(CommonMessageCode.COM4991, e);
		}
	}	

    public Object querySingleResult(String jpql) throws RollBackTechnicalException {
		try{
	    	return entityManager.createQuery(jpql).getSingleResult();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		}	
	}

	public Object querySingleResult(String jpql, Object... params) throws RollBackException {
		try{
			Query q = entityManager.createQuery(jpql);
			for(int i=0; i<params.length;i++) {
				q.setParameter(i+1, params[i]);
			}
			return q.getSingleResult();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		}	
	}

    public Object querySingleResult(String jpql, List<Criteria> list) throws RollBackException {
		try{				
			
			String orderBy = "";		
			int k = jpql.toUpperCase().indexOf("ORDER BY");
			if(  k > -1) {
				orderBy = " " + jpql.substring(k, jpql.length());
				jpql = jpql.substring(0, k);
				
			}

			if( jpql.toUpperCase().indexOf("WHERE") == -1 ){
				jpql += " WHERE 1 = 1 ";
			}

			String criteriaString = QueryHelper.genCriteriaString(list); 

			Query query = entityManager.createQuery(jpql + criteriaString + orderBy);						
			query = QueryHelper.setParameter(query, list, null);												

			return query.getSingleResult();			
		}
		catch( Exception e ){			
			throw new RollBackTechnicalException(CommonMessageCode.COM4991,e);
		}
	}

	public int executeBatch(String sql) throws RollBackException {
		return executeNativeSQL(sql.split("\\;"));
	}

	public int executeNativeSQL(String sql) throws RollBackException {
		try{
			return entityManager.createNativeQuery(sql).executeUpdate();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}	
	}

	public int executeNativeSQL(String sql, Object... params) throws RollBackException {
		try{
			Query q = entityManager.createNativeQuery(sql);
			for(int i=0; i<params.length;i++) {
				q.setParameter(i+1, params[i]);
			}
			return q.executeUpdate();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}	
	
	protected int executeNativeSQL(String[] sqlCommands) throws RollBackException {
		try{
			for (String sqlCommand : sqlCommands) {
				sqlCommand = sqlCommand.trim();
				if(sqlCommand.length() > 0) {
					int r = entityManager.createNativeQuery(sqlCommand).executeUpdate();
					if (logger.isDebugEnabled()) {
						logger.debug(r+":"+sqlCommand);
					}
				}
			}
			return 0;
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Object>List<T> nativeQuery(String sql) throws RollBackException{
		try{
			return entityManager.createNativeQuery(sql).getResultList();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Object>List<T> nativeQuery(String sql, Object... params) throws RollBackException{
		try{
			Query q = entityManager.createNativeQuery(sql);
			for(int i=0; i<params.length;i++) {
				q.setParameter(i+1, params[i]);
			}
			return q.getResultList();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz) throws RollBackException{
		try{
			return entityManager.createNativeQuery(sql, clazz).getResultList();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz, Object... params) throws RollBackException {
		try{
			Query q = entityManager.createNativeQuery(sql, clazz);
			for(int i=0; i<params.length;i++) {
				q.setParameter(i+1, params[i]);
			}
			return q.getResultList();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> List<T> nativeQuery(String sql, Class<T> clazz, PagingBean pagingBean) throws RollBackException {
		try{	
			Query query = entityManager.createNativeQuery(sql, clazz);
			
			int orderByIndex = sql.toUpperCase().lastIndexOf("ORDER BY");
			String countSql = sql;
			if( orderByIndex > -1) {
				countSql = countSql.substring(0, orderByIndex);
				
			}
			String countQuery = "Select count(*) from ("+countSql+") data";
			Query countQ = entityManager.createNativeQuery(countQuery);
			
			pagingBean.setTotalRows( ((Number)countQ.getSingleResult()).longValue() );				
			query.setFirstResult( (int)pagingBean.getOffsetBegin() )
				 .setMaxResults( pagingBean.getRowsPerPage() );
	
			return query.getResultList();		
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Object >List<T> nativeQuery(String sql, Class<T> clazz, PagingBean pagingBean, Object... params) throws RollBackException {
		try{	
			Query query = entityManager.createNativeQuery(sql, clazz);
			for(int i=0; i<params.length;i++) {
				query.setParameter(i+1, params[i]);
			}		
			
			int orderByIndex = sql.toUpperCase().lastIndexOf("ORDER BY");
			String countSql = sql;
			if( orderByIndex > -1) {
				countSql = countSql.substring(0, orderByIndex);
			}
			String countQuery = "Select count(*) from ("+countSql+") data";
			Query countQ = entityManager.createNativeQuery(countQuery);

			for(int i=0; i<params.length;i++) {
				countQ.setParameter(i+1, params[i]);
			}		
			
			pagingBean.setTotalRows( ((Number)countQ.getSingleResult()).longValue() );				
			query.setFirstResult( (int)pagingBean.getOffsetBegin() )
				 .setMaxResults( pagingBean.getRowsPerPage() );
	
			return query.getResultList();		
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}

	public Object nativeQuerySingleResult(String sql) throws RollBackException {
		try{
			Query q = entityManager.createNativeQuery(sql);
			q.setFirstResult( 0 )
			 .setMaxResults( 1 ); 
			return q.getSingleResult();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}

	public Object nativeQuerySingleResult(String sql, Object... params) throws RollBackException {
		try{
			Query q = entityManager.createNativeQuery(sql);
			for(int i=0; i<params.length;i++) {
				q.setParameter(i+1, params[i]);
			} 
			q.setFirstResult( 0 )
			 .setMaxResults( 1 );
			return q.getSingleResult();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}

	public void flush() throws RollBackException {
		try{
			entityManager.flush();
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}

	public void refresh(Object entity) throws RollBackException {
		try{
			entityManager.refresh(entity);
		}
		catch( Exception e ){
			throw new RollBackTechnicalException(CommonMessageCode.COM4998,e);
		}
	}

	@Override
	public <T> List<T> findByProperty(Class<T> class1, List<Criteria> criterias) throws RollBackException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByProperty(Class<T> class1, List<Criteria> criterias, PagingBean value)
			throws RollBackException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
