/*
 * Created on Sep 5, 2004
 *
 * $Id: SimpleTarget.java,v 1.1 2004/09/05 00:28:32 mojo_jojo Exp $
 */
package org.va_labs.vae.tag.target;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.va_labs.vae.core.TagAttribute;
import org.va_labs.vae.parser.IllegalAttributeException;
import org.va_labs.vae.tag.Tag;
import org.va_labs.vae.tag.task.Task;

/**
 * @author mojo_jojo
 *
 * First and simple extension of the Target abstract class.
 */
public class SimpleTarget extends Target {

    /**
	 * Legal attributes for a Target
	 */
	private static String[] legalAttributes = {"name", "depends", "if", 
			"unless", "description"};
	
	/**
	 * Dependencies that has to be satisfied 
	 * prior to considering this target.
	 */
	private List dependencies;
	
	/**
	 * Description associated to the target
	 * (Can be null).
	 */
	private StringBuffer description;

	/**
	 * Property that has to be set prior to the 
	 * execution of the target. 
	 */
	private StringBuffer ifProperty;
	
	/**
	 * The tasks that are associated with this
	 * target. (to be executed if conditions
	 * are satisfied).
	 */
	private List tasks;
	
	/**
	 * Property that must *not* be set prior
	 * to the execution of the target.
	 */
	private StringBuffer unlessProperty;

	/**
	 * Create a SimpleTarget with nothing set.
	 */
	public SimpleTarget()
	{
		super();
		dependencies = new ArrayList();
		tasks = new ArrayList();
	}

	/**
	 * Creates a SimpleTarget with the given name.
	 * @param name of the Target. 
	 */
	public SimpleTarget(String name)
	{
		this();
		this.name = new StringBuffer(name);
	}
	
	public SimpleTarget(Tag targetTag)
	{
		this(targetTag.getName());
		try {
			setAttributes(targetTag.getAttributes());
		} catch (IllegalAttributeException e) {
			System.out.println("Illegal target attribute detected");
		}
		setNestedElements(targetTag.getNestedElements());
	}

	/**
	 * Adds an attribute to the target.
	 * @param name of the attribute
	 * @param value of the attribute
	 * @throws IllegalAttributeException if the attribute is not legal
	 * Legal attributes are name (mandatory), depends, if, unless, 
	 * description.
	 * @see org.vae.core.Tag#addAttribute(java.lang.String, java.lang.String)
	 */
	public void addAttribute(String name, String value) 
		throws IllegalAttributeException
	{
		verifyAttribute(name, value);
		TagAttribute attribute = new TagAttribute(this, name, value);
		attributes.add(attribute);
	}

	/**
	 * Adds an attribute to the Target at a given position.
	 * @param name name of the attribute.
	 * @param value value of the attribute.
	 * @param position position of the attribute in the attribute list.
	 * @throw IllegalAttributeException if the attribute is not a legal 
	 * attribute. 
	 * Legal attributes are name (mandatory), depends, if, unless, 
	 * description (cf. ant user manual). 
	 * @see org.vae.core.Tag#addAttribute(java.lang.String, java.lang.String, int)
	 */
	public void addAttribute(String name, String value, int position) 
		throws IllegalAttributeException
	{
		verifyAttribute(name, value);
		TagAttribute attribute = new TagAttribute(this, name, value);
		attributes.add(position, attributes);
	}

	/**
	 * Add a dependency at a specific position in the queue.
	 * Index starts at 0 in the queue.
	 * @param index the position of the dependency.
	 * @param dependency the dependency itself. 
	 */
	public void addDependency(int index, String dependency)
	{
		dependencies.add(index, dependency);
	}
	/**
	 * Add a dependency at the end of the current queue
	 * @param The dependency to be added.
	 */
	public void addDependency(String dependency)
	{
		dependencies.add(dependency);
	}
	
	/**
	 * Adds a task to the target.
	 * @param task to be added.
	 * @see org.vae.core.Target#addTask(org.vae.core.Task)
	 */
	public void addTask(Task task) 
	{
		tasks.add(task);
	}

	/**
	 * Provides the dependencies of this target.
	 * @see org.vae.core.Target#getDependencies()
	 */
	public Object[] getDependencies()
	{
		return dependencies.toArray();
	}
	
