/*
 * Created on Aug 15, 2004
 *
 * $Id: SwtTask.java,v 1.1 2004/09/05 00:21:50 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.tag.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.va_labs.vae.gui.tag.ISwtElement;
import org.va_labs.vae.gui.tag.SwtNestedElement;
import org.va_labs.vae.gui.tag.target.SwtTarget;
import org.va_labs.vae.parser.IllegalAttributeException;
import org.va_labs.vae.tag.NestedElement;
import org.va_labs.vae.tag.NoSuchAttributeException;
import org.va_labs.vae.tag.task.Task;

/**
 * @author mojo_jojo
 * This class adds rcp specific methods and variables to the core Task 
 * definition.
 */
public class SwtTask implements ISwtElement {

    /**
	 * Reference to the SwtTarget that is the parent of this task in the tree.
	 */
	private SwtTarget target;
	
	/**
	 * Reference to the Task that holds the information about this tree element.
	 */
	private Task task;
	
	public SwtTask(SwtTarget target, Task task)
	{
		this.target = target;
		this.task = task;
	}

	/**
	 * Add an attribute to this task.
	 * @param attributeName name of the attribute.
	 * @param attributeValue value of the attribute.
	 * @throws IllegalAttributeException if the attribute is not legal for a task.
	 * @see org.vae.gui.swt.components.VaeElement#addAttribute(java.lang.String, java.lang.String)
	 */
	public void addAttribute(String attributeName, String attributeValue) 
	throws IllegalAttributeException
	{
		task.addAttribute(attributeName, attributeValue);
	}

	/**
	 * Returns the attributes for this task.
	 * @see org.vae.gui.swt.components.VaeElement#getAttributes()
	 */
	public Object[] getAttributes()
	{
		return task.getAttributes().toArray();
	}
	
	/**
	 * Return the children elements of this task in the project tree.
	 * @return an array of objects that are children of this task. 
	 * @see org.vae.gui.swt.components.VaeElement#getChildren()
	 */
	public Object[] getChildren()
	{		
		List elements = task.getNestedElements();
		
		if( elements != null ) {
			List children = new ArrayList(elements.size());
			Iterator i = elements.iterator();
			for(int j=0; i.hasNext(); j++) {
				SwtNestedElement element = new SwtNestedElement((NestedElement) i.next(), this);
				children.add(element);
			}
			return children.toArray();
		}
		return new Object[0];
	}
	
	/**
	 * Returns the name of the task.
	 * @return the name of the task in a String.
	 */
	public String getName()
	{
		return task.getName();
	}
	
	/**
	 * Return the parent object of this element in the project tree.
	 * @return an object that is the parent object of this element.
	 * @see org.vae.gui.swt.components.VaeElement#getParent()
	 */
	public Object getParent()
	{
		return target;
	}

	/**
	 * The label that needs to be associated to the tree entry.
	 * @see org.vae.gui.swt.components.VaeElement#getText()
	 */
	public String getText() 
	{
		return task.getName();
	}
	
	/**
	 * Indicates wether this task has children or not.
	 * @see org.vae.gui.swt.components.VaeElement#hasChildren()
	 */
	public boolean hasChildren()
	{
		return task.getNestedElements().size() != 0;
	}
	
	/**
	 * Remove an attribute of this task
	 * @param attributeName the name of the attribute to be removed.
	 * @throws NoSuchAttribute if the attribute doesn't exist for this task.
	 * @see org.vae.gui.swt.components.VaeElement#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String attributeName)
	throws NoSuchAttributeException
	{
		task.removeAttribute(attributeName);
	}
	
	/**
	 * Set the attributes of the underlaying task.
	 * @param attributes an array of TagAttribute objects that holds the 
	 * info.
	 * @throws IllegalAttributeException if one or more of the attribute 
	 * is illegal for a Task.
	 * @see org.vae.gui.swt.components.VaeElement#setAttributes(java.lang.Object[])
	 */
	public void setAttributes(Object[] attributes) 
	throws IllegalAttributeException
	{
		List attributeList = new ArrayList(attributes.length);
		for(int i =0; i< attributes.length; i++) {
			attributeList.add(attributes[i]);
		}
		task.setAttributes(attributeList);
	}
	
	/**
	 * Sets the name of the task.
	 * @param name the new name of the task.
	 */
	public void setName(String name)
	{
		task.setName(name);
	}

	/**
	 * Sets the name of this task to the new name chosen by the user.
	 * @see org.vae.gui.swt.components.VaeElement#setText(java.lang.String)
	 */
	public void setText(String newName)
	{
		task.setName(newName);
	}

	/**
	 * Updates an attribute for this task.
	 * @param originalName the original name of the attribute.
	 * @param newName the new name of the attribute.
	 * @param value the value of the attribute.
	 * @throws NoSuchAttributeException if the attribute does not exists.
	 * @throws IllegalAttributeException if the attribute is illegal for a
	 * task.
	 * @see org.vae.gui.swt.components.VaeElement#updateAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void updateAttribute(String originalName, String newName, 
			String value)
	throws NoSuchAttributeException, IllegalAttributeException
	{
		task.updateAttribute(originalName, newName, value);
	}
}
