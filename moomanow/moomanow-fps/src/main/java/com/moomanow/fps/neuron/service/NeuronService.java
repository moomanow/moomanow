package com.moomanow.fps.neuron.service;

import java.util.List;

import com.moomanow.fps.components.Neuron;

public interface NeuronService {
	
	public Neuron<?> getNeuron(String neuronCode);
	public Neuron<?> getNeuronByclassName(String className);
	public List<Neuron<?>> findNeuron(String neuronCode,String className);
//	public Neuron<?> findNeuron(String neuronCode);
	

}
