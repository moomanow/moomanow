package com.moomanow.web.security.authorize.dao;

import java.util.List;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.web.security.authorize.bean.Menu;



public interface MenuDao {

	public List<Menu> findAll()throws NonRollBackException,RollBackException;
	public List<Menu> findAllForNavigation()throws NonRollBackException,RollBackException;

	public List<Menu> findAllByAction(Long actionId)throws NonRollBackException, RollBackException;
	public List<Menu> findAllByMenuId(Long menuId)throws NonRollBackException, RollBackException;
}
