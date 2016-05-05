package com.moomanow.struts2.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ClosingUIBean;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * @author Jaurpong.w(Kwan)
 *
 */
@StrutsTag(name = "datepicker", tldTagClass = "com.moomanow.struts2.jquery.datepicker.views.jsp.ui.DatePickerTag", description = "Render a jQuery UI datepicker", allowDynamicAttributes = true)
public class DatePicker extends ClosingUIBean  {

	public DatePicker(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		super(stack, request, response);
	}

	@Override
	public String getDefaultOpenTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getDefaultTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void evaluateExtraParams() {
		super.evaluateExtraParams();
		
	}
}
