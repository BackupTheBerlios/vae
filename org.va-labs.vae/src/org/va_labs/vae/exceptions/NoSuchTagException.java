/*
 * Created on Aug 15, 2004
 *
 * $Id: NoSuchTagException.java,v 1.1 2004/08/17 22:41:03 mojo_jojo Exp $
 */
package org.va_labs.vae.exceptions;

import org.va_labs.vae.core.Vae;

/**
 * @author mojo_jojo
 * Such exception is raised if vae for some reason tries to delete an 
 * unidentified tag that doesn't exist from a project.
 * One has the ability to change the issuing module and its status if needed.
 * 
 * Unidentified tags are basically at the level of targets, but are neigher 
 * properties nor targets. At one point this elements need to be integrated 
 * specifically, but we want at least to display them in the tree with an 
 * image.
 */
public class NoSuchTagException extends VaeException {

    /**
     * Simply calls the VaeException constructor with adequate options. 
     * @param nestedTag tag that was processed when the exception was raised.
     * @param parentTag parent tag of the processed tag.
     */
    public NoSuchTagException(String nestedTag, String parentTag) {
		super("UserInterface", "Illegal Tag removal -- "+
				"Please signal it to vae development team",
				"Tag "+ nestedTag+" is not a nested tag of "
				+parentTag, Vae.VAE__OK);
	}
}
