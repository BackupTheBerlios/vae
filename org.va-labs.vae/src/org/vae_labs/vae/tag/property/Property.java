/*
 * Created on Aug 15, 2004
 *
 * $Id: Property.java,v 1.1 2005/04/05 02:45:25 mojo_jojo Exp $
 */
package org.vae_labs.vae.tag.property;

import org.vae_labs.vae.tag.Tag;


/**
 * @author mojo_jojo
 * Define what an implementation of a Property should have. 
 * It does so by adding methods to the Tag class.
 */
public abstract class Property extends Tag {
    public Property()
	{
		super();
	}
	
	/**
	 * Gets the location of this property.
	 * Can return a null value.
	 * @return
	 */
	public abstract String getLocation();
	
	/**
	  * Sets the location of this property.
	  * @see org.vae.core.Property#setLocation(java.lang.String)
	  */
	public abstract void setLocation(String location);

	/**
	 * Gets the value of this property.
	 * Can be null.
	 * @return the value of the property.
	 * @see org.vae.core.Property#getValue()
	 */
	public abstract String getValue();
	
	/**
	 * Sets the value of this property.
	 * Can't be null.
	 * @param the value of the property.
	 * @see org.vae.core.Property#setValue(java.lang.String)
	 */
	public abstract void setValue(String value);
	
	/**
	 * Gets the classpath value.
	 * Can be null.
	 * @return the classpath reference.
	 */
	public abstract String getClasspath();
	
	/**
	 * Sets the classpath value.
	 * @param the classpath reference in a String.
	 */
	public abstract void setClasspath(String classpath);
	
	/**
	 * Gets the classpath reference value.
	 * Can be null.
	 * @return the classpath reference.
	 */
	public abstract String getClasspathRef();
	
	/**
	 * Sets the classpath reference.
	 * @param the classpath reference in a String.
	 */
	public abstract void setClasspathRef(String classpathRef);
	
	/**
	 * Gets the Environment prefix used to retrieve 
	 * environment variables.
	 * Can be null.
	 * @return the Environment prefix.
	 */
	public abstract String getEnvPrefix();
	
	/**
	 * Sets the Environment prefix used to retrieve
	 * environment variables.
	 * @param the environment prefix in a String.
	 */
	public abstract void setEnvPrefix(String envPrefix);
	
	/**
	 * Get the filename of the property file to
	 * be loaded.
	 * Can be null.
	 * @return the filename in a String.
	 */
	public abstract String getFile();
	
	/**
	 * Sets the filename of the property file to
	 * be loaded.
	 * @param the filename in a String.
	 */
	public abstract void setFile(String file);
	
	/**
	 * Gets the prefix to be applied to properties loaded 
	 * using file or resource.
	 * Can be null.
	 * @return the prefix in a String 
	 */
	public abstract String getPrefix();
	
	/**
	 * Sets the prefix to be applied to properties loaded 
	 * using file or resource. 
	 * @param the prefix in a String.
	 */
	public abstract void setPrefix(String prefix);
	
	/**
	 * Gets the reference to another property.
	 * Can be null.
	 * @return the reference in a String. 
	 */
	public abstract String getRefId();
	
	/**
	 * Sets the reference to another property.
	 * @param the reference in a String.
	 */
	public abstract void setRefId(String refId);
	
	/**
	 * Gets the resource name of the property file.
	 * can be null.
	 * @return the name of the resource in a String.
	 */
	public abstract String getResource();
	
	/**
	 * Returns the type of this Tag.
	 * @return the type corresponding to PROPERTY_TAG.
	 * @see org.vae.core.Tag#getType()
	 */
	public int getType() {
		return Tag.PROPERTY_TAG;
	}
	
	/**
	 * Sets the resource name of the property file.
	 * @param the name of the ressource in a String.
	 */
	public abstract void setResource(String resource);
}
