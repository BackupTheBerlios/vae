/*
 * Created on Sep 4, 2004
 *
 * $Id: LoadFailedException.java,v 1.1 2005/04/05 02:45:23 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.processes;

import java.lang.reflect.InvocationTargetException;

import org.vae_labs.vae.VaeException;
import org.vae_labs.vae.core.Vae;

/**
 * @author mojo_jojo
 *
 * Exception Thrown in case the Loading of a build file failed.
 * It sets automatically the module to Build Loader.
 * The message and the reason are directly from the exception. 
 */
public class LoadFailedException extends VaeException {
    
	public LoadFailedException(InvocationTargetException e)
	{
		super("Ant Loader", "Loading of ant build file failed.",
				e.getCause().getMessage(), Vae.VAE__OK);
	}
}
