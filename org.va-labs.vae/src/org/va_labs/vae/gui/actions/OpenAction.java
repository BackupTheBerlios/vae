/*
 * Created on Aug 17, 2004
 *
 * $Id: OpenAction.java,v 1.4 2005/03/07 21:24:52 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.va_labs.vae.core.Vae;
import org.va_labs.vae.gui.Vui;

/**
 * @author mojo_jojo
 * 
 * Handles clicks on the Open entry of the file menu.
 */
public class OpenAction extends Action {

    /**
     * Reference to the Vae User Interface that instanciate this object.
     */
    private Vui vui;

    /**
     * Reference to the workbench window that is used in the workbench.
     */
    private IWorkbenchWindow window;

    /**
     * Creates a OpenAction with a reference to the Vae User Interface.
     * 
     * @param window
     *            the WorkbenchWindow that will give us a reference to the shell
     *            later.
     */
    public OpenAction(IWorkbenchWindow window) {
        setText("&Open@Ctrl+O");
        setToolTipText("Open a build file");
        this.window = window;
    }

    public void run() {
        Shell shell = window.getShell();
        String[] filters = { "XML Files", "Build Files (build.*)",
                "All Files (*.*)" };
        String[] extensions = { "*.xml", "build.*", "*.*" };

        FileDialog dialog = new FileDialog((Shell) shell, SWT.OPEN);

        dialog.setFilterNames(filters);
        dialog.setFilterExtensions(extensions);
        dialog.setFileName("build.xml");
        String file = dialog.open();
        if (file != null) {
            Vae.getInstance().openProject(file);
        }
    }
}