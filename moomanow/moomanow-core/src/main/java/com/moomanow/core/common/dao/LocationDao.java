package com.moomanow.core.common.dao;

import com.moomanow.core.common.bean.LocationBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public interface LocationDao {

	public LocationBean getLocation(String countryCode,String countryName,String region,String city,String postalCode )throws NonRollBackException,RollBackException;

	public void refresh();

	public LocationBean getLocation(String userId)throws NonRollBackException,RollBackException;

}
