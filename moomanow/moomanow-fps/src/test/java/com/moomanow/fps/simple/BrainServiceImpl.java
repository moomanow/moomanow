package com.moomanow.fps.simple;

import java.util.LinkedList;
import java.util.List;

import com.moomanow.fps.brain.bean.BrainBean;
import com.moomanow.fps.brain.service.BrainService;
import com.moomanow.fps.components.Neuron;

public class BrainServiceImpl implements BrainService {

	public List<Neuron<?>> getNeuronByName(String neuronName) {
		List<Neuron<?>> list = new LinkedList<Neuron<?>>();
		list.add(new SimpleProcessNeuron());
		return list;
	}

	public String addNeuron(Neuron<?> neuron) {
		// TODO Auto-generated method stub
		return null;
	}

	public BrainBean findBrainBean(String brainCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public BrainBean createBrainBean(List<Neuron<?>> neuron) {
		// TODO Auto-generated method stub
		return null;
	}

}
