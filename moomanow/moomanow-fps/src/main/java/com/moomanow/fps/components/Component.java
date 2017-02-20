package com.moomanow.fps.components;

import com.moomanow.fps.bean.IComponentResult;

public interface Component<Artery,Vein> {

	public IComponentResult<Vein> execute(IComponentResult<Artery> Artery);
}
