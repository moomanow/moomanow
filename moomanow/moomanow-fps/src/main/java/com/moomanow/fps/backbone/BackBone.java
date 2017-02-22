package com.moomanow.fps.backbone;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.moomanow.fps.BrainService;
import com.moomanow.fps.bean.INeuronResult;
import com.moomanow.fps.bean.NeuronResult;
import com.moomanow.fps.components.Neuron;
import com.moomanow.fps.dynamicbean.DynamicBeanService;
import com.moomanow.fps.dynamicbean.InterfaceDynamicBean;
import com.moomanow.fps.dynamicbean.ProxyDynamicBean;

public class BackBone {

	private BrainService brainService;
	private DynamicBeanService dynamicBeanService;
	
	public void setDynamicBeanService(DynamicBeanService dynamicBeanService) {
		this.dynamicBeanService = dynamicBeanService;
	}
	public void setBrainService(BrainService brainService) {
		this.brainService = brainService;
	}

	public <DataOut> INeuronResult<DataOut> execute(String neuronName, Map<String, Object> data,Class<DataOut> dataOutClass) {
		List<Neuron<?>> neurons = brainService.getNeuronByName(neuronName);
		for (Neuron neuron : neurons) {
//			System.out.println(neuron.getClass());
//			System.out.println(neuron.getClass().getSuperclass());
//			System.out.println(neuron.getClass().getGenericSuperclass());
//			System.out.println(neuron.getClass().getClasses());
//			System.out.println(neuron.getClass().getComponentType());
//			System.out.println(neuron.getClass().getDeclaredClasses());
//			System.out.println(neuron.getClass().getMethods());
//			System.out.println(neuron.getClass());
//			System.out.println(neuron.getClass());
//			System.out.println(neuron.getClass());
//			System.out.println(neuron.getClass());
//			System.out.println(neuron.getClass());
//			System.out.println(neuron.getClass());
//			neuron.getClass().getDeclaredClasses();
//			neuron.getClass().getSuperclass().getGenericSuperclass().getTypeName();
			Object dataInput = null;
			for (Method method : neuron.getClass().getMethods()) {
				if("execute".equals(method.getName())){
					Class<?>[] classes = method.getParameterTypes();
					dataInput = ProxyDynamicBean.newInstance(data, classes[0].getEnclosingClass(), dynamicBeanService, neuronName);
				}
			}
			neuron.execute(dataInput);
		}
		DataOut dataOut = ProxyDynamicBean.newInstance(data, dataOutClass, dynamicBeanService, neuronName);
		return new NeuronResult<DataOut>(dataOut);
	}

	public <DataInput>  DataInput buildBean(String neuronName, Map<String, Object> data,Class<DataInput> dataOutClass) {
		DataInput dataInput = (DataInput) ProxyDynamicBean.newInstance(data ,dataOutClass,dynamicBeanService,neuronName);
		return dataInput;
	}

}
