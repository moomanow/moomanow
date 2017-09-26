package com.moomanow.fps.brain;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.moomanow.core.common.context.ApplicationContextUtil;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.fps.backbone.BackBone;
import com.moomanow.fps.bean.INeuronResult;
import com.moomanow.fps.brain.service.BrainService;
import com.moomanow.fps.dynamicbean.proxy.ProxyDynamicBean;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class,
	    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class })
@ContextConfiguration(locations = {
      "classpath:spring.xml",
      "classpath:prj-brain.xml"})
public class BrainTestLive {
	
	@Autowired
	private BrainService brainService;
	
	@Autowired
	private BackBone backBone;
	@Test
	public void testSingleLine() throws RollBackException, NonRollBackException{
//		ApplicationContext s = ApplicationContextUtil.getApplicationContext();
//		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) ApplicationContextUtil.getApplicationContext();
		
//		configurableApplicationContext.refresh();
//		List<Neuron<?>> neurons = new LinkedList<Neuron<?>>();
//		
//		neurons.add(new FirstNeuron());
//		neurons.add(new SecondNeuron());
//		neurons.add(new ThirdNeuron());
//		BrainBean brainBean = brainService.createBrainBean(neurons );
		
		String brainCode = "B001";
//		System.out.println(brainCode);
		
		Map<String, Object> map = new HashMap<String, Object>();
		ProxyDynamicBean.newInstance(map , new Class[] {InputData.class },brainCode);
		INeuronResult<OutData> neuronResultOutData = backBone.execute(brainCode, map, OutData.class);

	}
	
//	@Test
//	public void testSwitch(){
//		
//		List<Neuron<?>> neurons = new LinkedList<Neuron<?>>();
//		
//		neurons.add(new FirstNeuron());
//		neurons.add(new SwitchNeuron());
//		neurons.add(new ThirdNeuron());
//		BrainBean brainBean = brainService.createBrainBean(neurons );
//		
//		String brainCode = brainBean.getBrainCode();
//		System.out.println(brainCode);
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		ProxyDynamicBean.newInstance(map , new Class[] {InputData.class },brainCode);
//		INeuronResult<OutData> neuronResultOutData = backBone.execute(brainCode, map, OutData.class);
//
//	}

}
