/*
 * Created on Aug 15, 2004
 *
 * $Id: NestedElement.java,v 1.1 2004/09/05 00:27:57 mojo_jojo Exp $
 */
package org.va_labs.vae.tag;


/**
 * @author mojo_jojo
 * 
 * Contains the information of a nested element inside
 * a Task.
 */
public abstract class NestedElement extends Tag {

    /**
	 * Calls the tag constructor.
	 */
	public NestedElement()
	{
		super();
	}
	
	/**
	 * Returns the unidentified tag type.
	 * @return the type corresponding to UNIDENTIFIED_TAG.
	 * @see org.vae.core.Tag#getType()
	 */
	public int getType()
	{
		return Tag.UNIDENTIFIED_TAG;
	}
}
