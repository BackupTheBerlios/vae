/*
 * Created on Sep 4, 2004
 *
 * $Id: VaeExport.java,v 1.1 2005/04/05 02:45:23 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.processes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.vae_labs.vae.tag.project.Project;

/**
 * @author mojo_jojo
 *
 * Exports the build file in various format.
 */
public class VaeExport {
    
    /**
     * Singleton instance of VaeExport class.
     */
    private static VaeExport vaeExport = null;
    
    /**
     * Gives the lone instance of VaeExport class.
     * 
     * Creates the class if needed.
     * @return the VaeExport singleton.
     */
    public static VaeExport getInstance() {
        if (vaeExport == null) {
            vaeExport = new VaeExport();
        }
        return vaeExport;
    }
    
    /**
     * Nothing to be done for now.
     *
     */
    public VaeExport() {
        
    }
    
    /**
     * Saves a given project in a specified file.
     * 
     * @param project
     *            project to be saved.
     * @param filePath
     *            file in which the project needs to be saved.
     */
    public void toFile(Project project, String filePath) {
        StringBuffer buffer = project.toXml();
        
        try {
            FileOutputStream ostream = new FileOutputStream(filePath);
            PrintStream pstream = new PrintStream(ostream);
            pstream.print(buffer);
            pstream.close();
        } catch (FileNotFoundException e) {
            // TODO Hanldle FileNotFoundException at the vae level.
            e.printStackTrace();
        }
    }
}
