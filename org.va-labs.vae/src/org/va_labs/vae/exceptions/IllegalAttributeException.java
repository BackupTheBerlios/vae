/*
 * Created on Aug 13, 2004
 *
 * $Id: IllegalAttributeException.java,v 1.1 2004/08/17 22:40:43 mojo_jojo Exp $
 */
package org.va_labs.vae.exceptions;

import org.va_labs.vae.core.Vae;

/**
 * @author mojo_jojo
 * This exception is intanciated if the user tried to add 
 * an Attribute that is not legal for the component.
 */
public class IllegalAttributeException extends VaeException {

    /**
	 * Creates an IllegalVaeAttribute exception indicating wich attribute
	 * caused problem, what is the element it caused problem for, 
	 * and a list of legal Attributes for the element.
	 * @param attributeName the name of attribute that has been rejected.
	 * @param elementName the name of the element the attribute is not legal 
	 * for.
	 * @param legalAttributes list of legal attributes for this element.
	 */
	public IllegalAttributeException(String attributeName, 
			String elementName, String[] legalAttributes) {
		super("Vae User Interface", "Wrong Attribute", 
				"Legal attributes for "+elementName+" : \n",
				Vae.VAE__OK);
	}
}
