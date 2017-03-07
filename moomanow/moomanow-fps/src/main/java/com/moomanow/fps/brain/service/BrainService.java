package com.moomanow.fps.brain.service;

import java.util.List;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.fps.brain.bean.BrainBean;
import com.moomanow.fps.components.Neuron;

public interface BrainService {

//	public List<Neuron<?>> getNeuronByName(String neuronName);
	public BrainBean createBrainBean(List<Neuron<?>> neuron)throws RollBackException, NonRollBackException;
	public BrainBean findBrainBean(String brainCode)throws RollBackException, NonRollBackException;

}
