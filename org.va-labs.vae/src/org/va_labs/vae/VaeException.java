/*
 * Created on Aug 15, 2004
 *
 * $Id: VaeException.java,v 1.2 2005/02/26 00:30:24 mojo_jojo Exp $
 */
package org.va_labs.vae;

/**
 * @author mojo_jojo
 * Barebone implementation of a Vae exception.
 * All exceptions in Vae should inherit from this one.
 */
public class VaeException extends Exception {

    /**
	 * Reference to the module that raised this exception.
	 */
	protected StringBuffer module;
	
	/**
	 * Message that should be transmited to the user when such exception
	 * is raised.
	 */
	protected StringBuffer message;
	
	/**
	 * Reason that caused this exception to be raised.
	 */
	protected StringBuffer reason;
	
	/**
	 * Status of the module that raised the exception.
	 * Defined in the Vae class.
	 */
	protected int status; 
	
	/**
	 * Basic constructor.
	 * @param vaeModule Module that raised the exception
	 * @param errorMessage message to be displayed to the user.
	 * @param errorReason reason that caused the exception to be raised.
	 * @param moduleStatus status of the module after the exception has 
	 * been raised.
	 */
	public VaeException(String vaeModule, String errorMessage, 
			String errorReason, int moduleStatus)
	{
		super(errorMessage);
		module = new StringBuffer(vaeModule);
		message = new StringBuffer(errorMessage);
		reason = new StringBuffer(errorReason);
		status = moduleStatus;
	}
	
	/**
	 * Gives the module that raised this exception.
	 * @return the module.
	 */
	public String getModule()
	{
		return module.toString();
	}
	
	/**
	 * Gives the reason why the exception has been raised.
	 * @return the reason.
	 */
	public String getReason()
	{
		return reason.toString();
	}
	
	/**
	 * Gives the satus of the module
	 * @return status of the module after the exception has been raised.
	 * Should be either VAE__OK, VAE__MODULE__ERROR, VAE__FATAL__ERROR.
	 * These are defined in the Vae class.
	 */
	public int getStatus()
	{
		return status;
	}
}
