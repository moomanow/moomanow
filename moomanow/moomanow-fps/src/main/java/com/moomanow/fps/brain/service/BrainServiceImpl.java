package com.moomanow.fps.brain.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;

import com.moomanow.core.common.context.ApplicationContextUtil;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.fps.brain.bean.BrainBean;
import com.moomanow.fps.brain.bean.NeronBean;
import com.moomanow.fps.brain.dao.BrainDao;
import com.moomanow.fps.components.Neuron;

public class BrainServiceImpl implements BrainService {
	@Autowired
	private BrainDao brainDao;
	
	
	private Map<String , BrainBean> map = new HashMap<String, BrainBean>();
	public BrainBean createBrainBean(List<Neuron<?>> neuron) throws RollBackException, NonRollBackException{
		BrainBean brainBean = new BrainBean();
		UUID uuid = UUID.randomUUID();
		String brainCode = uuid.toString();
		brainBean.setBrainCode(brainCode);
		brainBean.setLineNeuron(neuron);
		map.put(brainCode, brainBean);
		return brainBean;
	}
	
//	public BrainBean addBrainBean(List<Neuron<?>> neuron) {
//		BrainBean brainBean = new BrainBean();
//		UUID uuid = UUID.randomUUID();
//		String brainCode = uuid.toString();
//		brainBean.setBrainCode(brainCode);
//		brainBean.setLineNeuron(neuron);
//		map.put(brainCode, brainBean);
//		return brainBean;
//	}

	public BrainBean findBrainBean(String brainCode) throws RollBackException, NonRollBackException {
		
		BrainBean brainBean = new BrainBean();
		List<NeronBean> list = brainDao.getLineNuronByBrainCode(brainCode);
		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) ApplicationContextUtil.getApplicationContext();
		BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();
		List<Neuron<?>> neurons = new LinkedList<>();
//		lineNeuron.add()
		for (NeronBean neronBean : list) {
			try {
				Class c = Class.forName(neronBean.getClassType());
				BeanDefinition beanDefinition = new RootBeanDefinition(c);
//				
				beanDefinitionRegistry.registerBeanDefinition(neronBean.getCode(), beanDefinition);
				Neuron<?> neuron = ApplicationContextUtil.getBean(neronBean.getCode(),Neuron.class);
				neurons.add(neuron);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		
		brainBean.setLineNeuron(neurons );
		return brainBean;
	}

}
