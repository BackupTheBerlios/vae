/*
 * Created on Aug 17, 2004
 *
 * $Id: TaskDef.java,v 1.1 2005/04/05 02:45:25 mojo_jojo Exp $
 */
package org.vae_labs.vae.tag.taskdef;

import org.vae_labs.vae.tag.Tag;

/**
 * @author mojo_jojo
 * Holds the information regarding a taskdef ant task.
 * More informations about taskdef ant tasks at
 * http://ant.apache.org/manual/CoreTasks/taskdef.html
 */
public abstract class TaskDef extends Tag {

    /**
	 * Simply calls the Tag constructor.
	 */
	public TaskDef()
	{
		super();
	}
	
	/**
	 * Used to get the name of the class of this Taskdef.
	 * @return
	 */
	public abstract String getClassName();
	
	public abstract String getClassPath();
	
	public abstract String getClassPathRef();
	
	public abstract String getFile();
	
	public abstract String getLoaderReference();
	
	public abstract String getResource();
	
	public abstract String getTaskdefName();
	
	/**
	 * Returns the type of this tag.
	 * @return the type corresponding to TASKDEF_TAG. 
	 * @see org.vae.core.Tag#getType()
	 */
	public int getType()
	{
		return Tag.TASKDEF_TAG;
	}
	
	public abstract void setClassName(String className);
	
	public abstract void setClassPath(String classPath);
	
	public abstract void setClassPathRef(String classPathRef);
	
	public abstract void setFile(String file);
	
	public abstract void setLoaderReference(String loaderReference);
	
	public abstract void setResource(String resource);
	
	public abstract void setTaskdefName(String taskName);

}
