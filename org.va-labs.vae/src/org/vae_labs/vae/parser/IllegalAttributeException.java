/*
 * Created on Aug 13, 2004
 *
 * $Id: IllegalAttributeException.java,v 1.2 2006/05/06 19:17:49 mojo_jojo Exp $
 */
package org.vae_labs.vae.parser;

import org.vae_labs.vae.VaeException;
import org.vae_labs.vae.core.Vae;

/**
 * This exception is intanciated if the user tried to add an Attribute that is
 * not legal for the component.
 * 
 * @author mojo_jojo
 * 
 */
public class IllegalAttributeException extends VaeException {

	/**
	 * Used for serialization purposes.
	 */
	private static final long serialVersionUID = 6474279564735710036L;

	/**
	 * Creates an IllegalVaeAttribute exception indicating wich attribute caused
	 * problem, what is the element it caused problem for, and a list of legal
	 * Attributes for the element.
	 * 
	 * @param attributeName
	 *            the name of attribute that has been rejected.
	 * @param elementName
	 *            the name of the element the attribute is not legal for.
	 * @param legalAttributes
	 *            list of legal attributes for this element.
	 */
	public IllegalAttributeException(String attributeName, String elementName,
			String[] legalAttributes) {
		super("Vae User Interface", "Wrong Attribute", "Legal attributes for "
				+ elementName + " : \n    ", Vae.VAE__OK);
		reason.append(legalAttributes[0]);
		for (int i = 1; i < legalAttributes.length; i++) {
			reason.append(", " + legalAttributes[i]);
		}
	}
}