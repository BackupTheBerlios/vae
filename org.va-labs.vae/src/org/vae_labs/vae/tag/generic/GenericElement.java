/*
 * Created on Sep 5, 2004
 *
 * $Id: GenericElement.java,v 1.1 2005/04/05 02:45:27 mojo_jojo Exp $
 */
package org.vae_labs.vae.tag.generic;

import org.vae_labs.vae.parser.IllegalAttributeException;
import org.vae_labs.vae.tag.NestedElement;
import org.vae_labs.vae.tag.Tag;

/**
 * @author mojo_jojo
 *
 * Class used for elements that we have no precise Idea on what
 * they are (Usually just nested elements inside a target).
 * It's the simplest of all, none of the verify methods needs to be redefined.
 */
public class GenericElement extends NestedElement {

    /**
     * Creates a generic Element.
     * Just calls the constructor of NestedElement.
     */
    public GenericElement()
	{
		super();
	}
	
	/**
	 * Constructs a Generic Element whose name is the one
	 * given as parameter
	 * @param the name of this element.  
	 */
	public GenericElement(String name)
	{
		this();
		this.name = new StringBuffer(name);
	}
	
	/**
	 * Construcs a Generic Element from an existing Tag (built in the 
	 * xml parser).
	 */
	public GenericElement(Tag tag)
	{
		try {
			setAttributes(tag.getAttributes());
		} catch (IllegalAttributeException e) {
			System.out.println("Illegal target attribute detected");
		}
		setNestedElements(tag.getNestedElements());
	}
}
