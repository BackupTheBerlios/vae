/*
 * Created on Aug 15, 2004
 *
 * $Id: SwtProperty.java,v 1.1 2005/04/05 02:45:26 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.tag.property;

import java.util.Iterator;
import java.util.List;

import org.vae_labs.vae.core.TagAttribute;
import org.vae_labs.vae.gui.tag.ISwtElement;
import org.vae_labs.vae.gui.tag.SwtNestedElement;
import org.vae_labs.vae.parser.IllegalAttributeException;
import org.vae_labs.vae.tag.NestedElement;
import org.vae_labs.vae.tag.NoSuchAttributeException;
import org.vae_labs.vae.tag.property.Property;

/**
 * @author mojo_jojo
 * Adds rcp specific variables and method to the core property base class.
 */
public class SwtProperty implements ISwtElement {

    /**
     * Reference to the core Property class.
     */
    private Property property;
    
    /**
     * SwtElement that is the parent element of this property (if any) in
     * the displayed tree.
     */
	private ISwtElement parent;
	
	/**
	 * Creates a SwtProperty given a Property object.
	 * This property is project wise : it will be displayed with no parent
	 * element.
	 * @param property the underlaying property of the SwtProperty.
	 */
	public SwtProperty(Property property)
	{
		this.property = property;
	}
	
	/**
	 * Creates a SwtProperty given a Property object.
	 * This property will be displayed under the given parent parameter.
	 * @param property the underlaying property of the SwtProperty.
	 * @param parent the parent element of this property in the project 
	 * tree.
	 */
	public SwtProperty(Property property, ISwtElement parent)
	{
		this(property);
		this.parent = parent;
	}

	/**
	 * Adds an attribute to this property.
	 * @param attributeName name of the attribute to be added.
	 * @param attributeValue value of the attribute to be added.
	 * @throws IllegalAttributeException if the attribute is not legal for 
	 * a Property. 
	 * @see org.vae.gui.swt.components.VaeElement#addAttribute(java.lang.String, java.lang.String)
	 */
	public void addAttribute(String attributeName, String attributeValue) 
	throws IllegalAttributeException
	{
		property.addAttribute(attributeName, attributeValue);
	}

	/**
	 * Gets the TagAttributes to be displayed in the attribute table.
	 * @see org.vae.gui.swt.components.VaeElement#getAttributes()
	 */
	public Object[] getAttributes()
	{
		return property.getAttributes().toArray();
	}

	/**
	 * Returns the children of this property.
	 * @return an array of SwtUnidentifiedTag objects corresponding to the
	 * nested tags for this property.  
	 * @see org.vae.gui.swt.components.VaeElement#getChildren()
	 */
	public Object[] getChildren()
	{
		List nestedTags = property.getNestedElements();
		Object[] children = new Object[nestedTags.size()];
		Iterator i = nestedTags.iterator();
		int j=0;
		while(i.hasNext()) {
			SwtNestedElement tag = 
				new SwtNestedElement((NestedElement) i.next(), this);
			children[j++] = tag;
		}
		return children;
	}

	/**
	 * We return the parent element of this property.
	 * @return the parent or null if it is a root element in the project 
	 * tree.
	 * @see org.vae.gui.swt.components.VaeElement#getParent()
	 */
	public Object getParent()
	{
		return parent;
	}

	/**
	 * Returns the name of the property to be displayed in the tree.
	 * @see org.vae.gui.swt.components.VaeElement#getText()
	 */
	public String getText()
	{
		String name = property.getName();
		if( name == null) {
			name = new String("Anonymous property");
		}
		return name;
	}

	/**
	 * A property cannot have children in the tree, so we always return false. 
	 * @see org.vae.gui.swt.components.VaeElement#hasChildren()
	 */
	public boolean hasChildren()
	{
		return property.getNestedElements().size() != 0;
	}

	/**
	 * Remove an attribute of this property.
	 * @param attributeName name the attribute to be removed.
	 * @throws NoSuchAttributeException if the attribute doesn't exists.
	 * @see org.vae.gui.swt.components.VaeElement#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String attributeName) 
	throws NoSuchAttributeException
	{
		property.removeAttribute(attributeName);
	}

	/**
	 * Sets the attribute of this property.
	 * @param attributes an array of TagAttribute objects with the attributes to be set.
	 * Authorized attributes are : value, resource, refId, prefix, classpath, classpathref,
	 * envprefix, file, location
	 */
	public void setAttributes(Object[] attributes) 
	throws IllegalAttributeException
	{
		for(int i=0; i < attributes.length; i++) {
			TagAttribute tagAttribute = (TagAttribute) attributes[i];
			property.addAttribute(tagAttribute.getName(), 
					tagAttribute.getValue());
		}
	}
	
	/**
	 * Sets the new name of the property.
	 * @see org.vae.gui.swt.components.VaeElement#setText(java.lang.String)
	 */
	public void setText(String newName)
	{
		property.setName(newName);
	}

	/**
	 * Updates an attribute of this property.
	 * @param originaName the original name of the attribute.
	 * @param newName the new name of the attribute.
	 * @param value the value of the attribute.
	 * @throws IllegalAttributeException if the new attribute is not legal
	 * for a Property.
	 * @throws NoSuchAttributeException if the original attribute does
	 * not exist.
	 * @see org.vae.gui.swt.components.VaeElement#updateAttribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void updateAttribute(String originalName, String newName, 
			String value) 
	throws NoSuchAttributeException, IllegalAttributeException
	{
		property.updateAttribute(originalName, newName, value);
	}
}
