/*
 * Created on Aug 15, 2004
 *
 * $Id: TagAttribute.java,v 1.1 2004/08/17 22:35:25 mojo_jojo Exp $
 */
package org.va_labs.vae.core;

import org.va_labs.vae.core.definitions.Tag;

/**
 * @author mojo_jojo
 * Used to hold information concerning attributes of the build file xml tags.
 */
public class TagAttribute {
    /**
	 * Name of this attribute.
	 */
	private String name;

	/**
	 * Tag we are an attribute for. 
	 */
	private Tag tag;
	
	/**
	 * Value of this attribute.
	 */
	private String value;

	/**
	 * Creates a TagAttribute object with the given name and value.
	 * @param attributeName name of the attribute.
	 * @param attributeValue value of the attribute.
	 */
	public TagAttribute(String attributeName, String attributeValue) {
		name = attributeName;
		value = attributeValue;
	}
	
	public TagAttribute(Tag tag, String attributeName, String attributeValue) {
		this(attributeName, attributeValue);
		this.tag = tag;
	}

	/**
	 * Gets the name of this attribute.
	 * @return the name of this attribute in a String.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the tag we are an attribute of.
	 * @return the tag.
	 */
	public Tag getTag() {
		return tag;
	}
	
	/**
	 * Gets the value of this attribute.
	 * @return the value of this attribute in a String.
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Sets the name of this attribute.
	 * @param attributeName the name of this attribute in a String.
	 */
	public void setName(String attributeName) {
		name = attributeName;
	}
	
	/**
	 * Sets the tag we are an attribute of.
	 * @param tag tag we are an attribute of. 
	 */
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	/**
	 * Sets the value of this attribute.
	 * @param attributeValue the value of this attribute in a String.
	 */
	public void setValue(String attributeValue) {
		value = attributeValue;
	}
	
	/**
	 * Gives a human readable information about the tag attribute.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer("Attribute "+name+
		        " set to "+value);
		return buffer.toString();
	}
}
