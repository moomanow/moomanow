package com.moomanow.struts2.views.freemarker.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.freemarker.tags.TagModel;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class DatePickerModel extends TagModel {

	public DatePickerModel(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		super(stack, req, res);
	}

	@Override
	protected Component getBean() {
		return null;
	}

}
