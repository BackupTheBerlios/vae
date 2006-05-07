/*
 * Created on Aug 17, 2004
 *
 * $Id: NoSuchTargetException.java,v 1.2 2006/05/07 10:49:15 mojo_jojo Exp $
 */
package org.vae_labs.vae.tag.project;

import org.vae_labs.vae.VaeException;
import org.vae_labs.vae.core.Vae;

/**
 * Such exception is raised if vae for some reason tries to remove a target that
 * doesn't exist from a project. One has the opportunity to change the issuing
 * module and its status if necessary.
 * 
 * @author mojo_jojo
 */
public class NoSuchTargetException extends VaeException {

	/**
	 * Used for serialization purposes.
	 */
	private static final long serialVersionUID = 7404249312822404654L;

	/**
	 * Creates a target exception concerning the target for the given project.
	 * 
	 * @param targetName
	 *            name of the target.
	 * @param projectName
	 *            name of the project.
	 */
	public NoSuchTargetException(String targetName, String projectName) {
		super("UserInterface", "Illegal target removal -- Please "
				+ "signal it to the vae development team",
				"Target " + targetName + " does not exist in " + "project "
						+ projectName, Vae.VAE__OK);
	}
}
