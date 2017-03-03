package com.moomanow.fps.backbone;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.moomanow.fps.bean.INeuronResult;
import com.moomanow.fps.bean.NeuronResult;
import com.moomanow.fps.brain.bean.BrainBean;
import com.moomanow.fps.brain.service.BrainService;
import com.moomanow.fps.components.ISwitch;
import com.moomanow.fps.components.Neuron;
import com.moomanow.fps.components.SwitchNeuron;
import com.moomanow.fps.dynamicbean.proxy.ProxyDynamicBean;
import com.moomanow.fps.dynamicbean.service.DynamicBeanService;

public class BackBone {

	@Autowired
	private BrainService brainService;
	public void setBrainService(BrainService brainService) {
		this.brainService = brainService;
	}

	public <DataOut> INeuronResult<DataOut> execute(String brainCode, Map<String, Object> data,Class<DataOut> dataOutClass) {
		think(brainCode, data);
		DataOut dataOut = ProxyDynamicBean.newInstance(data, dataOutClass, brainCode);
		return new NeuronResult<DataOut>(dataOut);
	}
	
	private void think(String brainCode, Map<String, Object> data) {
		BrainBean brainBean  = brainService.findBrainBean(brainCode);
		for (Neuron neuron : brainBean.getLineNeuron()) {
			
			if(neuron instanceof SwitchNeuron){
				SwitchNeuron switchNeuron = (SwitchNeuron) neuron;
				ISwitch input = ProxyDynamicBean.newInstance(data, (Class) ISwitch.class, brainCode);
				switchNeuron.execute(input);
				String nextCode = input.nextCode();
				if(nextCode!=null){
					think(nextCode, data);
				}
			}else{
				Object dataInput = null;
				Type type = neuron.getClass().getGenericInterfaces()[0];
				ParameterizedType parameterizedType = (ParameterizedType) type;
				Type typeed = parameterizedType.getActualTypeArguments()[0];
				dataInput = ProxyDynamicBean.newInstance(data, (Class) typeed, brainCode);
				neuron.execute(dataInput);
			}

			
		}
	}

	public <DataInput> DataInput buildBean(String neuronName, Map<String, Object> data, Class<DataInput> dataOutClass) {
		DataInput dataInput = (DataInput) ProxyDynamicBean.newInstance(data, dataOutClass,neuronName);
		return dataInput;
	}

}
