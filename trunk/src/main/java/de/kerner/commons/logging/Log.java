/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

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
		if(msg == null)
			msg = "null";
		if (logger.isDebugEnabled()) {
			logger.debug(msg.toString());
		}
	}

	public void debug(Object msg, Throwable t) {
		if(msg == null)
			msg = "null";
		if (logger.isDebugEnabled()) {
			logger.debug(msg.toString(), t);
		}
	}
	
	public void debug(Throwable t) {
		if (logger.isDebugEnabled()) {
			logger.debug(t.getLocalizedMessage(), t);
		}
	}

	public void info(Object msg) {
		if(msg == null)
			msg = "null";
		if (logger.isInfoEnabled()) {
			logger.info(msg.toString());
		}
	}

	public void warn(Object msg) {
		if(msg == null)
			msg = "null";
		if(logger.isWarnEnabled())
		logger.warn(msg.toString());
	}
	
	public void warn(Object msg, Throwable t) {
		if(msg == null)
			msg = "null";
		if(logger.isWarnEnabled())
		logger.warn(msg.toString(), t);
	}
	
	public void warn(Throwable t) {
		if(logger.isWarnEnabled())
		logger.warn(t.getLocalizedMessage(), t);
	}
	
	public void error(String msg) {
		if(msg == null)
			msg = "null";
		if(logger.isErrorEnabled())
		logger.error(msg);
	}
	
	public void error(Throwable t) {
		if(logger.isErrorEnabled())
		logger.error(t.getLocalizedMessage(), t);
	}
	
	public void error(String msg, Throwable t) {
		if(msg == null)
			msg = "null";
		if(logger.isErrorEnabled())
		logger.error(msg, t);
	}
}

