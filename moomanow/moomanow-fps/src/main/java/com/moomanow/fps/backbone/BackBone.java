package com.moomanow.fps.backbone;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.moomanow.fps.bean.INeuronResult;
import com.moomanow.fps.bean.NeuronResult;
import com.moomanow.fps.brain.bean.BrainBean;
import com.moomanow.fps.brain.service.BrainService;
import com.moomanow.fps.components.Neuron;
import com.moomanow.fps.dynamicbean.proxy.ProxyDynamicBean;
import com.moomanow.fps.dynamicbean.service.DynamicBeanService;

public class BackBone {

	private BrainService brainService;
	private DynamicBeanService dynamicBeanService;

	public void setDynamicBeanService(DynamicBeanService dynamicBeanService) {
		this.dynamicBeanService = dynamicBeanService;
	}

	public void setBrainService(BrainService brainService) {
		this.brainService = brainService;
	}

	public <DataOut> INeuronResult<DataOut> execute(String brainCode, Map<String, Object> data,Class<DataOut> dataOutClass) {
		BrainBean brainBean  = brainService.findBrainBean(brainCode);
		for (Neuron neuron : brainBean.getLineNeuron()) {
			Object dataInput = null;
			Type type = neuron.getClass().getGenericSuperclass();
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type typeed = parameterizedType.getActualTypeArguments()[0];
			dataInput = ProxyDynamicBean.newInstance(data, (Class) typeed, dynamicBeanService, brainCode);

			neuron.execute(dataInput);
		}
		DataOut dataOut = ProxyDynamicBean.newInstance(data, dataOutClass, dynamicBeanService, brainCode);
		return new NeuronResult<DataOut>(dataOut);
	}

	public <DataInput> DataInput buildBean(String neuronName, Map<String, Object> data, Class<DataInput> dataOutClass) {
		DataInput dataInput = (DataInput) ProxyDynamicBean.newInstance(data, dataOutClass, dynamicBeanService,
				neuronName);
		return dataInput;
	}

}
