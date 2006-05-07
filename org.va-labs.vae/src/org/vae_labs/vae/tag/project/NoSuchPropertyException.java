/*
 * Created on Aug 17, 2004
 *
 * $Id: NoSuchPropertyException.java,v 1.2 2006/05/07 10:49:15 mojo_jojo Exp $
 */
package org.vae_labs.vae.tag.project;

import org.vae_labs.vae.VaeException;
import org.vae_labs.vae.core.Vae;

/**
 * Raised when trying to remove a Property that doesn't exist.
 * 
 * @author mojo_jojo
 */
public class NoSuchPropertyException extends VaeException {

	/**
	 * Used for serialization purposes.
	 */
	private static final long serialVersionUID = -4620308770116886942L;

	/**
	 * Creates an exception concerning the given property for the given projet.
	 * 
	 * @param propertyName
	 *            name of the property that raised the problem.
	 * @param projectName
	 *            name of the project that is concerned.
	 */
	public NoSuchPropertyException(String propertyName, String projectName) {
		super("UserInterface", "Illegal removal of a property -- "
				+ "Please signal it to the vae development " + "team",
				"The property " + propertyName
						+ " is not a valid property of project " + projectName,
				Vae.VAE__OK);
	}
}
