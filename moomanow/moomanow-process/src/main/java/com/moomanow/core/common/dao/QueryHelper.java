package com.moomanow.core.common.dao;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.Transient;

import com.moomanow.core.common.bean.Criteria;
import com.moomanow.core.common.bean.PagingBean;
import com.moomanow.core.common.bean.PagingBean.Order;

public class QueryHelper {

	public static List<Criteria> beanToParameterList(Object beanObj) throws Exception{
		List<Criteria> parameterList = null;		
		
		if (beanObj != null){
			
			parameterList = new ArrayList<Criteria>();
			
			Class<?> beanClazz = beanObj.getClass();
			Method[] beanMethods = beanClazz.getMethods();
			int beanMethodCount = beanMethods.length;
			Method beanMethod = null;
			String beanMethodName = null;
	
			Object[] beanArgs = null;
	
			for (int i = 0; i < beanMethodCount; i++) {
	
				String beanFieldName = null;
				String beanFieldType = null;
				Object beanFieldValue = null;
				Criteria parameter = null;
	
				beanMethod = beanMethods[i];
				beanMethodName = beanMethod.getName();
					
				if (beanMethodName.startsWith("get")) {

					Transient annotation = beanMethod.getAnnotation(Transient.class);		
					if( annotation != null ){
						continue;					
					}				
					
					beanFieldName = beanMethodName.substring(3,4).toLowerCase() + beanMethodName.substring(4);
					Class<?> clazz = beanMethod.getReturnType();
					beanFieldType = clazz.getName();
					
					beanFieldValue = beanMethod.invoke(beanObj, beanArgs);
	
					if (beanFieldValue != null) {
						if (beanFieldType.equals("java.lang.String")) {
							if (((String) beanFieldValue).length() > 0) {
								parameter = new Criteria(beanFieldName, beanFieldValue);
							}
						}
						else if (beanFieldType.equals("java.util.Set")) {
							// DO Nothing
						}
						else if (beanFieldType.equals("java.util.List") || beanFieldType.equals("java.lang.Class")) {
							// DO Nothing
						}
						else {
							parameter = new Criteria(beanFieldName, beanFieldValue);
						}
					}
	
					if (parameter != null) {
						parameterList.add(parameter);
					}
				}
			}
		}
		
		return parameterList;
	}
	
	public static Map<String, Criteria> beanToParameterMap(Object beanObj) throws Exception{
		Map<String, Criteria> parameterMap = null;		
		
		if (beanObj != null){
			
			parameterMap = new HashMap<String, Criteria>();
			
			Class<?> beanClazz = beanObj.getClass();
			Method[] beanMethods = beanClazz.getMethods();
			int beanMethodCount = beanMethods.length;
			Method beanMethod = null; // each BeanMethod
			String beanMethodName = null; // each BeanMethodName
	
			Object[] beanArgs = null;
	
			for (int i = 0; i < beanMethodCount; i++) {
	
				String beanFieldName = null;
				String beanFieldType = null;
				Object beanFieldValue = null;
				Criteria parameter = null;
	
				beanMethod = beanMethods[i];
				beanMethodName = beanMethod.getName();
					
				if (beanMethodName.startsWith("get")) {
					beanFieldName = beanMethodName.substring(3,4).toLowerCase() + beanMethodName.substring(4);
					Class<?> clazz = beanMethod.getReturnType();
					beanFieldType = clazz.getName();
					
					beanFieldValue = beanMethod.invoke(beanObj, beanArgs);
	
					if (beanFieldValue != null) {
						if (beanFieldType.equals("java.lang.String")) {
							if (((String) beanFieldValue).length() > 0) {
								parameter = new Criteria(beanFieldName, beanFieldValue);
							}
						}
						else if (beanFieldType.equals("java.util.Set")) {
							// DO Nothing
						}
						else if (beanFieldType.equals("java.util.List") || beanFieldType.equals("java.lang.Class")) {
							// DO Nothing
						}
						else {
							parameter = new Criteria(beanFieldName, beanFieldValue);
						}
					}
	
					if (parameter != null) {
						parameterMap.put(parameter.getColumn(),parameter);
					}
				}
			}
		}
		
		return parameterMap;
	}	
	
