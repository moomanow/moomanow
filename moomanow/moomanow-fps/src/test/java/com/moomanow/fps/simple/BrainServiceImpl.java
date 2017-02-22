package com.moomanow.fps.simple;

import java.util.LinkedList;
import java.util.List;

import com.moomanow.fps.BrainService;
import com.moomanow.fps.components.Neuron;

public class BrainServiceImpl implements BrainService {

	public List<Neuron<?>> getNeuronByName(String neuronName) {
		List<Neuron<?>> list = new LinkedList<Neuron<?>>();
		list.add(new SimpleProcessNeuron());
		return list;
	}

}
