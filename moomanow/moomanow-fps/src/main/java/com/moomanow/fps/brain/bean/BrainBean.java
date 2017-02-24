package com.moomanow.fps.brain.bean;

import java.io.Serializable;
import java.util.List;

import com.moomanow.fps.components.Neuron;

public class BrainBean implements Serializable {

	private List<Neuron<?>> lineNeuron;
	private String brainCode;
	
	public List<Neuron<?>> getLineNeuron() {
		return lineNeuron;
	}
	public void setLineNeuron(List<Neuron<?>> lineNeuron) {
		this.lineNeuron = lineNeuron;
	}
	public String getBrainCode() {
		return brainCode;
	}
	public void setBrainCode(String brainCode) {
		this.brainCode = brainCode;
	}
	
	
	
}
