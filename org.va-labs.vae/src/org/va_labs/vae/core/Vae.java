/*
 * Created on Aug 15, 2004
 *
 * $Id: Vae.java,v 1.3 2004/09/05 00:12:48 mojo_jojo Exp $
 */
package org.va_labs.vae.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.va_labs.vae.gui.Vui;
import org.va_labs.vae.gui.processes.AntLoader;
import org.va_labs.vae.gui.processes.VaeExport;
import org.va_labs.vae.parser.IAntParser;
import org.va_labs.vae.tag.project.Project;

/**
 * @author mojo_jojo
 * The Visual Ant Editor core functionality is here.
 */
public class Vae {
    /**
	 * Indicates that Vae is operating under normal conditions.
	 */
    public static final int VAE__OK = 0;
    
    /**
	 * Indicates that the module issuing an error is dead and needs to be
	 * treated in order to continue operating.
	 */
	public static final int VAE__MODULE__ERROR = -1;
	
	/**
	 * We are facing a fatal error, and Vae should be trying to quit 
	 * safely. 
	 */
	public static final int VAE__FATAL__ERROR = -100;
	
	/**
	 * module in charge of loading build files
	 * and handling the different processes involved.
	 */
	private AntLoader buildLoader;
	
	/**
	 * Module that handles exceptions for us.
	 */
	private ExceptionHandler exceptionHandler;

	/**
	 * Reference to the different projects that are
	 * currently opened by this Vae. 
	 */
	private ArrayList projects;
	
	/**
	 * Reference to the VaeExport used to export
	 * project information to various formats.
	 */
	private VaeExport vaeExport;
	
	/**
	 * Vae User Interface in use for this core.
	 */
	private Vui vui;
	
	/**
	 * Module that parses the ant build file.
	 */
	private IAntParser parser;
	
	public Vae() {
	    exceptionHandler = new ExceptionHandler(this);
		projects = new ArrayList(5);
	}
	
	/**
	 * Prompts an error to the user.
	 * @param vaeModule The module that issued this error.
	 * @param errorMessage The error message.
	 * @param reasonMessage The reason for this error message.
	 * @param moduleStatus The status of the issuing module after the error.
	 * @param e The exception that was raised because of the error (just send null
	 * if no exception was the source of it).
	 * @see org.eclipse.core.runtime.IStatus
	 */
	public void acknowledgeError(String vaeModule, String errorMessage, 
			String reasonMessage, int moduleStatus, Exception e) { 
		vui.acknowledgeError(vaeModule, errorMessage, reasonMessage, 
				moduleStatus, e);
	}
	
	/**
	 * Prompts a warning to the user.
	 * @param vaeModule The module that issued this warning.
	 * @param warningMessage The warning message.
	 * @param reasonMessage The reason for this warning message.
	 * @param moduleStatus The status of the issuing module after the 
	 * warning.
	 * @param e The exception that was raised to issue this warning (just 
	 * send null if no exception was the source of it).
	 * @see org.eclipse.core.runtime.IStatus
	 */
	public void acknowledgeWarning(String vaeModule,String warningMessage, 
			String warningReason, int moduleStatus, Exception e)
	{
		vui.acknowledgeWarning(vaeModule, warningMessage, 
				warningReason, moduleStatus, e);
	}
	
	/**
	 * Indicates the projects that have been changed.
	 * @return the list of the projects that have been changed.
	 */
	public List getDirtyProjects()
	{
		int numberProjects = projects.size();
		ArrayList dirtyProjects = new ArrayList(numberProjects);

		if( numberProjects > 0 ) {
			for(Iterator i = projects.iterator(); i.hasNext();  ) {
				Project p = (Project) i.next();
				if(! p.getClean()) {
					dirtyProjects.add(p);
				}
			}
		}
		return dirtyProjects;
	}
	
	/**
	 * Indicates whether at least one of the projects currently opened 
	 * need to be saved.
	 * @return true if one of the project needs to be saved.
	 */
	public boolean isDirty()
	{
		for(Iterator i = projects.iterator(); i.hasNext();  ) {
			Project p = (Project) i.next();
			if(! p.getClean()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Opens an ant build file, parses it and prompts the results to the user.
	 * @param project file name in a String.
	 */
	public void openProject(String filename) {
	    
	}
	
	/**
	 * User asked to quit. 
	 * This function checks for project that possibly need to be saved, and 
	 * asks the user wether he wants to save those ones or not.
	 */
	public void quit()
	{
		int projectsNumber = projects.size();
		
		if (isDirty()) {
		    Object toSave[] = vui.getToSave();
		    saveProjects(toSave);
			System.exit(0);
		}
		System.exit(0);
	}
	
	/**
	 *  Saves specified projects.
	 * The export format is an xml build file.
	 * @param toSave array of the projects that we want to save.
	 */
	public void saveProjects(Object[] toSave)
	{
		if(toSave != null) {
			for (int i=0; i < toSave.length; i++ ) {
//				TODO : Implement the actual saving of projects that needs to be saved.
				System.out.println((
						(Project)toSave[i]).getName()
						+ " should have been saved");
				// vaeExport should throw VaeExportException in case of
				// trouble, this call should be surrounded by a try/catch.
//				vaeExport.project2ant((Project) toSave[i]);
			}
		}
	}
	
	/**
	 * Signify an error in the status bar.
	 * @param error message to be displayed in the taskbar.
	 */
	public void signifyError(String error)
	{
		vui.signifyError(error);
	}
	
	/**
	 * Sends some information to the user.
	 * This type is one way kind : perfect for status bar.
	 */
	public void signifyMessage(String message) {
		vui.signifyMessage(message);
	}
}
