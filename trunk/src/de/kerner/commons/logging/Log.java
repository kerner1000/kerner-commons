package de.kerner.commons.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p> slf4j-logging helper class </p>
 * @lastVisit 2009-10-05
 * @author Alexander Kerner
 *
 */
public class Log {
	
	//TODO: move to kerner.commons

	private final Logger logger;

	public Log(Logger logger) {
		this.logger = logger;
	}

	public Log(Class<?> c) {
		this.logger = LoggerFactory.getLogger(c);
	}

	public void debug(Object msg) {
		if (logger.isDebugEnabled()) {
			logger.debug(msg.toString());
		}
	}

	public void debug(Object msg, Throwable t) {
		if (logger.isDebugEnabled()) {
			logger.debug(msg.toString(), t);
		}
	}

	public void info(Object msg) {
		if (logger.isInfoEnabled()) {
			logger.info(msg.toString());
		}
	}

	public void warn(Object msg) {
		if(logger.isWarnEnabled())
		logger.warn(msg.toString());
	}
	
	public void warn(Object msg, Throwable t) {
		if(logger.isWarnEnabled())
		logger.warn(msg.toString(), t);
	}
	
	public void error(String msg) {
		if(logger.isErrorEnabled())
		logger.error(msg);
	}
	
	public void error(String msg, Throwable t) {
		if(logger.isErrorEnabled())
		logger.error(msg, t);
	}
}

