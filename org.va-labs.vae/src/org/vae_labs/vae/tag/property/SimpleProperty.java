/*
 * Created on Sep 5, 2004
 *
 * $Id: SimpleProperty.java,v 1.1 2005/04/05 02:45:25 mojo_jojo Exp $
 */
package org.vae_labs.vae.tag.property;

import org.vae_labs.vae.parser.IllegalAttributeException;
import org.vae_labs.vae.tag.Tag;

/**
 * @author mojo_jojo
 *
 * First implementation of the Property interface.
 */
public class SimpleProperty extends Property {

    /**
	 * Legal values for a SimpleProperty in an array.
	 */
	private static final String[] legalAttributes= new String [] 
		{ "name", "value",  "location", "refid", "resource", "file", 
		   "environment", "classpath", "prefix"};
	
	/**
	 * The classpath to use when looking up a resource.
	 */
	private StringBuffer classpath;
	
	/**
	 * the classpath to use when looking up a resource, given as 
	 * reference to a path defined elsewhere..
	 */
	private StringBuffer classpathRef;
	
	/**
	 * The prefix to use when retrieving environment variables.
	 */
	private StringBuffer envPrefix;
	
	/**
	 * The filename of the property file.
	 */
	private StringBuffer file;
	
	/**
	 * Holds the location of the property.
	 */
	private StringBuffer location;
	
	/**
	 * Prefix to apply to properties loaded using file  or resource. 
	 * A "." is appended to the prefix if not specified.
	 */
	private StringBuffer prefix;
	
	/**
	 * Holds a reference to another property.
	 */
	private StringBuffer refId;
	
	/**
	 * The resource name of the property file.
	 */
	private StringBuffer resource;
	
	/**
	 * Holds the value of the property.
	 */
	private StringBuffer value;
	
	/**
	 * Default contructor. 
	 * Doesn't do anything as there is no really
	 * mandatory parameter in a property (it's more a set
	 * of parameters).
	 */
	public SimpleProperty()
	{
		super();
		name = new StringBuffer("Anonymous Property");
	}

	public SimpleProperty(Tag propertyTag)
	{
		this();
		String name = propertyTag.getName();
		if(name != null) {
			setName(name);
		}
		try {
			setAttributes(propertyTag.getAttributes());
		} catch (IllegalAttributeException e) {
			// TODO Should send some sort of warning to the user.
			System.out.println("Invalid property attribute detected.");
		}
		setNestedElements(propertyTag.getNestedElements());
	}	

	/**
	 * Gets the value of the classpath.
	 * Can be null.
	 * @return the classpath in a String.
	 */
	public String getClasspath() 
	{
		if(classpath != null) {
			return classpath.toString();
		} else {
			return null;
		}
	}

	/**
	 * Gets the classpath reference value.
	 * Can be null.
	 * @return the classpath reference.
	 */
	public String getClasspathRef() 
	{
		if(classpathRef != null) {
			return classpathRef.toString();
		} else {
			return null;
		}
	}

	/**
	 * Gets the Environment prefix used to retrieve 
	 * environment variables.
	 * Can be null.
	 * @return the Environment prefix.
	 */
	public String getEnvPrefix() 
	{
		if(envPrefix != null) {
			return envPrefix.toString();
		} else {
			return null;
		}
	}

	/**
	 * Get the filename of the property file to
	 * be loaded.
	 * Can be null.
	 * @return the filename in a String.
	 */
	public String getFile() 
	{
		if(file != null) {
			return file.toString();
		} else {
			return null;
		}
	}

	/**
	 * Gets the location of this property.
	 * Can return a null value.
	 * @see org.vae.core.Property#getLocation()
	 */
	public String getLocation() 
	{
		if(location != null) {
			return location.toString();
		} else {
			return null;
		}
	}

	/**
	 * Gets the prefix to be applied to properties loaded 
	 * using file or resource.
	 * Can be null.
	 * @return the prefix in a String 
	 */
	public String getPrefix() 
	{
		if(prefix != null) {
			return prefix.toString();
		} else {
			return null;
		}
	}

	/**
	 * Gets the reference to another property.
	 * Can be null.
	 * @return the reference in a String. 
	 */
	public String getRefId() 
	{
		if(refId != null) {
			return refId.toString();
		} else {
			return null;
		}
	}

	/**
	 * Gets the resource name of the property file.
	 * can be null.
	 * @return the name of the resource in a String.
	 */
	public String getResource() 
	{
		if(resource != null) {
			return resource.toString();
		} else {
			return null;
		}
	}

	/**
	 * Gets the value of this property.
	 * Can be null.
	 * @return the value of the property.
	 * @see org.vae.core.Property#getValue()
	 */
	public String getValue()
	{
		if(value != null) {
			return value.toString();
		} else {
			return null;
		}
	}
	
