/*
 * Created on Aug 17, 2004
 *
 * $Id: NewAction.java,v 1.1 2004/08/17 22:41:51 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.actions;

import org.eclipse.jface.action.Action;
import org.va_labs.vae.gui.Vui;

/**
 * @author mojo_jojo
 * Action that is run when the user clicks on File > New.
 */
public class NewAction extends Action {
    
    /**
	 * Reference to the main application window.
	 */
	private Vui vui;
	
	/**
	 * Creates an Action that will handle a File > New event for an 
	 * ApplicationWindow.
	 * @param window swtVui we handle the action for.
	 */
	public NewAction(Vui gui) {
		vui = gui;
		setText("&New@Ctrl+N");
//		Image newFileImage = new Image(window.getDisplay(), 
//			"org/vae/gui/swt/resources/newfile_wiz.gif");
	}
	
	/**
	 * Handles the event of the File > New entry of the menubar.
	 */
	public void run() {
		vui.signifyMessage("Click on New File captured");
	}
}
