/*
 * Created on Aug 15, 2004
 *
 * $Id: NoSuchAttributeException.java,v 1.2 2005/02/26 00:50:57 mojo_jojo Exp $
 */
package org.va_labs.vae.tag;

import org.va_labs.vae.VaeException;
import org.va_labs.vae.core.Vae;

/**
 * @author mojo_jojo
 * 
 * This exception is thrown when one tries to update or remove an attribute that
 * doesn't exists.
 * 
 * This exception is not critical and just voids the change asked by the user.
 * One can change the module name and the status related to this exception if
 * needed.
 */
public class NoSuchAttributeException extends VaeException {

    /**
     * The constructor simply calls the regular VaeException constructor to take
     * care of everythin.
     * 
     * @param operation
     *            Operation that was being processed while exception was raised
     *            (deletion or update).
     * @param attributeName
     *            name of the attribute which was supposed to be deleted or
     *            updated.
     * @param elementName
     *            Name of the particular element that was being processed.
     */
    public NoSuchAttributeException(String operation, String attributeName,
            String elementName) {
        super("UserInterface", "Illegal " + operation + " operation"
                + " -- Please signal it to development team",
                "Attribute " + attributeName + " is not a valid attribute of "
                        + elementName, Vae.VAE__OK);
    }
}