	/**
	 * Remove an attribute at a given position in the attribute list.
	 * @param attributeName name of the attribute to be removed.
	 * @see org.vae.core.Tag#removeKeyAttribute(java.lang.String)
	 */
	protected void removeKeyAttributes(String attributeName) 
	{
		if(attributeName.equalsIgnoreCase("name")) {
			name = null;
		} else if (attributeName.equalsIgnoreCase("value")) {
			value = null;
		} else if (attributeName.equalsIgnoreCase("location")) {
			location = null;
		} else if (attributeName.equalsIgnoreCase("refid")) {
			refId = null;
		} else if (attributeName.equalsIgnoreCase("resource")) {
			resource = null;
		} else if (attributeName.equalsIgnoreCase("file")) {
			file = null;
		} else if (attributeName.equalsIgnoreCase("environment")) {
			envPrefix = null;
		} else if (attributeName.equalsIgnoreCase("classpath")) {
			// The classpath can also be set as an independant 
			// nested tag so watchout here.
			classpath = null;
		} else if (attributeName.equalsIgnoreCase("classpathref")) {
			classpathRef = null;
		} else if (attributeName.equalsIgnoreCase("prefix")) {
			prefix = null;
		}
	}

	/**
	 * Sets the value of the classpath.
	 * @param classpath
	 */
	public void setClasspath(String classpath) 
	{
		this.classpath = new StringBuffer(classpath);
	}

	/**
	 * Sets the classpath reference.
	 * @param the classpath reference in a String.
	 */
	public void setClasspathRef(String classpathRef) 
	{
		this.classpathRef = new StringBuffer(classpathRef);
	}

	/**
	 * Sets the Environment prefix used to retrieve
	 * environment variables.
	 * @param the environment prefix in a String.
	 */
	public void setEnvPrefix(String envPrefix) 
	{
		this.envPrefix = new StringBuffer(envPrefix);
	}

	/**
	 * Sets the filename of the property file to
	 * be loaded.
	 * @param the filename in a String.
	 */
	public void setFile(String file) 
	{
		this.file = new StringBuffer(file);
	}

	/**
	 * Sets the location of this property.
	 * @see org.vae.core.Property#setLocation(java.lang.String)
	 */
	public void setLocation(String location) 
	{
		this.location = new StringBuffer(location);
	}

	/**
	 * Sets the prefix to be applied to properties loaded 
	 * using file or resource. 
	 * @param the prefix in a String.
	 */
	public void setPrefix(String prefix) 
	{
		this.prefix = new StringBuffer(prefix);
	}

	/**
	 * Sets the reference to another property.
	 * @param the reference in a String.
	 */
	public void setRefId(String refId) 
	{
		this.refId = new StringBuffer(refId);
	}

	/**
	 * Sets the resource name of the property file.
	 * @param the name of the ressource in a String.
	 */
	public void setResource(String resource) 
	{
		this.resource = new StringBuffer(resource);
	}
	
	/**
	 * Sets the value of this property.
	 * Can't be null.
	 * @param the value of the property.
	 * @see org.vae.core.Property#setValue(java.lang.String)
	 */
	public void setValue(String value)
	{
		this.value = new StringBuffer(value);
	}
	
	/**
	 * Gives some various debug information
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer(100);
		if(name != null) {
			buf.append("   o Property : " +name + "\n");
		} 
		
		if (classpath != null) {
			buf.append("      Classpath set to " + classpath + "\n");
		} 
		
		if (classpathRef != null) {
			buf.append("      Classpath reference set to " + classpathRef + "\n");
		} 
		
		if (envPrefix != null) {
			buf.append("      Environment prefix set to "+ envPrefix + "\n");
		} 
		
		if (file != null) {
			buf.append("      File referenced "+ file + "\n");
		} 
		
		if (location != null) {
			buf.append("      Location set to "+ location + "\n");
		}
		 
		if (prefix != null) {
			buf.append("   Prefix for properties loaded from file : "+prefix+"\n");
		} 
		
		if (refId != null) {
			buf.append("   Referencing property : "+ refId +"\n");
		} 
		
		if (resource != null) {
			buf.append("   Resource name of the property file : "+resource+"\n");
		} 
		
		if (value != null) {
			buf.append("      Value of the property : "+ value+"\n");
		}
		
		return buf.toString();	
	}
	
	protected void verifyAttribute(String attribName, String attribValue)
	throws IllegalAttributeException
	{
		if(attribName.equalsIgnoreCase("name")) {
			setName(attribValue);
		} else if (attribName.equalsIgnoreCase("value")) {
			setValue(attribValue);
		} else if (attribName.equalsIgnoreCase("location")) {
			setLocation(attribValue);
		} else if (attribName.equalsIgnoreCase("refid")) {
			setRefId(attribValue);
		} else if (attribName.equalsIgnoreCase("resource")) {
			setResource(attribValue);
		} else if (attribName.equalsIgnoreCase("file")) {
			setFile(attribValue);
		} else if (attribName.equalsIgnoreCase("environment")) {
			setEnvPrefix(attribValue);
		} else if (attribName.equalsIgnoreCase("classpath")) {
			// The classpath can also be set as an independant 
			// nested tag so watchout here.
			setClasspath(attribValue);
		} else if (attribName.equalsIgnoreCase("classpathref")) {
			setClasspathRef(attribValue);
		} else if (attribName.equalsIgnoreCase("prefix")) {
			setPrefix(attribValue);
		} else {
			throw new IllegalAttributeException(attribName,
					name.toString(), legalAttributes);
		}
	}
}
