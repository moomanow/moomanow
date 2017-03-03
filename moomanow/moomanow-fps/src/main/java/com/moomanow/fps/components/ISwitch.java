package com.moomanow.fps.components;

public interface ISwitch {

	public Boolean isTest();
	public void setTureCode(String code);
	public void setFalseCode(String code);
	public String getTureCode();
	public String getFalseCode();
	public String nextCode();
	public void setNextCode(String code);
	

}
