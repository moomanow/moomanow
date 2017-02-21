package com.moomanow.fps;

import com.moomanow.fps.components.Neuron;

public interface BrainService {

	public <Data> Neuron<Data> getNeuronByName(String neuronName,Data class1);

}
