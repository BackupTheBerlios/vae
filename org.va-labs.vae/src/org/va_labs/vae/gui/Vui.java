/*
 * Created on Aug 16, 2004
 *
 * $Id: Vui.java,v 1.7 2005/02/22 22:54:50 mojo_jojo Exp $
 */
package org.va_labs.vae.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.va_labs.vae.core.TagAttribute;
import org.va_labs.vae.core.Vae;
import org.va_labs.vae.gui.dialog.SaveFilesContentProvider;
import org.va_labs.vae.gui.dialog.SaveFilesLabelProvider;
import org.va_labs.vae.gui.listener.IElementListener;
import org.va_labs.vae.gui.tag.ISwtElement;
import org.va_labs.vae.gui.tag.project.SwtProject;
import org.va_labs.vae.gui.view.datainfo.DataInfoView;
import org.va_labs.vae.gui.view.datatree.DataTreeView;
import org.va_labs.vae.tag.project.Project;
import org.va_labs.vae.tag.workspace.SwtWorkspace;

/**
 * @author mojo_jojo
 * 
 * Vae User Interface class. Core user interface methods.
 */
public class Vui {

    /**
     * Reference to the unique vui instance that pilotes the user interface.
     */
    private static Vui vui = null;

    /**
     * Enables to get the vui instance driving the user interface, The instance
     * is created if needed.
     * 
     * @return the vui instance piloting the user interface.
     */
    public static Vui getVui() {
        if (vui == null) {
            vui = new Vui();
        }
        return vui;
    }

    /**
     * Reference to the current SwtElement being considered by the user.
     */
    private ISwtElement currentElement;

    /**
     * Reference to the dataInfoView.
     */
    private DataInfoView dataInfoView;

    /**
     * Reference to the dataTreeView.
     */
    private DataTreeView dataTreeView;

    /**
     * Reference to the registered ElementListeners.
     */
    private List elementListeners;

    /**
     * Reference to the core vae object.
     */
    private Vae vae;

    /**
     * Workbench Configurer used to get access to gui subcomponents.
     */
    private IWorkbenchWindowConfigurer windowConfigurer;

    /**
     * Workspace of the visual ant editor.
     */
    private SwtWorkspace workspace;

    /**
     * Initialization of the Vae User Interface. Registers the vae core, and
     * registers this instance to the vae core.
     *  
     */
    private Vui() {
        vae = Vae.getVae();
        vae.registerVui(this);
        elementListeners = new ArrayList();
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
        if (reasonMessage == null) {
            reasonMessage = e.getCause().toString();
        }

        Status status = new Status(IStatus.ERROR, vaeModule, moduleStatus,
                reasonMessage, e);

        ErrorDialog.openError(windowConfigurer.getWindow().getShell(),
                "Visual Ant Editor -- " + vaeModule + " error", errorMessage,
                status, IStatus.ERROR);
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
            String reasonMessage, int moduleStatus, Exception e) {
        // Creates a swt status for the swt warning dialog
        Status status = new Status(IStatus.WARNING, vaeModule, moduleStatus,
                reasonMessage, e);

        ErrorDialog.openError(windowConfigurer.getWindow().getShell(),
                "Visual Ant Editor -- " + vaeModule + "  warning",
                warningMessage, status, IStatus.WARNING);
    }

    /**
     * Adds an ElementListener to the list. The listner will be kept informed
     * each time the user changes the ISwtElement he/she considers.
     * 
     * @param listener
     *            listener to be kept updated of changes to the current
     *            ISwtElement.
     */
    public void addElementListener(IElementListener listener) {
        elementListeners.add(listener);
    }

    /**
     * Displays a given project in the gui,
     * 
     * @param project
     *            project to be displayed.
     */
    public void displayProject(Project project) {
        workspace.addProject(new SwtProject(project, workspace));
        dataTreeView.setTreeInput(workspace);
    }

    /**
     * Gets the currently active project. TODO: Implement the getActiveProject
     * method.
     * 
     * @return the Project object referencing the active project.
     */
    public Project getActiveProject() {
        return Vae.getVae().getCurrentProject();
    }

    /**
     * Enables access to the shell of this gui.
     * 
     * @return the shell of this gui.
     */
    public Shell getShell() {
        return windowConfigurer.getWindow().getShell();
    }

