/*
 * Created on Aug 17, 2004
 *
 * $Id: NewAction.java,v 1.1 2005/04/05 02:45:25 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.actions;

import org.eclipse.jface.action.Action;
import org.vae_labs.vae.gui.Vui;

/**
 * @author mojo_jojo
 * 
 * Action that is run when the user clicks on File > New.
 */
public class NewAction extends Action {

    /**
     * Creates an Action that will handle a File > New event for an
     * ApplicationWindow.
     * 
     */
    public NewAction() {
        setText("&New@Ctrl+N");
        setToolTipText("Creates a new project build file.");
    }

    /**
     * Handles the event of the File > New entry of the menubar.
     */
    public void run() {
        Vui.getInstance().signifyMessage("Click on New File captured");
    }
}