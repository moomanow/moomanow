package com.moomanow.struts2.views.jsp.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractClosingTag;

import com.moomanow.struts2.components.DatePicker;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class DatePickerTag extends AbstractClosingTag  {

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return new DatePicker(stack, req, res);
	}

}
