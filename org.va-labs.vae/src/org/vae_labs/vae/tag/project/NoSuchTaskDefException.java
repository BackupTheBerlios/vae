/*
 * Created on Aug 17, 2004
 *
 * $Id: NoSuchTaskDefException.java,v 1.2 2006/05/07 10:49:15 mojo_jojo Exp $
 */
package org.vae_labs.vae.tag.project;

import org.vae_labs.vae.VaeException;
import org.vae_labs.vae.core.Vae;

/**
 * Such exception is raised if vae tries for a reason to remove a taskdef that
 * doesn't exist from a project. One has the ability to change the issuing
 * module and its status if necessary.
 * 
 * @author mojo_jojo
 */
public class NoSuchTaskDefException extends VaeException {

	/**
	 * Used for serialization purposes.
	 */
	private static final long serialVersionUID = -4279738195401164037L;

	/**
	 * Define an exception for a TaskDef for the given project.
	 * 
	 * @param taskDef
	 *            name of the taskdef having troubles.
	 * @param projectName
	 *            name of the project that is concerned.
	 */
	public NoSuchTaskDefException(String taskDef, String projectName) {
		super("UserInterface", "Illegal TaskDef removal -- Please "
				+ "signal it to the vae development team", "TaskDef " + taskDef
				+ " does not exist in " + "project " + projectName, Vae.VAE__OK);
	}
}