	public static String genCriteriaString( List<Criteria> criteriaList ){
		StringBuilder criteriaString = new StringBuilder();
		if( criteriaList != null ){
			for( Criteria criteria : criteriaList ){
				if( criteria.getTable() != null ){
					criteriaString.append( " AND " + criteria.getTable() + "." + criteria.getColumn() + " = :" + criteria.getParam() );
				}
				else{
					criteriaString.append( " AND " + criteria.getColumn() + " = :" + criteria.getParam() );
				}
			}
		}
		return criteriaString.toString();
	}
	
	public static String genCriteriaStringLike( List<Criteria> criteriaList ){
		StringBuilder criteriaString = new StringBuilder();
		if( criteriaList != null ){
			for( Criteria criteria : criteriaList ){
				if( criteria.getTable() != null ){
					if( criteria.getValue().getClass().equals(java.lang.String.class) ){
						criteriaString.append( " AND UPPER(" + criteria.getTable() + "." + criteria.getColumn() + ") LIKE :" + criteria.getParam() );
					}
					else{
						criteriaString.append( " AND " + criteria.getTable() + "." + criteria.getColumn() + " = :" + criteria.getParam() );
					}
				}
				else{
					if( criteria.getValue().getClass().equals(java.lang.String.class) ){
						criteriaString.append( " AND UPPER(" + criteria.getColumn() + ") LIKE :" + criteria.getParam() );
					}
					else{
						criteriaString.append( " AND " + criteria.getColumn() + " = :" + criteria.getParam() );
					}					
				}
			}
		}
		return criteriaString.toString();
	}	

	public static String genOrderString( List<Order> orderList ){
		StringBuilder orderString = new StringBuilder();
		if( orderList != null ){
			if( orderList != null && orderList.size() > 0 ){
				orderString.append(" order by ");
				for( Order order : orderList ){
					orderString.append( CommonDao.ENTITY_MODEL_ALIAS + "." + order.getOrderBy() + " " + order.getOrderMode() + ",");
				}
				orderString.deleteCharAt(orderString.length()-1);
			}
		}
		return orderString.toString();
	}

	public static Query setParameter( Query query, List<Criteria> criteriaList ){
		return setParameter( query, criteriaList, null );
	}
		
	public static Query setParameter( Query query, List<Criteria> criteriaList, List<Order> orderList ){		
		if( criteriaList != null ){
			for( Criteria criteria : criteriaList ){
				query.setParameter( criteria.getParam(), criteria.getValue() );
			}
		}
		return query;
	}
	
	public static Query setParameterLike( Query query, List<Criteria> criteriaList ){
		return setParameterLike( query, criteriaList, null );
	}	
	
	public static Query setParameterLike( Query query, List<Criteria> criteriaList, List<Order> orderList ){		
		if( criteriaList != null ){
			for( Criteria criteria : criteriaList ){
				if( criteria.getValue().getClass().equals(java.lang.String.class) ){
					query.setParameter( criteria.getParam(), "%"+((String)criteria.getValue()).toUpperCase()+"%" );
				}
				else{
					query.setParameter( criteria.getParam(), criteria.getValue() );
				}				
			}
		}
		return query;
	}
	
	public static String genCountQueryString( String queryString ){
		return " SELECT COUNT( * ) FROM (" + queryString + " ) ";
	}
			
	public static String appendOrderString( String queryString, PagingBean paging ){
		if( paging != null && paging.getOrderList().size() > 0 ){
			queryString += " ORDER BY " + paging.getOrderBy() + " " + paging.getOrderMode();
		}
		return queryString;
	}
	
	public static boolean isEmptyCriteria( String args ){
		if( args != null && args.length() > 0 ){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean isEmptyCriteria( Number args ){
		if( args != null && !args.toString().equals( -2 ) ){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean isEmptyCriteria( Object args ){		
		if( args != null ){
			return false;
		}
		else{
			return true;
		}
	}
}
