/*
 * Created on Aug 16, 2004
 *
 * $Id: Vui.java,v 1.1 2004/08/17 22:41:51 mojo_jojo Exp $
 */
package org.va_labs.vae.gui;

import org.eclipse.swt.widgets.Composite;
import org.va_labs.vae.core.definitions.Project;

/**
 * @author mojo_jojo
 * Vae User Interface level methods.
 */
public class Vui {

    private Composite shell;
    /**
     * Saves the shell that we are using.
     * @param window the main composite that will be used by subcomponents.
     */
    public Vui() {
        
    }
    
    /**
     * Gets the currently active project.
     * @return the Project object referencing the active project.
     */
    public Project getActiveProject() {
        System.out.println(
                "Gui has been requested the name of the active project");
        return null;
    }
    
    /**
     * Opens a build file and displays its content.
     * @param filename path to the build file to be loaded.
     */
    public void openProject(String filename) {
        System.out.println("Gui has been requested to open "+filename);
    }
    
    /**
     * Handles a proper shutdown of the Visual Ant Editor.
     */
    public void quit() {
       System.out.println("Gui has been requested to close Vae"); 
    }
    
    /**
     * Saves a given project in a specified file.
     * @param project name of the project to be saved.
     * @param file file in which the project needs to be saved.
     */
    public void saveProject(Project project, String file) {
        System.out.println("Gui has been requested to save "+project+
                "in "+file);
    }
    
    /**
     * Signify a message to the user through the status bar.
     * @param message message to be shown to the user.
     */
    public void signifyMessage(String message) {
        System.out.println(message);
    }
}
