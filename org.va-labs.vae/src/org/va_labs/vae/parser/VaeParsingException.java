/*
 * Created on Sep 4, 2004
 *
 * $Id: VaeParsingException.java,v 1.1 2004/09/05 00:30:54 mojo_jojo Exp $
 */
package org.va_labs.vae.parser;

import org.va_labs.vae.VaeException;

/**
 * @author mojo_jojo
 *
 * Exception thrown when the Vae xml parser faces an error while parsing
 * a build file.
 * For now, nothing more than a VaeException.
 */
public class VaeParsingException extends VaeException {

    /**
     * Creates a Vae Parsing Exception
     * @param vaeModule vae module issuing the exception.
     * @param errorMessage error message to be prompted to the user.
     * @param errorReason reason for which the exception has been raised.
     * @param moduleStatus status of the issuing module after the exception has
     * been raise.
     */
    public VaeParsingException(String vaeModule, String errorMessage, 
			String errorReason, int moduleStatus)
	{
		super(vaeModule, errorMessage, errorReason, moduleStatus);
	}
}