/*
 * Created on Aug 15, 2004
 *
 * $Id: SwtNestedElement.java,v 1.1 2005/04/05 02:45:26 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.vae_labs.vae.core.TagAttribute;
import org.vae_labs.vae.parser.IllegalAttributeException;
import org.vae_labs.vae.tag.NestedElement;
import org.vae_labs.vae.tag.NoSuchAttributeException;

/**
 * @author mojo_jojo
 * This is used to add rcp specific methods and variable to the core
 * NestedElement class.
 */
public class SwtNestedElement implements ISwtElement {
    /**
	 * Reference to the nested element that holds the info for this
	 * swt component.
	 */
	private NestedElement element;
	
	/**
	 * Parent of this nested element.
	 */
	private ISwtElement parent;
	
	/**
	 * Creates a swt nested element from a core vae nested element.
	 * Initializes the list of children for the current element.
	 * @param nested the core vae element used as a base.
	 */
	public SwtNestedElement(NestedElement nested, ISwtElement parent)
	{
		element = nested;
		this.parent = parent;
	}

	/**
	 * Add an attribute for this nested element.
	 * @param attributeName name of the attribute.
	 * @param attributeValue value of the attribute.
	 * @throws IllegalAttributeException if the attribute is not legal for this element.
	 * @see org.vae.gui.swt.components.VaeElement#addAttribute(java.lang.String, java.lang.String)
	 */
	public void addAttribute(String attributeName, String attributeValue) 
	throws IllegalAttributeException
	{
		element.addAttribute(attributeName, attributeValue);
	}
	
	/**
	 * Add an attribute to the nested element.
	 * Does not throw any exception as there is no 
	 * @param attribute attribute to be added to the element
	 */
	public void AddAttribute(TagAttribute attribute) 
		throws NoSuchAttributeException
	{
		
	}

	/**
	 * Returns the attributes for this nested elements.
	 * @return the attributes in an array of TagAttributes.
	 * @see org.vae.gui.swt.components.VaeElement#getAttributes()
	 * @see org.vae.core.TagAttribute
	 */
	public Object[] getAttributes()
	{
		List attributeList = element.getAttributes();
		if(attributeList != null) {
			return attributeList.toArray();
		} else {
			return new Object[0];
		}
	}
	
	/**
	 * Returns the children of this element.
	 * 
	 * @see org.vae.gui.swt.components.VaeElement#getChildren()
	 */
	public Object[] getChildren()
	{
		List list = element.getNestedElements();
		Object[] children = new Object[list.size()];
		Iterator i = list.iterator();
		for(int j =0; i.hasNext() ; j++) {
			SwtNestedElement element = new SwtNestedElement((NestedElement)i.next(), this);
			children[j] = element;
		}
		return children;
	}

	/**
	 * Return the parent of this element in the project tree.
	 * @see org.vae.gui.swt.components.VaeElement#getParent()
	 */
	public Object getParent()
	{
		return parent;
	}

	/**
	 * Returns the name of this element in a String
	 * @return a String containing the name of this element. 
	 * @see org.vae.gui.swt.components.VaeElement#getText()
	 */
	public String getText()
	{
		return element.getName();
	}

	/**
	 * Tells wether or not this element has children elements in the project tree.
	 * @see org.vae.gui.swt.components.VaeElement#hasChildren()
	 */
	public boolean hasChildren()
	{
		return element.getNestedElements().size() != 0;
	}

	/**
	 * Removes an attribute from the attribute list.
	 * @param attributeName the name of the attribute to be deleted.
	 * @throws NoSuchAttributeException if the attribute doesn't exist.
	 * @see org.vae.gui.swt.components.VaeElement#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String attributeName) 
	throws NoSuchAttributeException
	{
		element.removeAttribute(attributeName);
	}
	
	/**
	 * Sets the attributes of the underlying Nested element.
	 * @param attributes an array of TagAttribute objects.
	 * There are no requirements on what are the possible names and values
	 * of the attributes.
	 * @throws IllegalAttributeException if one or more attribute is not
	 * legal. 
	 * @see org.vae.gui.swt.components.VaeElement#setAttributes(java.lang.Object[])
	 */
	public void setAttributes(Object[] attributes)
	throws IllegalAttributeException
	{
		List attributeList= new ArrayList(attributes.length);
		for(int i=0; i<attributes.length; i++) {
			attributeList.add(attributes[i]);
		}
		element.setAttributes(attributeList);
	}

	/**
	 * Sets the new name of this nested element.
	 * @see org.vae.gui.swt.components.VaeElement#setText(java.lang.String)
	 */
	public void setText(String newName)
	{
		element.setName(newName);
	}

	/**
	 * Updates an attribute.
	 * @param originalName original name of the attribute before the 
	 * change.
	 * @param newName the new name of the attribute (may be the same as 
	 * before).
	 * @param value the value of the attribute (may be the same as 
	 * before).
	 * @throws NoSuchAttributeException if the original attribute doesn't 
	 * exist.
	 * @throws IllegalAttributeException if the new attribute is not legal.
	 * @see org.vae.gui.swt.components.VaeElement#updateAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void updateAttribute(String originalName, String newName, 
			String value) 
	throws NoSuchAttributeException, IllegalAttributeException
	{
		element.updateAttribute(originalName, newName, value);
	}
}
