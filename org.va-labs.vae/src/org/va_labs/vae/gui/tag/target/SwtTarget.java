/*
 * Created on Aug 15, 2004
 *
 * $Id: SwtTarget.java,v 1.1 2004/09/05 00:21:50 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.tag.target;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.va_labs.vae.core.TagAttribute;
import org.va_labs.vae.gui.tag.ISwtElement;
import org.va_labs.vae.gui.tag.property.SwtProperty;
import org.va_labs.vae.gui.tag.task.SwtTask;
import org.va_labs.vae.gui.tag.unidentified.SwtUnidentifiedTag;
import org.va_labs.vae.parser.IllegalAttributeException;
import org.va_labs.vae.tag.NoSuchAttributeException;
import org.va_labs.vae.tag.Tag;
import org.va_labs.vae.tag.NestedElement;
import org.va_labs.vae.tag.property.Property;
import org.va_labs.vae.tag.target.Target;
import org.va_labs.vae.tag.task.Task;


/**
 * @author mojo_jojo
 * Adds Rcp specific methods and variable to the core Target class.
 */
public class SwtTarget implements ISwtElement {
    /**
	 * Reference to the target which hold the info we use.
	 */
	private Target target;
	
	/**
	 * Creates a Swt Target that gets the information from a Target and keeps a 
	 * reference to the project given as parameters.
	 * @param the target that holds the necessary information. 
	 * @param the project that references this target.
	 */
	public SwtTarget(Target t)
	{
		target = t;
	}

	/**
	 * Add an attribute to this target.
	 * @param attributeName the name of the attribute.
	 * @param attributeValue the value of the attribute.
	 * @throws IllegalAttributeException if the attribute is not legal for
	 * a Target.
	 * @see org.vae.gui.swt.components.VaeElement#addAttribute(java.lang.String, java.lang.String)
	 */
	public void addAttribute(String attributeName, String attributeValue)
	throws IllegalAttributeException
	{
		target.addAttribute(attributeName, attributeValue);
	}

	/**
	 * Add an attribute to the target.
	 * @param attribute TagAttribute object holding the info about the 
	 * attribute to be added.
	 * @throws an IllegalAttributeException if the attribute is not legal
	 * for targets.
	 * @see org.vae.gui.swt.components.VaeElement#addAttribute(org.vae.gui.swt.components.TagAttribute)
	 */
	public void addAttribute(TagAttribute attribute) 
			throws IllegalAttributeException
	{
		target.addAttribute(attribute.getName(), attribute.getValue());
	}

	/**
	 * Returns the attributes for this target.
	 * @see org.vae.gui.swt.components.VaeElement#getAttributes()
	 */
	public Object[] getAttributes()
	{	
		return target.getAttributes().toArray();
	}
	
	public Object[] getChildren()
	{		
		List tags = target.getNestedElements();
		
		if(tags != null) {
			List children = new ArrayList(tags.size());
			Iterator i = tags.iterator();
			while(i.hasNext()) {
				Tag nestedTag = (Tag) i.next();
				int tagType = nestedTag.getType();
				if(tagType == Tag.TASK_TAG) {
					SwtTask task = new SwtTask(this, (Task) nestedTag);
					children.add(task);
				} else if(tagType == Tag.PROPERTY_TAG) {
					SwtProperty property = new SwtProperty((Property) nestedTag);
					children.add(property);
				} else {
					SwtUnidentifiedTag tag = 
						new SwtUnidentifiedTag((NestedElement) nestedTag, this);
					children.add(tag);
				}
			}
			return children.toArray();
		}
		return new Object[0];
	}
	
	public String getName()
	{
		return target.getName();
	}
	
	/**
	 * We return null as a target is a root element of the Project Tree.
	 * @see org.vae.gui.swt.components.VaeElement#getParent()
	 */
	public Object getParent()
	{
		return null;
	}

	/**
	 * The label that needs to be associated to the tree entry.
	 * @see org.vae.gui.swt.components.VaeElement#getText()
	 */
	public String getText() 
	{
		return getName();
	}
	
	public boolean hasChildren()
	{
		return target.getNestedElements().size() != 0;
	}

	/**
	 * Removes an attribute from the target.
	 * @param attributeName the name of the attribute to be removed.
	 * @throws NoSuchAttributeException if the attribute to be removed does 
	 * not  exist.
	 * @see org.vae.gui.swt.components.VaeElement#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String attributeName) 
			throws NoSuchAttributeException
	{
		target.removeAttribute(attributeName);		
	}
	
	/**
	 * Sets the attributes for this target.
	 * @param attributes an array of TagAttribute objects.
	 * the recognized attributes for a target are :
	 * <li> description </li>
	 * <li>if</li>
	 * <li>unless</li>
	 * @throws IllegalAttributeException if one or more of the attributes
	 * are illegal for a Target.
	 * @see org.vae.gui.swt.components.VaeElement#setAttributes(java.lang.Object[])
	 */
	public void setAttributes(Object[] attributes) 
	throws IllegalAttributeException
	{
		List attributeList = new ArrayList(attributes.length);
		for(int i=0; i<attributes.length; i++) {
			attributeList.add(attributes[i]);
		}
		target.setAttributes(attributeList);
	}
	
	public void setName(String name)
	{
		target.setName(name);
	}

	/**
	 * Sets the new name of the target.
	 * @see org.vae.gui.swt.components.VaeElement#setText(java.lang.String)
	 */
	public void setText(String newName)
	{
		target.setName(newName);
	}

	/**
	 * Updates an attribute for this target.
	 * @param originalName the original name of the attribute.
	 * @param newName the new name of the attribute.
	 * @param value the value of the attribute.
	 * @throws IllegalAttributeException if the new attribute is illegal 
	 * for a Target.
	 * @throws NoSuchAttributeException if the attribute doesn't exist.
	 * @see org.vae.gui.swt.components.VaeElement#updateAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void updateAttribute(String originalName, String newName, 
			String value) 
	throws NoSuchAttributeException, IllegalAttributeException
	{
		target.updateAttribute(originalName, newName, value);
	}
}
