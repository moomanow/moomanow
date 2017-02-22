package com.moomanow.fps.components;

import java.util.List;

import com.moomanow.fps.bean.INeuronResult;

public interface Neuron<InterFaceData> {
	public void execute(InterFaceData input);
//	public List<Neuron<InterFaceData>> getAxon();
}
