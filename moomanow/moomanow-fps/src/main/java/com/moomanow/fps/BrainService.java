package com.moomanow.fps;

import java.util.List;

import com.moomanow.fps.components.Neuron;

public interface BrainService {

	public List<Neuron<?>> getNeuronByName(String neuronName);

}
