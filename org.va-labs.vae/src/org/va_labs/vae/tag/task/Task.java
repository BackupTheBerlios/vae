/*
 * Created on Aug 15, 2004
 *
 * $Id: Task.java,v 1.1 2004/09/05 00:28:46 mojo_jojo Exp $
 */
package org.va_labs.vae.tag.task;

import org.va_labs.vae.tag.Tag;

/**
 * @author mojo_jojo
 * Holds the information that is specific of a task.
 * Just returns the adequate type and nothing more. 
 * Shared attributes are specified in the Tag interface.
 * @see org.vae.core.Tag 
 */
public abstract class Task extends Tag {

    public Task() {
		super();
	}
	
	/**
	 * Returns the type of this tag.
	 * @return the type corresponding to TASK_TAG.
	 * @see org.vae.core.Tag#getType()
	 */
	public int getType()
	{
		return Tag.TASK_TAG;
	}
}
