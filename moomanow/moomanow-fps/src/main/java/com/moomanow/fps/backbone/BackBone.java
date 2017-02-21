package com.moomanow.fps.backbone;

import com.moomanow.fps.BrainService;
import com.moomanow.fps.bean.INeuronResult;
import com.moomanow.fps.bean.NeuronResult;
import com.moomanow.fps.components.Neuron;

public class BackBone {

	private BrainService brainService;

	public <Data> INeuronResult<Data> execute(String neuronName, Data artery) {
		Neuron<Data> neuron = brainService.getNeuronByName(neuronName, artery);
		INeuronResult<Data> neuronResult = new NeuronResult<Data>(artery);
		do {
			neuron.execute(neuronResult);
			neuron = neuronResult.nextNeuron();
		} while (neuron != null);
		return neuronResult;
	}

}
