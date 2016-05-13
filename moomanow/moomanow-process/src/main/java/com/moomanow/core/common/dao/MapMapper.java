package com.moomanow.core.common.dao;

import java.util.Map;

public interface MapMapper<T> {

	T mapRow(Map<String, Object> map, int rowNum);
}
