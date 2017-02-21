package com.moomanow.fps.components;

import java.util.List;

import com.moomanow.fps.bean.INeuronResult;

public interface Neuron<Data> {
	public void execute(INeuronResult<Data> input);
	public List<Neuron<Data>> getAxon();
}
