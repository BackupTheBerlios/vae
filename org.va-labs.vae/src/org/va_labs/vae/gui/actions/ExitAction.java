/*
 * Created on Aug 17, 2004
 *
 * $Id: ExitAction.java,v 1.3 2005/03/07 21:23:37 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.actions;

import org.eclipse.jface.action.Action;
import org.va_labs.vae.core.Vae;

/**
 * @author mojo_jojo
 * 
 * Handles the click on File > Exit.
 */
public class ExitAction extends Action {

    /**
     * Sets the entry up for the menu.
     * 
     */
    public ExitAction() {
        setText("&Exit@Ctrl+Q");
        setToolTipText("Exit from VAE");
    }

    /**
     * Takes care of what to do when the User clicks on File > Exit
     */
    public void run() {
        Vae.getInstance().quit();
    }
}