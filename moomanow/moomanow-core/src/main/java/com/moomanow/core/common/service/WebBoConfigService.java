package com.moomanow.core.common.service;

import java.util.Map;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;


public interface WebBoConfigService {
	
	public Map<Object, Object> getWebBoConfigByGeographyPageName(Long idRegion, Long idCountry, Long idZone, Long idProvince, Long idCity)throws RollBackException ,NonRollBackException;
	
	public boolean getWebBoConfigMapList()throws RollBackException ,NonRollBackException;
	
	public boolean getIsDisplay(Long idRegion, Long idCountry, Long idZone, Long idProvince, Long idCity, String page, String field)throws RollBackException ,NonRollBackException;
}
