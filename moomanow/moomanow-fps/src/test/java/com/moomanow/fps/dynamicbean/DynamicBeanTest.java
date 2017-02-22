package com.moomanow.fps.dynamicbean;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.moomanow.fps.backbone.BackBone;
import com.moomanow.fps.bean.INeuronResult;


//@RunWith(SpringJUnit4ClassRunner.class)
//@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
//	    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
//@ContextConfiguration(locations = {
//        "classpath:spring.xml",
//        "classpath:prj-simpleProcess.xml"})
public class DynamicBeanTest {

	@Test
	public void test(){
//		System.out.println("TEST");
		DynamicBeanService dynamicBeanService = new DynamicBeanServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		InterfaceDynamicBean interfaceDynamicBean = (InterfaceDynamicBean) ProxyDynamicBean.newInstance(map , new Class[] {InterfaceDynamicBean.class },dynamicBeanService,null);
		interfaceDynamicBean.setName("setset");
		InterfaceOutDynamicBean interfaceOutDynamicBean = (InterfaceOutDynamicBean) ProxyDynamicBean.newInstance(map , new Class[] {InterfaceOutDynamicBean.class },dynamicBeanService,null);
		
 		String s = interfaceOutDynamicBean.getName();
 		Assert.assertEquals(s, "setset");
	}
}
