/*
 * Created on Aug 17, 2004
 *
 * $Id: SaveAsAction.java,v 1.2 2005/03/06 23:33:51 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.va_labs.vae.core.Vae;

/**
 * @author mojo_jojo
 * 
 * Action that is run when the user clicks on File > Save As.
 */
public class SaveAsAction extends Action {

    private IWorkbenchWindow window;

    /**
     * Creates an action for the Save as... Menu entry.
     * 
     * @param window
     *            the IWorkbenchWindow that will give us access to the shell
     *            later.
     */
    public SaveAsAction(IWorkbenchWindow window) {
        setText("&Save as...");
        setToolTipText("Save the project as...");
        this.window = window;
    }

    /**
     * Takes care of what to do when the User clicks on File > Exit
     */
    public void run() {
        Shell shell = window.getShell();
        String[] filters = { "XML Files", "Build Files (build.*)",
                "All Files (*.*)" };
        String[] extensions = { "*.xml", "build.*", "*.*" };

        FileDialog dialog = new FileDialog(shell, SWT.OPEN);

        dialog.setText("Save Project As");
        dialog.setFilterNames(filters);
        dialog.setFilterExtensions(extensions);
        dialog.setFileName("build.xml");
        String file = dialog.open();

//        String path = dialog.getFilterPath();
//        String file = dialog.getFilterPath()
//                + System.getProperty("file.separator") + dialog.getFileName();

        if (file != null) {
            Vae vae = Vae.getInstance();
            vae.saveAsProject(vae.getCurrentProject(), file);
        }
    }
}