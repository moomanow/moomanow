package com.moomanow.struts2.views.freemarker.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class MoomanowModels {
	
	protected DatePickerModel datePicker;
	
	private ValueStack stack;
	private HttpServletRequest req;
	private HttpServletResponse res;
	
	public MoomanowModels(ValueStack stack, HttpServletRequest req,HttpServletResponse res ) {
		this.stack = stack;
		this.req = req;
		this.res = res;
	}
	
	public DatePickerModel getDatePicker() {
		if(datePicker ==null){
			datePicker = new DatePickerModel(stack, req, res);
		}
		return datePicker;
	}
}
