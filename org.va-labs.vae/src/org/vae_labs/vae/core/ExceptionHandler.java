/*
 * Created on Sep 4, 2004
 *
 * $Id: ExceptionHandler.java,v 1.1 2005/04/05 02:45:25 mojo_jojo Exp $
 */
package org.vae_labs.vae.core;

import java.io.FileNotFoundException;

import org.vae_labs.vae.VaeException;
import org.vae_labs.vae.parser.IllegalAttributeException;
import org.vae_labs.vae.parser.VaeParsingException;
import org.vae_labs.vae.tag.NoSuchAttributeException;

/**
 * @author mojo_jojo
 * 
 * Handles the exceptions for Vae.
 */
public class ExceptionHandler {

    /**
     * Reference to the ExceptionHandler singleton.
     */
    private static ExceptionHandler exceptionHandler;

    /**
     * Returns the singleton instance of the ExceptionHandler.
     * 
     * @return the only ExceptionHandler exception.
     */
    public static ExceptionHandler getInstance() {
        if (exceptionHandler == null) {
            exceptionHandler = new ExceptionHandler();
        }
        return exceptionHandler;
    }

    /**
     * For now, nothing to be done.
     */
    private ExceptionHandler() {

    }

    /**
     * Handles any java exception that is not encapsulated in a VaeException.
     * 
     * @param e
     *            exception thrown.
     */
    public void handle(Exception e) {
        Vae vae = Vae.getInstance();
        vae.acknowledgeError("Unidentified Module", "Unexpected Exception", e
                .getMessage(), Vae.VAE__OK, e);
        vae.signifyMessage("Ready.");
    }

    /**
     * Handles any typical VaeException
     * 
     * @param e
     *            VaeException that needs to be handled.
     */
    public void handle(VaeException e) {
        Vae vae = Vae.getInstance();
        if (e.getStatus() == Vae.VAE__OK) {
            vae.acknowledgeWarning(e.getModule(), e.getMessage(),
                    e.getReason(), e.getStatus(), e);
        } else if (e.getStatus() == Vae.VAE__MODULE__ERROR) {
            // Next line is disabled because of a jface bug.
            //			vae.signifyError("Error, please read the message.");
            vae.acknowledgeError(e.getModule(), e.getMessage(), e.getReason(),
                    e.getStatus(), e);
            vae.signifyMessage("Ready.");
        } else {
            // Next line is disabled because of a jface bug.
            //			vae.signifyError("Error, please read the message.");
            vae.acknowledgeError(e.getModule(), e.getMessage(), e.getReason(),
                    e.getStatus(), e);
            vae.quit();
        }
    }
    
    /**
     * Handles exceptions raised in case the user tried to update/access an 
     * attribute that doesn't exist. 
     * 
     * This is an internal error and can be raised only because of a bug.
     * 
     * @param e
     *            the raised NoSuchAttributeException.
     */
    public void handle(NoSuchAttributeException e) {
        Vae vae = Vae.getInstance();
        // Next line disabled because of a jface bug.
        //		vae.signifyError("Error, please read the message.");
        vae.acknowledgeError(e.getModule(), e.getMessage(), e.getReason(), e
                .getStatus(), e);
        vae.signifyMessage("Ready.");
    }

    public void handle(FileNotFoundException e) {
        Vae vae = Vae.getInstance();
        //		Next line is commented because of a jface bug.
        //		vae.signifyError("Error, please read the message.");
        vae.acknowledgeWarning("SaxXml", "Cannot load build file", "File "
                + e.getMessage() + " not found.\n Please check the path again",
                Vae.VAE__OK, e);
        vae.signifyMessage("Ready.");
    }

    /**
     * Handles exceptions raised in case the user tried to give an illegal
     * attribute to a Tag.
     * 
     * @param e
     *            the raised IllegalAttributeException.
     */
    public void handle(IllegalAttributeException e) {
        Vae vae = Vae.getInstance();
//        Next line is commented because of a SWT bug.
//        vae.signifyError("Please acknowledge the pending warning.");
        vae.acknowledgeWarning(e.getModule(), e.getMessage(), e.getReason(), e
                .getStatus(), e);
        vae.signifyMessage("Ready");
    }

    public void handle(VaeParsingException e) {
        Vae vae = Vae.getInstance();
//        Next line is commented because of a SWT bug.
//        vae.signifyError("Please acknowledge the pending warning.");
        vae.acknowledgeWarning(e.getModule(), e.getMessage(), e.getReason(), e
                .getStatus(), e);
        vae.signifyMessage("Ready.");
    }
}