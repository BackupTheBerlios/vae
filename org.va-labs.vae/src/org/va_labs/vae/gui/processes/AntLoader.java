/*
 * Created on Sep 4, 2004
 *
 * $Id: AntLoader.java,v 1.1 2004/09/05 00:18:12 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.processes;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.va_labs.vae.gui.Vui;
import org.va_labs.vae.parser.IAntParser;

/**
 * @author mojo_jojo
 *
 * Loads build file, and transfers the results to the user interface.
 * It's pretty much a pleasure to code this : the load is so fast that it
 * doesn't really need this kind of thing.
 */
public class AntLoader implements IRunnableWithProgress {
    /**
	 * Path to the build file.
	 */
	private StringBuffer buildFile;
	/**
	 * Gui we are related to.
	 */
	private Vui gui;
	
	/**
	 * Parser that will load the build file.
	 */
	private IAntParser parser;
	
	/**
	 * Creates the process in charge of loading a build file.
	 * @param the gui interface that will display the project.
	 */
	public AntLoader(Vui vui, IAntParser antParser)
	{
		gui = vui;
		parser = antParser;
	}

	/**
	 * 
	 * @see org.vae.core.BuildLoader#loadBuild()
	 */
	public void loadBuild() throws LoadFailedException
	{
		try {
			new ProgressMonitorDialog(gui.getShell()).run(false, true, this);
		} catch(InterruptedException e) {
			// Nothing to be done : interrupted at user's demand.
		} catch(InvocationTargetException e) {
			throw new LoadFailedException(e);
		}
	}

	/**
	 * Opens a build file, parses it and prompts the results to the user.
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(IProgressMonitor progressMonitor)
		throws InvocationTargetException, InterruptedException
	{
		try {
			progressMonitor.beginTask("Loading "+buildFile +"...", 4);
			progressMonitor.subTask("Opening "+buildFile+"...");
			parser.open(buildFile.toString());
			if(progressMonitor.isCanceled()) {
				return;
			}
			progressMonitor.worked(1);
			
			progressMonitor.subTask("Parsing "+buildFile+"...");
			parser.parseProject();
			if(progressMonitor.isCanceled()) {
				return;
			}
			progressMonitor.worked(1);
			
			progressMonitor.subTask("Transfering the results to the Vae User Interface...");
			gui.displayProject(parser.getProject());
			if(progressMonitor.isCanceled()) {
				return;
			}
			progressMonitor.worked(1);
			
			progressMonitor.subTask("Starting background checking process...");
			// TODO : Design & Implement the process in charge of checking the build file integrity.
			if(progressMonitor.isCanceled()) {
				return;
			}
			progressMonitor.done();
			
		} catch(Exception e) {
			progressMonitor.setCanceled(true);
			throw new InvocationTargetException(e);
		}
	}

	/**
	 * Sets the build we want to load.
	 * @see org.vae.core.BuildLoader#setBuildName(java.lang.String)
	 */
	public void setBuildName(String filename)
	{
		buildFile = new StringBuffer(filename);
	}

	/**
	 * Sets the parser that is used to load the file.
	 * @see org.vae.core.BuildLoader#setParser(org.vae.xml.Xml)
	 */
	public void setParser(IAntParser antParser)
	{
		parser = antParser;
	}
}
