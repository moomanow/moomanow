package com.moomanow.core.common.processhandler;

import java.util.Locale;

import com.moomanow.core.common.context.CurrentBlockContextThread;

public class ProcessContextTest {

	public static void pre() {
		BlockContext blockContext = CurrentBlockContextThread.getBlockContext();
		if (blockContext == null) {
			blockContext = new BlockContext();
			LocaleContext localeContext = new LocaleContext(Locale.ENGLISH);
			blockContext.setContext(localeContext);
			blockContext.setContext(new UserBeanContext());
		}
		CurrentBlockContextThread.setBlockContext(blockContext);
	}
}
