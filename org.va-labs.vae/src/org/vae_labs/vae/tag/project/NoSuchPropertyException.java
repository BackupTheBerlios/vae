/*
 * Created on Aug 17, 2004
 *
 * $Id: NoSuchPropertyException.java,v 1.1 2005/04/05 02:45:17 mojo_jojo Exp $
 */
package org.vae_labs.vae.tag.project;

import org.vae_labs.vae.VaeException;
import org.vae_labs.vae.core.Vae;


/**
 * @author mojo_jojo
 * Raised when trying to remove a Property that doesn't exist.
 */
public class NoSuchPropertyException extends VaeException {
    
    /**
	 * Creates an exception concerning the given property for the given
	 * projet.
	 * @param propertyName name of the property that raised the problem.
	 * @param projectName name of the project that is concerned.
	 */
	public NoSuchPropertyException(	String propertyName, 
			String projectName)
	{
		super("UserInterface", "Illegal removal of a property -- "+
				"Please signal it to the vae development "+
				"team", "The property "+propertyName+
				" is not a valid property of project "+
				projectName, Vae.VAE__OK);
	}
}
