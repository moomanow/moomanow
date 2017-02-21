package com.moomanow.fps.bean;

import com.moomanow.fps.components.Neuron;

public interface INeuronResult<T extends Object> {
	public Neuron<T> nextNeuron();
	void selectNextNeuron(NeuronCode neuronCode);
}
