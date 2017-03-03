package com.moomanow.fps.components;

public final class SwitchNeuron implements Neuron<ISwitch> {

    
	@Override
	public void execute(ISwitch input) {
		Boolean test =  input.isTest();
		if(test!=null&&test){
			input.setNextCode(input.getTureCode());
		}else{
			input.setNextCode(input.getFalseCode());
		}
//		Ognl.getValue(tree, context, root);
//		getValue(key, context, root, resultType)
		
	}
	

}
