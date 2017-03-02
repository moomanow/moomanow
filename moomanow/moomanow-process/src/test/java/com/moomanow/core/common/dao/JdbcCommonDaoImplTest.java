package com.moomanow.core.common.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.ProcessContextTest;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
//	    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
//@ContextConfiguration(locations = {
//        "classpath:spring.xml",
//        "classpath:prj-jdbcCommonDaoImpl.xml"})
//public class JdbcCommonDaoImplTest {
//	
//	@Autowired
//	private JdbcCommonDao jdbcCommonDao;
//	
//	@Before
//	public void name() {
//		ProcessContextTest.pre();
//	}
//	@Test
//    public void test_save() throws RollBackException, NonRollBackException {
//		
////		Vacancy vacancy = jdbcCommonDao.save(new Vacancy());
//    }
//
//}
