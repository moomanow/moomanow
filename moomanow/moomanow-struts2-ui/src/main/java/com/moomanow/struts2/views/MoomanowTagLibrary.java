package com.moomanow.struts2.views;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.TagLibraryDirectiveProvider;
import org.apache.struts2.views.TagLibraryModelProvider;

import com.moomanow.struts2.views.freemarker.tags.MoomanowModels;
import com.moomanow.struts2.views.velocity.components.DatePickerDirective;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class MoomanowTagLibrary implements TagLibraryDirectiveProvider, TagLibraryModelProvider  {

	@Override
	public Object getModels(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		return new MoomanowModels(stack, req, res);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Class> getDirectiveClasses() {
		Class[] directives = new Class[] { DatePickerDirective.class};
		return Arrays.asList(directives);
	}

}
