/*
 * Created on Aug 15, 2004
 *
 * $Id: Task.java,v 1.1 2005/04/05 02:45:24 mojo_jojo Exp $
 */
package org.vae_labs.vae.tag.task;

import org.vae_labs.vae.tag.Tag;

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
