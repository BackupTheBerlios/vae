/*
 * Created on Aug 13, 2004
 *
 * $Id: SwtElement.java,v 1.1 2004/08/17 22:41:51 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.views.elements;

import org.va_labs.vae.exceptions.NoSuchAttributeException;

import org.va_labs.vae.exceptions.IllegalAttributeException;

/**
 * @author mojo_jojo
 * Interface that summarizes what methods needs to be implemented by the
 * swt elements of the project tree.
 * Typically the elements that are gui dependant will take the
 * Vae internal style + this interface that need to be redefined for each
 * different implementation.
 */
public interface SwtElement {
    /**
	 * Adds an attribute to the underlying SwtElement.
	 * @param attribute the attribute to be added
	 * @throws NoSuchAttributeException if it's an illegal attribute for the 
	 * element.
	 */
	public void addAttribute(String attributeName, String attributeValue) 
			throws IllegalAttributeException;
	/**
	 * Get the attributes of the vae elements.
	 * The attributes will be listed in the attribute table underneath the 
	 * project tree. 
	 * @return the attributes in an Object array.
	 */
	public Object[] getAttributes();
	
	/**
	 * Get the childern of the vae elements in the project tree.
	 * These will be displayed if the item is "opened". 
	 * @return the children elements in an Object array.
	 */
	public Object[] getChildren();
	
	/**
	 * Indicates wether the vae elements has children or not in the project 
	 * tree.
	 * @return true if the item has children in this tree.
	 */
	public boolean hasChildren();
	
	/**
	 * Return the parent element in the project tree of this vae element.
	 * Note : root objects return null here.
	 * @return the parent element as an object.
	 */
	public Object getParent();
	
	/**
	 * Indicates the text to be displayed for this item in the project tree. 
	 * This is used by the TreeLabelProvider.
	 * @return the String to be displayed for this element.
	 * @throws NoSuchAttributeException if the attribute doesn't exist.
	 */
	public String getText();
	
	public void removeAttribute(String attributeName) 
		throws NoSuchAttributeException;
	
	/**
	 * Sets the attributes of the VaeElement.
	 * Used to update the underlaying object (Property, Target, Task or 
	 * UniditenfiedTag).
	 * @param attributes the new attributes for this VaeElement.
	 */
	public void setAttributes(Object[] attributes)
		throws IllegalAttributeException;
	
	/**
	 * This method is called to indicated the value displayed in the 
	 * project tree has been modified by the user. The Vae Element 
	 * has to update the model so it uses the new value.
	 */
	public void setText(String newName);
	
	/**
	 * Updates a specific attribute of the VaeElement.
	 * @param originalName the original name of the attribute (it might have 
	 * been changed at well).
	 * @param newName the new name of the attribute (may be the same as 
	 * originalName).
	 * @param value Value of the attribute (may be the same as the 
	 * old value).
	 * @throws NoSuchAttributeException if the attribute doesn't exist.
	 */
	public void updateAttribute(String originalName, String newName, 
	        String value) 
		throws NoSuchAttributeException, IllegalAttributeException;

}
