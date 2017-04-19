/**
 * 
 */
package com.moomanow.core.common.context;

import com.moomanow.core.common.processhandler.BlockContext;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class CurrentBlockContextThread {
	
	private static final ThreadLocal<BlockContext> blockContextThreadLocal = new ThreadLocal<BlockContext>();
	
	
	public static BlockContext getBlockContext(){
		return blockContextThreadLocal.get();
	}
	
	public static <T> T getContext(Class<T> class1){
		return blockContextThreadLocal.get().getContext(class1);
	}
	
	public static void setBlockContext(BlockContext blockContext){
		blockContextThreadLocal.set(blockContext);
	}
	
	public static void remove(){
		blockContextThreadLocal.remove();
	}

}
