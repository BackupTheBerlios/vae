/*
 * Created on Sep 4, 2004
 *
 * $Id: VaeExport.java,v 1.2 2005/03/05 15:47:40 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.processes;

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
}
