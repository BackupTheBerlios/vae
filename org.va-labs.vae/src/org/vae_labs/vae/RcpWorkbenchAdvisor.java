/*
 * Created on Jul 24, 2004
 *
 * $Id: RcpWorkbenchAdvisor.java,v 1.1 2005/04/05 02:45:23 mojo_jojo Exp $
 */
package org.vae_labs.vae;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.vae_labs.vae.core.Vae;
import org.vae_labs.vae.gui.Vui;
import org.vae_labs.vae.gui.actions.ExitAction;
import org.vae_labs.vae.gui.actions.NewAction;
import org.vae_labs.vae.gui.actions.OpenAction;
import org.vae_labs.vae.gui.actions.SaveAsAction;

/**
 * @author mojo_jojo 
 * 
 * Customize the workbench for the Visual Ant Editor.
 */
public class RcpWorkbenchAdvisor extends WorkbenchAdvisor {

    /**
     * Reference to the Visual Editor User Interface.
     */
    private Vui vui;

    /**
     * Handles the creation of the menu of the RcpPerspective.
     * 
     * @param window
     *            the workbench window.
     * @param configurer
     *            the action bar configurer instance.
     * @param flags
     *            bit mask indicating what needs to be filled or just described.
     *            (menu bar, cool bar, status line).
     */
    public void fillActionBars(IWorkbenchWindow window,
            IActionBarConfigurer configurer, int flags) {
        IMenuManager menubar = configurer.getMenuManager();
        fillFileMenu(menubar, window);
        fillEditMenu(menubar, window);
        fillHelpMenu(menubar, window);
    }

    private void fillEditMenu(IMenuManager menubar, IWorkbenchWindow window) {
        MenuManager editMenu = new MenuManager("&Edit");

        menubar.add(editMenu);
    }

    /**
     * Adds the File Menu to the given menubar.
     * 
     * @param menubar
     *            menubar to which the File menu has to added.
     */
    private void fillFileMenu(IMenuManager menubar, IWorkbenchWindow window) {
        MenuManager fileMenu = new MenuManager("&File");

        fileMenu.add(new NewAction());
        fileMenu.add(new OpenAction(window));
        fileMenu.add(new Separator());

        // Not ready yet.
        //		filemenu.add(new SaveAction(vui));
        fileMenu.add(new SaveAsAction(window));
        fileMenu.add(new Separator());

        fileMenu.add(new ExitAction());

        menubar.add(fileMenu);
    }

    private void fillHelpMenu(IMenuManager menubar, IWorkbenchWindow window) {
        MenuManager helpMenu = new MenuManager("&Help");

        menubar.add(helpMenu);
    }

    /**
     * Used to get the perspective id that we will be using for the visual ant
     * editor.
     * 
     * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
     */
    public String getInitialWindowPerspectiveId() {
        return "org.vae_labs.vae.gui.RcpPerspective";
    }

    public void preWindowOpen(IWorkbenchWindowConfigurer configurer) {
        super.preWindowOpen(configurer);
        configurer.setInitialSize(new Point(800, 600));
        configurer.setTitle(Messages.getString("VAE"));
        configurer.setShowCoolBar(false);

        // Initializes the Vae core, and the user interface.
        Vae vae = Vae.getInstance();
        vui = vae.getVui();
        vui.registerConfigurer(configurer);
    }
}