	/**
	 * @return the description for this target.
	 *  can return null.
	 * @see org.vae.core.Target#getDescription()
	 */
	public String getDescription() 
	{
		if(description != null) {
			return description.toString();
		} else {
			return null;
		}
	}

	/**
	 * @see org.vae.core.Target#getIf()
	 * Can return null.
	 */
	public String getIf() {
		if(ifProperty != null) {
			return ifProperty.toString();
		} else {
			return null;
		}
	}

	/**
	 * @see org.vae.core.Target#getTasks()
	 * @return the tasks for this target. 
	 */
	public List getTasks() 
	{
		return tasks;
	}

	/**
	 * @see org.vae.core.Target#getUnless()
	 * Can return null.
	 */
	public String getUnless() 
	{
		if(unlessProperty != null) {
			return unlessProperty.toString();
		} else {
			return null;
		}
	}
	
	/**
	 * Set the dependencies of this target.
	 * Nullify previous dependencies if any.
	 * @param dependencies the String containing the dependencies
	 * as comma seperated values.
	 */
	public void setDependencies(String dependencies)
	{
		this.dependencies = new ArrayList();
		
		String[] deps = dependencies.split(",");
		for(int i=0, length = deps.length; i < length; i++) {
			addDependency(deps[i]);
		}
	}

	/** 
	 * @see org.vae.core.Target#setDescription(java.lang.String)
	 * you can send a null value.
	 */
	public void setDescription(String description) 
	{
		this.description = new StringBuffer(description);
	}

	/**
	 * @see org.vae.core.Target#setIf(java.lang.String)
	 * Can be set to null.
	 */
	public void setIf(String property) 
	{
		ifProperty = new StringBuffer(property);
	}

	/**
	 * Sets the unless attribute.
	 * @see org.vae.core.Target#setUnless(java.lang.String)
	 * Can be set to null.
	 */
	public void setUnless(String property) 
	{
		unlessProperty=new StringBuffer(property);
	}
	
	/**
	 * @return a string containing various debug statements. 
	 */
	public String toString()
	{
		StringBuffer info = new StringBuffer("   o Target : "+ name + "\n");
		
		if(description == null) {
			info.append("      No description available for this target.\n");
		} else {
			info.append("      " + description + "\n");
		}
		if(ifProperty != null) {
			info.append("      Executed only if "+ ifProperty +" is set\n");
		}
		if(unlessProperty != null) {
			info.append("      Not executed if "+ unlessProperty + " is set\n");
		}
		if(tasks.size()!=0) {
			info.append("      Tasks currently associated with this target : \n");
			Iterator i = tasks.iterator();
			while(i.hasNext()) {
				Task t=(Task) i.next();
				info.append("         o " + t.getName() + "\n");
			}
		}
		
		return info.toString();
	}
	
	/**
	 * Verifies that the attributes are legal for target and calls the 
	 * appropriate methods.
	 * @param name name of the attribute that is set/added.
	 * @param value value of the attribute that is set/added.
	 * @throws IllegalAttributeException if the attribute is not
	 * legal. 
	 * Legal attributes are name (mandatory), depends, if, unless, 
	 * description.
	 */
	protected void verifyAttribute(String name, String value) 
	throws IllegalAttributeException
	{
		if(name.equalsIgnoreCase("name")) {
			setName(value);
		} else if(name.equalsIgnoreCase("description")) {
			setDescription(value);
		} else if(name.equalsIgnoreCase("depends")) {
			setDependencies(value);
		} else if(name.equalsIgnoreCase("if")) {
			setIf(value);
		} else if(name.equalsIgnoreCase("unless")) {
			setUnless(value);
		} else {
			throw new IllegalAttributeException(
					name, this.name.toString(), legalAttributes);
		}
	}
	
	/**
	 * All tags are legal, Just keeps the tasks in a special place, other 
	 * nested tags will appear as unknown elements in the project tree.
	 * @see org.vae.core.Tag#setKeyTags(org.vae.core.Tag)
	 */
	protected void verifyTag(Tag tag)
	{
		// Keeps specific repository in line with added info
		int tagType = tag.getType();
		if( tagType == Tag.TASK_TAG) {
			addTask((Task) tag);
		}
	}
}
