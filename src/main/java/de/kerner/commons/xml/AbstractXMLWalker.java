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

package de.kerner.commons.xml;

import java.util.Enumeration;
import java.util.Stack;

import net.n3.nanoxml.IXMLElement;
import net.n3.nanoxml.XMLException;

public abstract class AbstractXMLWalker {

	private class XMLObject {
		final IXMLElement element;
		final Long depth;

		XMLObject(IXMLElement element, Long depth) {
			this.element = element;
			this.depth = depth;
		}
	}

	private final Stack<XMLObject> stack = new Stack<XMLObject>();
	private volatile boolean cancelled = false;
	private volatile Long maxDepth = null;
	private volatile Long minDepth = null;

	public synchronized void walk(final IXMLElement rootElement) throws XMLException {

		stack.push(new XMLObject(rootElement, 0L));
		while (!cancelled && !stack.isEmpty()) {
			handle(stack.pop());
		}
		lastAction(cancelled);

	}

	private void handle(final XMLObject element) throws XMLException {
		if ((minDepth != null && element.depth < minDepth)
				|| (maxDepth != null && element.depth > maxDepth)) {
			// out of boundaries
		} else {
			handleElement(element.element);
			handleChilds(element);
		}
	}

	abstract public void handleElement(final IXMLElement element) throws XMLException;

	@SuppressWarnings("unchecked")
	private void handleChilds(final XMLObject element) {
		final Stack<IXMLElement> stack = new Stack<IXMLElement>();
		final Enumeration<IXMLElement> content = element.element
				.enumerateChildren();
		if (content.hasMoreElements()) {
			while (content.hasMoreElements()) {
				stack.push(content.nextElement());
			}
			while (!stack.isEmpty()) {
				this.stack.push(new XMLObject(stack.pop(), element.depth + 1));
			}
		} else {
			// done
		}

	}

	public void stopWalking() {
		cancelled = true;
	}

	public void setMinDepth(Long min) {
		minDepth = min;
	}

	public void setMaxDepth(Long max) {
		maxDepth = max;
	}

	protected void lastAction(boolean wasCancelled) {
		// do nothing by default
	}
}
