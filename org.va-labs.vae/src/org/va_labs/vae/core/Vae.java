/*
 * Created on Aug 15, 2004
 *
 * $Id: Vae.java,v 1.2 2004/08/20 19:49:04 mojo_jojo Exp $
 */
package org.va_labs.vae.core;

/**
 * @author mojo_jojo
 * The Visual Ant Editor core functionality is here.
 */
public class Vae {
    /**
	 * Indicates that Vae is operating under normal conditions.
	 */
    public static final int VAE__OK = 0;
    
    /**
	 * Indicates that the module issuing an error is dead and needs to be
	 * treated in order to continue operating.
	 */
	public static final int VAE__MODULE__ERROR = -1;
	
	/**
	 * We are facing a fatal error, and Vae should be trying to quit 
	 * safely. 
	 */
	public static final int VAE__FATAL__ERROR = -100;
}
