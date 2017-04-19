package com.moomanow.core.common.util;

import org.junit.Assert;
import org.junit.Test;

import com.moomanow.core.common.bean.ClassMapper;
import com.moomanow.core.common.util.bean.*;

public class TestJPAUtil {
	
	@Test
	public void testTableName(){
		
		ClassMapper classMapper = JPAUtil.getClassMapper(Bean1.class);
		Assert.assertEquals(classMapper.getTableName(), "Bean1");
		classMapper = JPAUtil.getClassMapper(Bean2.class);
		Assert.assertEquals(classMapper.getTableName(), "tableNameBean2");
		
		
	}
	
	@Test
	public void testCloumName(){
		
//		ClassMapper classMapper = JPAUtil.getClassMapper(Bean3.class);
//		Assert.assertEquals(classMapper.getTableName(), "Bean1");
//		classMapper = JPAUtil.getClassMapper(Bean2.class);
//		Assert.assertEquals(classMapper.getTableName(), "tableNameBean2");
		
		
	}

}
