/*
 * Created on Sep 5, 2004
 *
 * $Id: SimpleTask.java,v 1.1 2004/09/05 00:28:46 mojo_jojo Exp $
 */
package org.va_labs.vae.tag.task;

import java.util.ArrayList;

import org.va_labs.vae.parser.IllegalAttributeException;
import org.va_labs.vae.tag.Tag;

/**
 * @author mojo_jojo
 *
 * Simple Task used when the task is not known.
 */
public class SimpleTask extends Task {

    public SimpleTask(String name)
	{
		super();
		this.name = new StringBuffer(name);
		attributes = new ArrayList();
		nestedElements = new ArrayList(3);
	}
	
	public SimpleTask(Tag taskTag)
	{
		this("Anonymous Task");
		setNestedElements(taskTag.getNestedElements());
		try {
			setAttributes(taskTag.getAttributes());
		} catch(IllegalAttributeException e) {
			System.out.println("Detected illegal task attribute");
		} 
	}
}
