/*
 * Created on Sep 4, 2004
 *
 * $Id: LoadFailedException.java,v 1.1 2004/09/05 00:17:29 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.processes;

import java.lang.reflect.InvocationTargetException;

import org.va_labs.vae.VaeException;
import org.va_labs.vae.core.Vae;

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
