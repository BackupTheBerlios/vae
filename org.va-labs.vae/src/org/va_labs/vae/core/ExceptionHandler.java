/*
 * Created on Sep 4, 2004
 *
 * $Id: ExceptionHandler.java,v 1.1 2004/09/05 00:10:44 mojo_jojo Exp $
 */
package org.va_labs.vae.core;

import java.io.FileNotFoundException;

import org.va_labs.vae.VaeException;
import org.va_labs.vae.parser.VaeParsingException;
import org.va_labs.vae.tag.NoSuchAttributeException;

/**
 * @author mojo_jojo
 *
 * Handles the exceptions for Vae.
 */
public class ExceptionHandler {
    /**
	 * Reference to the core Vae component related to this exception handler.
	 */
	private Vae vae;
	
	/**
	 * Keeps a reference to the core Vae component related to this exception 
	 * handler.
	 * @param coreVae core Vae component instanciating this ExceptionHandler.
	 */
	public ExceptionHandler(Vae coreVae)
	{
		vae = coreVae;
	}
	
	public void handle(Exception e)
	{
		vae.acknowledgeError("Unidentified Module", 
				"Unexpected Exception", e.getMessage(), 
				Vae.VAE__OK, e);
		vae.signifyMessage("Ready.");
	}
	
	public void handle(VaeException e) 
	{
		if (e.getStatus() == Vae.VAE__OK) {
			vae.acknowledgeWarning(e.getModule(), e.getMessage(),
					e.getReason(), e.getStatus(), e);
		} else if (e.getStatus() == Vae.VAE__MODULE__ERROR) {
			// Next line is disabled because of a jface bug.
//			vae.signifyError("Error, please read the message.");
			vae.acknowledgeError(e.getModule(), e.getMessage(), 
					e.getReason(), e.getStatus(), e);
			vae.signifyMessage("Ready.");
		} else {
			// Next line is disabled because of a jface bug.
//			vae.signifyError("Error, please read the message.");
			vae.acknowledgeError(e.getModule(), e.getMessage(), 
					e.getReason(), e.getStatus(), e);
			vae.quit();
		}
	}
	
	public void handle(NoSuchAttributeException e) 
	{
		// Next line disabled because of a jface bug.
//		vae.signifyError("Error, please read the message.");
		vae.acknowledgeError(e.getModule(), e.getMessage(), 
				e.getReason(), e.getStatus(), e);
		vae.signifyMessage("Ready.");
	}
	
	public void handleFileNotFound(FileNotFoundException e)
	{
//		Next line disabled because of a jface bug.
//		vae.signifyError("Error, please read the message.");
		vae.acknowledgeWarning("SaxXml", "Cannot load build file" , "File " + e.getMessage() + 
			" not found.\n Please check the path again", Vae.VAE__OK, e);
		vae.signifyMessage("Ready.");
	}
	
	public void handleVaeParseException(VaeParsingException e)
	{
		vae.acknowledgeWarning(e.getModule(), e.getMessage(), 
				e.getReason(), e.getStatus(), e);
		vae.signifyMessage("Ready.");
	}
}
