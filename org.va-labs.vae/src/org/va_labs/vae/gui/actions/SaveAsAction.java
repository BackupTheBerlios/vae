/*
 * Created on Aug 17, 2004
 *
 * $Id: SaveAsAction.java,v 1.1 2004/08/17 22:41:51 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.va_labs.vae.gui.Vui;

/**
 * @author mojo_jojo
 * Action that is run when the user clicks on File > Save As.
 */
public class SaveAsAction extends Action {
    
    private IWorkbenchWindow window;
    
    /**
	 * Reference to the Swt Vae User Interface that instanciated this.
	 */
	private Vui vui;
	
	/**
	 * Creates an action for the Save as... Menu entry.
	 * @param vui the visual Ant Editor Gui that will handle user interaction.
	 * @param window the IWorkbenchWindow that will give us access to the 
	 * shell later.
	 */
	public SaveAsAction(Vui vui, IWorkbenchWindow window)
	{
		this.vui = vui;
		setText("&Save as...");
		setToolTipText("Save the project as...");
		this.window = window;
	}
	
	/** 
	 * Takes care of what to do when the User clicks on File > Exit
	 */
	public void run()
	{
	    Shell shell = window.getShell();
		String [] filters = { "XML Files", "Build Files (build.*)", 
		"All Files (*.*)" };
		String[] extensions = { "*.xml", "build.*", "*.*" };
		
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);

		dialog.setText("Save Project As");
		dialog.setFilterNames(filters);
		dialog.setFilterExtensions(extensions);
		dialog.setFileName("build.xml");
		dialog.open();
		
		String path = dialog.getFilterPath();
		String file = dialog.getFilterPath() + 
			System.getProperty("file.separator") + 
			dialog.getFileName();
		
		if (!dialog.getFilterPath().equals("")) {
			vui.saveProject(vui.getActiveProject(), file);
		}
	}
}
