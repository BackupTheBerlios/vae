/*
 * Created on Aug 17, 2004
 *
 * $Id: ExitAction.java,v 1.2 2005/02/20 12:35:39 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.actions;

import org.eclipse.jface.action.Action;
import org.va_labs.vae.gui.Vui;

/**
 * @author mojo_jojo
 * 
 * Handles the click on File > Exit.
 */
public class ExitAction extends Action {

    /**
     * Reference to the Swt Vae User Interface that instanciated this.
     */
    private Vui vui;

    /**
     * Sets the entry up for the menu.
     * 
     * @param vui
     *            Visual ant editor User Interface that will be in relationship
     *            with this action.
     */
    public ExitAction(Vui vui) {
        this.vui = vui;
        setText("&Exit@Ctrl+Q");
        setToolTipText("Exit from VAE");
    }

    /**
     * Takes care of what to do when the User clicks on File > Exit
     */
    public void run() {
        vui.quit();
    }
}