    /**
     * Presents a dialog with the resources that needs to be saved as a list of
     * checkboxed items. The dialog shows up only if at least one project needs
     * to be saved.
     * 
     * @return an array of projects that the user asked to save.
     */
    public Object[] getToSave() {
        ListSelectionDialog dlg = new ListSelectionDialog(windowConfigurer
                .getWindow().getShell(), vae, new SaveFilesContentProvider(),
                new SaveFilesLabelProvider(), "Select the resources to save : ");
        dlg.setTitle("Resources to save.");
        dlg.setMessage("Select the resources that should be saved : ");
        dlg.setInitialSelections(vae.getDirtyProjects().toArray());
        dlg.open();
        return dlg.getResult();
    }

    /**
     * Opens a build file and displays its content.
     * 
     * @param filename
     *            path to the build file to be loaded.
     */
    public void openProject(String filename) {
        vae.openProject(filename);
    }

    /**
     * Handles a proper shutdown of the Visual Ant Editor.
     */
    public void quit() {
        vae.quit();
    }
    
    /**
     * Refreshes everything that is relative to this tag.
     * 
     * @param tagAttribute attribute that has been modified.
     */
    public void refresh(TagAttribute tagAttribute) {
        dataTreeView.refreshWorkspace(workspace);
        dataInfoView.refresh(tagAttribute);
    }

    /**
     * Registers the IWorkbenchWindowConfigurer used for the user interface.
     * It's provided by the eclipse toolkit from the RcpWorkbench.
     * 
     * @param configurer
     *            IworkbenchWindowConfigurer that will configure this interface.
     */
    public void registerConfigurer(IWorkbenchWindowConfigurer configurer) {
        windowConfigurer = configurer;
    }

    /**
     * Registers the DataInfoView for this vui. It instanciates the
     * TreeSelectionListener if the tree is ready.
     * 
     * @param dataView
     *            the DataInfoView in charge of the data table view.
     */
    public void registerInfoView(DataInfoView infoView) {
        dataInfoView = infoView;
    }

    /**
     * Registers the DataTreeView for this vui, and sets the workspace that will
     * be its input.
     * 
     * @param treeView
     *            the DataTreeView in charge of the tree view.
     */
    public void registerTreeView(DataTreeView treeView) {
        dataTreeView = treeView;
        workspace = new SwtWorkspace();
    }

    /**
     * Register the core vae instance that pilots this user interface.
     * 
     * @param coreVae
     *            vae instance that pilots the vui instance.
     */
    public void registerVae(Vae coreVae) {
        vae = coreVae;
    }

    /**
     * Removes an ElementListener from the list.
     * 
     * @param listener
     *            listener to be taken out from the list.
     */
    public void removeElementListener(IElementListener listener) {
        int index = elementListeners.indexOf(listener);
        if (!elementListeners.remove(listener)) {
            // TODO: Send the trouble to the exceptionHandler !
        }
    }

    /**
     * Saves a given project in a specified file.
     * 
     * TODO: Implement saveProject.
     * 
     * @param project
     *            name of the project to be saved.
     * @param file
     *            file in which the project needs to be saved.
     */
    public void saveProject(Project project, String file) {
        System.out.println("Gui has been requested to save " + project + "in "
                + file);
    }

    /**
     * Used to set the current Element that is being considered by the user.
     * This method calls updateElementListeners to inform the listeners of the
     * change.
     * 
     * @param element
     *            element currently viewed/edited by the user.
     */
    public void setCurrentElement(ISwtElement element) {
        currentElement = element;
        updateElementListeners();
        System.out.println("Vui.setCurrentElement");
    }

    /**
     * Signify an error message to the user. The message is displayed in the
     * status bar in red.
     * 
     * @param errorMessage
     */
    public void signifyError(String errorMessage) {
        IActionBarConfigurer actionConfigurer = windowConfigurer
                .getActionBarConfigurer();
        IStatusLineManager statusManager = actionConfigurer
                .getStatusLineManager();
        statusManager.setErrorMessage(errorMessage);
    }

    /**
     * Signify a message to the user the message is displayed in the status bar.
     * 
     * @param message
     *            message to be displayed to the user.
     */
    public void signifyMessage(String message) {
        IActionBarConfigurer actionConfigurer = windowConfigurer
                .getActionBarConfigurer();
        IStatusLineManager statusManager = actionConfigurer
                .getStatusLineManager();
        statusManager.setMessage(message);
        statusManager.update(true);
    }

    /**
     * Tells all the registered Element Listeners that the current element has
     * been changed.
     * 
     * Note: We should be careful if some day one listener has the ability to
     * update the current element, he could be signaled of that change and that
     * could lead to something inconvenient or worse (problems).
     */
    private void updateElementListeners() {
        Iterator i = elementListeners.iterator();
        while (i.hasNext()) {
            IElementListener listener = (IElementListener) i.next();
            listener.setCurrentElement(currentElement);
        }
    }
}