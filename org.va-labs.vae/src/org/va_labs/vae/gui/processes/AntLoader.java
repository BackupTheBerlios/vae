/*
 * Created on Sep 4, 2004
 *
 * $Id: AntLoader.java,v 1.6 2005/03/06 23:30:09 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.processes;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.va_labs.vae.core.Vae;
import org.va_labs.vae.gui.Vui;
import org.va_labs.vae.parser.IAntParser;

/**
 * @author mojo_jojo
 * 
 * Loads build file, and transfers the results to the vae core.
 * 
 * It's pretty much a bonus to code this : the load is so fast that it doesn't
 * really need this kind of thing.
 */
public class AntLoader implements IRunnableWithProgress {

    /**
     * Path to the build file.
     */
    private File buildFile;

    /**
     * Path + name of the build file.
     */
    private StringBuffer buildLocation;

    /**
     * Nothing to be done for now.
     */
    public AntLoader() {

    }

    /**
     * Loads the currenly set build file and register it to the vae.
     * 
     * @see org.vae.core.BuildLoader#loadBuild()
     */
    public void loadBuild() throws LoadFailedException {
        try {
            Vui vui = Vui.getInstance();
            new ProgressMonitorDialog(vui.getShell()).run(false, true, this);
        } catch (InterruptedException e) {
            // Nothing to be done : interrupted at user's demand.
        } catch (InvocationTargetException e) {
            throw new LoadFailedException(e);
        }
    }

    /**
     * Initialize the data : it's after this call that it is actually going to
     * be used. 
     * 
     * Opens a specified build file. Defined in org.vae.xml.Xml
     * 
     * @param filename
     *            the name of the build file to be opened.
     * @throws FileNotFoundException
     *             if the file couldn't be opened.
     */
    public void open(StringBuffer filename) throws FileNotFoundException {
        buildFile = new File(filename.toString());

        if (!buildFile.exists()) {
            throw new FileNotFoundException(filename.toString());
        }
    }

    /**
     * Opens a build file, parses it and transfers the results to the vae core.
     * 
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void run(IProgressMonitor progressMonitor)
            throws InvocationTargetException, InterruptedException {
        Vae vae = Vae.getInstance();
        IAntParser parser = vae.getAntParser();
        try {
            progressMonitor.beginTask("Loading " + buildLocation + "...", 4);
            progressMonitor.subTask("Opening " + buildLocation + "...");
            open(buildLocation);
            if (progressMonitor.isCanceled()) {
                return;
            }
            progressMonitor.worked(1);

            progressMonitor.subTask("Parsing " + buildLocation + "...");
            parser.parseProject(buildFile);
            if (progressMonitor.isCanceled()) {
                return;
            }
            progressMonitor.worked(1);

            progressMonitor
                    .subTask("Transfering the results to the Vae core...");
            vae.registerProject(buildLocation.toString(), parser.getProject());
            if (progressMonitor.isCanceled()) {
                return;
            }
            progressMonitor.worked(1);

            progressMonitor.subTask("Checking build structure...");
            // TODO : Design & Implement the process in charge of checking the
            // build file integrity.
            if (progressMonitor.isCanceled()) {
                return;
            }
            buildFile = null;
            progressMonitor.done();

        } catch (Exception e) {
            progressMonitor.setCanceled(true);
            throw new InvocationTargetException(e);
        }
    }

    /**
     * Sets the build we want to load.
     * 
     * @see org.vae.core.BuildLoader#setBuildName(java.lang.String)
     */
    public void setBuildFile(String filename) {
        buildLocation = new StringBuffer(filename);
    }
}