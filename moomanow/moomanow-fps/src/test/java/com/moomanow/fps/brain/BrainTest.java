package com.moomanow.fps.brain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

import com.moomanow.fps.brain.service.BrainService;
import com.moomanow.fps.components.Neuron;
import com.moomanow.fps.dynamicbean.InterfaceDynamicBean;
import com.moomanow.fps.dynamicbean.InterfaceOutDynamicBean;
import com.moomanow.fps.dynamicbean.proxy.ProxyDynamicBean;
import com.moomanow.fps.dynamicbean.service.DynamicBeanService;
import com.moomanow.fps.dynamicbean.service.DynamicBeanServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
	    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = {
      "classpath:spring.xml",
      "classpath:prj-simpleProcess.xml"})
public class BrainTest {
	
	@Autowired
	private BrainService brainService;
	@Test
	public void test(){
		
		List<Neuron<?>> neurons = new LinkedList<Neuron<?>>();
		
//		neurons.add(e)
		brainService.createBrainBean(neurons );
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
