/**
 * 
 */
package de.kerner.commons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * TODO description
 * </p>
 * <p>
 * TODO Example of usage
 * </p>
 * 
 * @author Alexander Kerner
 * 
 */
public abstract class AsyncCallBackExecutor<R, V> implements AsyncCallBack<R, V> {

	private final ExecutorService exe;
	
	public AsyncCallBackExecutor() {
		exe = Executors.newCachedThreadPool();
	}
	
	public AsyncCallBackExecutor(ExecutorService exe) {
		this.exe = exe;
	}

	public void execute(final V value) {
		exe.execute(new Runnable() {
			
			public void run() {
				try {
					doOnSucess(AsyncCallBackExecutor.this.run(value));
				} catch (final Throwable t) {
					doOnFailure(t);
				}
			}
		});
	}
}
