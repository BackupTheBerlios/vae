/*
 * Created on Sep 5, 2004
 *
 * $Id: VaeInitException.java,v 1.2 2006/05/06 19:11:00 mojo_jojo Exp $
 */
package org.vae_labs.vae;

/**
 * @author amine
 * 
 * Exception thrown when a module doesn't manage to initialize itself.
 */
public class VaeInitException extends VaeException {

    /**
	 * Used for serialization purposes.
	 */
	private static final long serialVersionUID = -1567466370704400308L;

	/**
     * @param vaeModule
     *            module that couldn't initialize.
     * @param errorMessage
     *            error message contained by the exception.
     * @param errorReason
     *            reason for which the exception was raised.
     * @param moduleStatus
     *            status of the exception after the exception has been raised
     *  
     */
    public VaeInitException(String vaeModule, String errorMessage,
            String errorReason, int moduleStatus) {
        super(vaeModule, errorMessage, errorReason, moduleStatus);
    }

}