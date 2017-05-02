/**
 * 
 */
package com.moomanow.core.common.context;

import com.moomanow.core.common.processhandler.BlockContext;
import com.moomanow.core.common.processhandler.Context;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class CurrentBlockContextThread {
	
	private static final ThreadLocal<BlockContext> blockContextThreadLocal = new ThreadLocal<BlockContext>();
	
	
	public static BlockContext getBlockContext(){
		return blockContextThreadLocal.get();
	}
	
	public static <T extends Context> T getContext(Class<T> class1){
		BlockContext blockContext = getBlockContext();
		if(blockContext==null){
			blockContext = new BlockContext();
			blockContextThreadLocal.set(blockContext);
		}
		T t = blockContext.getContext(class1);
		if(t==null){
			try {
				t = class1.newInstance();
				blockContext.setContext(t);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return blockContext.getContext(class1);
	}
	
	public static void setBlockContext(BlockContext blockContext){
		blockContextThreadLocal.set(blockContext);
	}
	
	public static void remove(){
		blockContextThreadLocal.remove();
	}

}
