/*
 * Created on Aug 13, 2004
 *
 * $Id: IllegalAttributeException.java,v 1.2 2005/02/26 00:47:28 mojo_jojo Exp $
 */
package org.va_labs.vae.parser;

import org.va_labs.vae.VaeException;
import org.va_labs.vae.core.Vae;

/**
 * @author mojo_jojo This exception is intanciated if the user tried to add an
 *         Attribute that is not legal for the component.
 */
public class IllegalAttributeException extends VaeException {

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
        for (int i=1; i < legalAttributes.length; i++) {
            reason.append(", " + legalAttributes[i]);
        }
    }
}