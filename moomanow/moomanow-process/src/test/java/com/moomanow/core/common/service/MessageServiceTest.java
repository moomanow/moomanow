package com.moomanow.core.common.service;

import org.junit.Assert;
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

import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.constant.CommonMessageCode;
import com.moomanow.core.common.processhandler.ProcessContextTest;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
	    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = {
        "classpath:spring.xml",
        "classpath:prj-messageServiceTest.xml"})
public class MessageServiceTest {
	@Autowired
	private MessageService messageService;
	
	@Before
	public void name() {
		ProcessContextTest.pre();
	}
	@Test
    public void test_ml_always_return_true() {
		
		String[] para = null;
		IMessage iMessage = messageService.getMessage(CommonMessageCode.COM0000,para );
		
		Assert.assertNotNull(iMessage);
		Assert.assertEquals(iMessage.getMessageCode(), "COM0000");

    }

}
