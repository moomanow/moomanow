package com.moomanow.core.common.processhandler;

import java.util.Locale;

import com.moomanow.core.common.context.CurrentThread;

public class ProcessContextTest {

	public static void pre() {
		ProcessContext context = CurrentThread.getProcessContext();
		if (context == null) {
			context = new ProcessContext();
			context.setLocale(Locale.ENGLISH);
		}
		CurrentThread.setProcessContext(context);
	}
}
