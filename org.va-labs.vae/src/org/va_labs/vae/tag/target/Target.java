/*
 * Created on Aug 15, 2004
 *
 * $Id: Target.java,v 1.1 2004/09/05 00:28:32 mojo_jojo Exp $
 */
package org.va_labs.vae.tag.target;

import java.util.List;

import org.va_labs.vae.tag.Tag;
import org.va_labs.vae.tag.task.Task;

/**
 * @author mojo_jojo
 * Add the information that is a specific to a Target to the Tag base
 * class.
 * This implements a base class that can be implemented in multiple manners.
 */
public abstract class Target extends Tag {

    public Target()
	{
		super();
	}

	/**
	 * Add a dependency at a specific position in the queue.
	 * Index starts at 0 in the queue.
	 * @param index the position of the dependency.
	 * @param dependency the dependency itself. 
	 */
	public abstract void addDependency(int index, String dependency);
	
	/**
	 * Add a dependency at the end of the current queue
	 * @param The dependency to be added.
	 */
	public abstract void addDependency(String dependency);

	/**
	 * Add a task to the target.
	 * @param task to be added.
	 */
	public abstract  void addTask(Task task);
	
	/**
	 * Provides the dependencies of this target.
	 * @return an array of String specifying the dependencies of this target.
	 */
	public abstract Object[] getDependencies();
	
	/**
	 * Provides the description of the target.
	 * Returns NULL in case there is no description for this target.
	 * @return a String holding the description of the target or NULL.
	 */
	public abstract String getDescription();
	
	/**
	 * Provides the name of the property that has to be set prior
	 * to the execution of the target. Returns NULL in case 
	 * there is no such property for this target.
	 * @return a String holding the name of the property or NULL.
	 */
	public abstract String getIf();
	
	/**
	 * Provides the tasks for this target.
	 * @return a List of Task objects.
	 */
	public abstract List getTasks();
	
	/**
	 * Returns the type of this Tag.
	 * @return the type corresponding to TARGET_TAG.
	 * @see org.vae.core.Tag#getType()
	 */
	public int getType() {
		return Tag.TARGET_TAG;
	}
	
	/**
	 * Provides the name of the property that must *not* be
	 * set prior to the execution of the target. Returns NULL in
	 * case there is no such property for this target.
	 * @return a String holding the name of the property or NULL 
	 */
	public abstract String getUnless();
	
	/**
	 * Set the dependencies of this target.
	 * @param dependencies the String containing the dependencies
	 * as comma seperated values.
	 */
	public abstract void setDependencies(String dependencies);
	
	/**
	 * Sets the description of the target.
	 * @param description of the target.
	 */
	public abstract void setDescription(String description);
	
	/**
	 * Sets the name of the property that has to be set prior
	 * to the execution of the target. 
	 * @param property that has to be set before execution. Can
	 * be NULL.
	 */
	public abstract void setIf(String property);
	
	/**
	 * Sets the name of the property that must *not* be set
	 * prior to the execution of the target. 
	 * @param property that must not be set before execution. Can
	 * be NULL.
	 */
	public abstract void setUnless(String property);

}
