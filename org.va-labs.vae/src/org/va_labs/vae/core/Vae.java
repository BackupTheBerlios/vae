/*
 * Created on Aug 15, 2004
 *
 * $Id: Vae.java,v 1.6 2005/01/11 00:04:49 mojo_jojo Exp $
 */
package org.va_labs.vae.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.va_labs.vae.VaeInitException;
import org.va_labs.vae.gui.Vui;
import org.va_labs.vae.gui.processes.AntLoader;
import org.va_labs.vae.gui.processes.VaeExport;
import org.va_labs.vae.tag.project.Project;

/**
 * @author mojo_jojo The Visual Ant Editor core functionality is here.
 */
public class Vae {

    /**
     * Unique vae instance driving the application.
     */
    private static Vae vae = null;

    /**
     * We are facing a fatal error, and Vae should be trying to quit safely.
     */
    public static final int VAE__FATAL__ERROR = -100;

    /**
     * Indicates that the module issuing an error is dead and needs to be
     * treated in order to continue operating.
     */
    public static final int VAE__MODULE__ERROR = -1;

    /**
     * Indicates that Vae is operating under normal conditions.
     */
    public static final int VAE__OK = 0;

    /**
     * Get the core vae component driving the application
     * 
     * @return the core Vae instance.
     */
    public static Vae getVae() {
        if (vae == null) {
            vae = new Vae();
        }
        return vae;
    }

    /**
     * module in charge of loading build files and handling the different
     * processes involved.
     */
    private AntLoader antLoader;

    /**
     * Reference to the currently opened project.
     */
    private Project currentProject;

    /**
     * Module that handles exceptions for us.
     */
    private ExceptionHandler exceptionHandler;

    /**
     * Reference to the different projects that are currently opened by this
     * Vae.
     */
    private HashMap projects;

    /**
     * Reference to the VaeExport used to export project information to various
     * formats.
     */
    private VaeExport vaeExport;

    /**
     * Vae User Interface in use for this core.
     */
    private Vui vui;

    /**
     * Initialization of the Vae instance. Creates the exceptionHandler and a
     * stack that will receive the projects. The AntLoader is initiliazed as
     * well.
     */
    private Vae() {
        exceptionHandler = new ExceptionHandler(this);
        projects = new HashMap(5);
        try {
            initAntLoader();
        } catch (VaeInitException e) {
            exceptionHandler.handle(e);
        }
    }

    /**
     * Prompts an error to the user.
     * 
     * @param vaeModule
     *            The module that issued this error.
     * @param errorMessage
     *            The error message.
     * @param reasonMessage
     *            The reason for this error message.
     * @param moduleStatus
     *            The status of the issuing module after the error.
     * @param e
     *            The exception that was raised because of the error (just send
     *            null if no exception was the source of it).
     * @see org.eclipse.core.runtime.IStatus
     */
    public void acknowledgeError(String vaeModule, String errorMessage,
            String reasonMessage, int moduleStatus, Exception e) {
        vui.acknowledgeError(vaeModule, errorMessage, reasonMessage,
                moduleStatus, e);
    }

    /**
     * Prompts a warning to the user.
     * 
     * @param vaeModule
     *            The module that issued this warning.
     * @param warningMessage
     *            The warning message.
     * @param reasonMessage
     *            The reason for this warning message.
     * @param moduleStatus
     *            The status of the issuing module after the warning.
     * @param e
     *            The exception that was raised to issue this warning (just send
     *            null if no exception was the source of it).
     * @see org.eclipse.core.runtime.IStatus
     */
    public void acknowledgeWarning(String vaeModule, String warningMessage,
            String warningReason, int moduleStatus, Exception e) {
        vui.acknowledgeWarning(vaeModule, warningMessage, warningReason,
                moduleStatus, e);
    }

    /**
     * Indicates the projects that have been changed.
     * 
     * @return the list of the projects that have been changed.
     */
    public List getDirtyProjects() {
        int numberProjects = projects.size();
        ArrayList dirtyProjects = new ArrayList(numberProjects);

        if (numberProjects > 0) {
            Collection values = projects.values();
            for (Iterator i = values.iterator(); i.hasNext();) {
                Project p = (Project) i.next();
                if (!p.getClean()) {
                    dirtyProjects.add(p);
                }
            }
        }
        return dirtyProjects;
    }

    /**
     * Initializes the ant loader.
     * 
     * @throws VaeInitException
     *             if anything goes wrong.
     */
    private void initAntLoader() throws VaeInitException {
        antLoader = new AntLoader(this);
    }

    /**
     * Indicates whether at least one of the projects currently opened need to
     * be saved.
     * 
     * @return true if one of the project needs to be saved.
     */
    public boolean isDirty() {
        Collection values = projects.values();
        for (Iterator i = values.iterator(); i.hasNext();) {
            Project p = (Project) i.next();
            if (!p.getClean()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Opens an ant build file, parses it and prompts the results to the user.
     * 
     * @param project
     *            file name in a String.
     */
    public void openProject(String filename) {
        if (!projects.containsKey(filename)) {
            antLoader.setBuildFile(filename);
            try {
                antLoader.loadBuild();
                vui.displayProject((Project) projects.get(filename));
            } catch (Exception e) {
                exceptionHandler.handle(e);
            }
        } else {
            acknowledgeWarning("Vae Core", "Cannot open project",
                    "The project is already opened", VAE__OK, new Exception(
                            "Cannot open project"));
        }
    }

    /**
     * User asked to quit. This function checks for project that possibly need
     * to be saved, and asks the user wether he wants to save those ones or not.
     */
    public void quit() {
        int projectsNumber = projects.size();

        if (isDirty()) {
            Object toSave[] = vui.getToSave();
            saveProjects(toSave);
            System.exit(0);
        }
        System.exit(0);
    }

    /**
     * Register a projet in the list of opened projects. Calling this method for
     * a previously registered project overrides the previous data with the new
     * one. The newly registered project becomes the current project.
     * 
     * @param buildLocation
     *            path + name to the build file. Used to keep one opened copy of
     *            each project.
     * @param project
     *            project object summarizing the build information.
     */
    public void registerProject(String buildLocation, Project project) {
        projects.put(buildLocation, project);
        currentProject = project;
    }

    /**
     * Registers the vui to this vae core.
     * 
     * @param gui
     *            the user interface we use for this vae core.
     */
    public void registerVui(Vui gui) {
        vui = gui;
    }

    /**
     * Saves specified projects. The export format is an xml build file.
     * 
     * @param toSave
     *            array of the projects that we want to save.
     */
    public void saveProjects(Object[] toSave) {
        if (toSave != null) {
            for (int i = 0; i < toSave.length; i++) {
                // TODO : Implement saveProjects.
                System.out.println(((Project) toSave[i]).getName()
                        + " should have been saved");
                // vaeExport should throw VaeExportException in case of
                // trouble, this call should be surrounded by a try/catch.
                //              vaeExport.project2ant((Project) toSave[i]);
            }
        }
    }

    /**
     * Signify an error in the status bar.
     * 
     * @param error
     *            message to be displayed in the taskbar.
     */
    public void signifyError(String error) {
        vui.signifyError(error);
    }

    /**
     * Sends some information to the user. This type is one way kind : perfect
     * for status bar.
     */
    public void signifyMessage(String message) {
        vui.signifyMessage(message);
    }
}