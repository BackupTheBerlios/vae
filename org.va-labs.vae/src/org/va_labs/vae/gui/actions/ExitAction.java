/*
 * Created on Aug 17, 2004
 *
 * $Id: ExitAction.java,v 1.1 2004/08/17 22:41:51 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.actions;

import org.eclipse.jface.action.Action;
import org.va_labs.vae.gui.Vui;

/**
 * @author mojo_jojo
 * Handles the click on File > Exit.
 */
public class ExitAction extends Action {
    /**
	 * Reference to the Swt Vae User Interface that instanciated this.
	 */
	private Vui vui;
	
	public ExitAction(Vui vui)
	{
		this.vui = vui;
		setText("&Exit@Ctrl+Q");
		setToolTipText("Exit from VAE");
	}
	
	/** 
	 * Takes care of what to do when the User clicks on File > Exit
	 */
	public void run()
	{
		vui.quit();
	}
}
