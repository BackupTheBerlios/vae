/*
 * Created on Aug 16, 2004
 *
 * $Id: SwtUnidentifiedTag.java,v 1.1 2004/09/05 00:21:50 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.tag.unidentified;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.va_labs.vae.gui.tag.ISwtElement;
import org.va_labs.vae.gui.tag.SwtNestedElement;
import org.va_labs.vae.parser.IllegalAttributeException;
import org.va_labs.vae.tag.NoSuchAttributeException;
import org.va_labs.vae.tag.Tag;
import org.va_labs.vae.tag.NestedElement;

/**
 * @author mojo_jojo 
 * An unidentified tags is a 'unrecognized' tag at the task level (not nested).
 * We add the swt specific information to the core NestedElement base class.
 */
public class SwtUnidentifiedTag implements ISwtElement {
    /**
     * Reference to the core Tag that holds the information we display.
     */
    private Tag tag;
	
    /**
     * SwtElement that represent the parent of this class in the project tree.
     */
	private ISwtElement parent;

	/**
	 * Creates an unidentified Tag at the root level given a 
	 * NestedElement.
	 * @param nestedElement underlying object holding the info for this
	 * SwtUnidentifiedTag.
	 */
	public SwtUnidentifiedTag(Tag unidentifiedElement)
	{
		tag = unidentifiedElement;
	}
	
	/**
	 * Creates an unidentified Tag that will appear under a given element 
	 * of the project tree.
	 * @param nestedElement underlying object holding the info for this
	 * SwtUnidentifiedTag.
	 * @param parent parent of this element in the project tree. 
	 */
	public SwtUnidentifiedTag(Tag unidentifiedElement, ISwtElement parent)
	{
		this(unidentifiedElement);
		this.parent = parent;
	}

	/**
	 * Adds an attribute to the underlaying generic element.
	 * @param attributeName the name of the attribute.
	 * @param attributeValue the value of the attribute.
	 * @throws IllegalAttributeException if the attribute is not legal for
	 * a generic element. (Right now it can't happen).
	 * @see org.vae.gui.swt.components.VaeElement#addAttribute(java.lang.String, java.lang.String)
	 */
	public void addAttribute(String attributeName, String attributeValue) 
	throws IllegalAttributeException
	{
		tag.addAttribute(attributeName, attributeValue);
	}

	/**
	 * Gives the attributes to be displayed in the table for this element.
	 * @see org.vae.gui.swt.components.VaeElement#getAttributes()
	 */
	public Object[] getAttributes()
	{
		return tag.getAttributes().toArray();
	}

	/**
	 * Gives the children elements (nested tags) of this tag.
	 * @see org.vae.gui.swt.components.VaeElement#getChildren()
	 */
	public Object[] getChildren()
	{
		List elements = tag.getNestedElements();
		List children = new ArrayList(elements.size()); 		
		Iterator i = elements.iterator();
		while(i.hasNext()) {
			children.add(new SwtNestedElement((NestedElement)i.next(), this));
		}
		return children.toArray();
	}

	/**
	 * Returns the parent element of this SwtUnidentifiedTag.
	 * @see org.vae.gui.swt.components.VaeElement#getParent()
	 */
	public Object getParent()
	{
		return parent;
	}

	/**
	 * Returns an id, or name if that one exists.
	 * If not we just return undefined as a name 
	 * @see org.vae.gui.swt.components.VaeElement#getText()
	 */
	public String getText()
	{
		StringBuffer name;
		if( tag.getName() != null) {
			name = new StringBuffer(tag.getName());
		} else {
			name = new StringBuffer("Unamed Tag");
		}
		return name.toString();
	}

	/**
	 * Tells wether this element of the tree has nested elements or not.
	 * @see org.vae.gui.swt.components.VaeElement#hasChildren()
	 */
	public boolean hasChildren()
	{
		return tag.getNestedElements().size() != 0;
	}

	/**
	 * Removes an attribute of the element.
	 * @param attributeName the name of the attribute.
	 * @throws NoSuchAttributeException if the attribute doesn't exist.
	 * @see org.vae.gui.swt.components.VaeElement#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String attributeName) 
	throws NoSuchAttributeException
	{
		tag.removeAttribute(attributeName);
	}

	/**
	 * Sets the attributes for underlying unidentified tag.
	 * @param attributeArray an array of TagAttribute objects that carry the info.
	 * There are no requirements regarding the names and values of the attributes.
	 * @throws IllegalAttributeException if one or more of the attributes
	 * is not legal for generic elements. This can't happen right now.
	 * @see org.vae.gui.swt.components.VaeElement#setAttributes(java.lang.Object[])
	 */
	public void setAttributes(Object[] attributeArray) 
	throws IllegalAttributeException 
	{
		List attributeList = new ArrayList(attributeArray.length);
		for(int i=0; i<attributeArray.length; i++) {
			attributeList.add(attributeArray[i]);
		}
		tag.setAttributes(attributeList);
	}
	
	/**
	 * Sets the new name to the model element.
	 * @see org.vae.gui.swt.components.VaeElement#setText(java.lang.String)
	 */
	public void setText(String newName)
	{
		try
		{
			tag.updateAttribute("name", "name", newName);
		}
		catch (NoSuchAttributeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAttributeException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Update an attribute.
	 * @param originalName the original name of the attribute.
	 * @param newName the new name of the attribute.
	 * @param value the value of the attribute.
	 * @see org.vae.gui.swt.components.VaeElement#updateAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void updateAttribute(String originalName, String newName, 
			String value) 
	throws NoSuchAttributeException, IllegalAttributeException
	{
		tag.updateAttribute(originalName, newName, value);
	}
}
