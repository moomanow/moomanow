package com.moomanow.fps.brain.dao;

import java.util.List;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.fps.brain.bean.NeronBean;

public interface BrainDao {

	public List<NeronBean> getLineNuronByBrainCode(String brainCode) throws RollBackException, NonRollBackException ;

}
