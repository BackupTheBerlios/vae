/*
 * Created on Aug 17, 2004
 *
 * $Id: NoSuchTaskDefException.java,v 1.1 2004/08/17 22:41:03 mojo_jojo Exp $
 */
package org.va_labs.vae.exceptions;

import org.va_labs.vae.core.Vae;

/**
 * @author mojo_jojo
 * Such exception is raised if vae tries for a reason to remove a taskdef
 * that doesn't exist from a project.
 * One has the ability to change the issuing module and its status if 
 * necessary.
 */
public class NoSuchTaskDefException extends VaeException {

    /**
	 * Define an exception for a TaskDef for the given project.
	 * @param taskDef name of the taskdef having troubles.
	 * @param projectName name of the project that is concerned.
	 */
	public NoSuchTaskDefException(String taskDef, String projectName)
	{
		super("UserInterface", "Illegal TaskDef removal -- Please "+
				"signal it to the vae development team",
				"TaskDef "+taskDef+" does not exist in "+
				"project "+projectName, Vae.VAE__OK);
	}
}
