package com.moomanow.fps.brain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.moomanow.fps.brain.bean.BrainBean;
import com.moomanow.fps.components.Neuron;

public class BrainServiceImpl implements BrainService {

	private Map<String , BrainBean> map = new HashMap<String, BrainBean>();
	public BrainBean createBrainBean(List<Neuron<?>> neuron) {
		BrainBean brainBean = new BrainBean();
		UUID uuid = UUID.randomUUID();
		String brainCode = uuid.toString();
		brainBean.setBrainCode(brainCode);
		map.put(brainCode, brainBean);
		return brainBean;
	}

	public BrainBean findBrainBean(String brainCode) {
		BrainBean brainBean = map.get(brainCode);
		return brainBean;
	}

}
