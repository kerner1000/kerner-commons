/**
 * 
 */
package de.kerner.commons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kerner
 * 
 */
public abstract class AsyncCallBackExecutor<R, V> implements AsyncCallBack<R, V> {

	private final ExecutorService exe = Executors.newCachedThreadPool();

	public void execute(final V value) {
		exe.execute(new Runnable() {
			@Override
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
