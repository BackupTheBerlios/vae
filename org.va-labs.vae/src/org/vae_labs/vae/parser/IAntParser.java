/*
 * Created on Sep 4, 2004
 *
 * $Id: IAntParser.java,v 1.1 2005/04/05 02:45:24 mojo_jojo Exp $
 */
package org.vae_labs.vae.parser;

import java.io.File;

import org.vae_labs.vae.tag.project.Project;

/**
 * @author mojo_jojo
 * 
 * Interface that hold the operation that needs to be performed by the Xml
 * parser on the build.xml file...
 */
public interface IAntParser {

    /**
     * Checks if the build file is correct according to the Ant build files dtd.
     * Note : This method is optional as no official dtd is avalaible for ant
     * files.
     */
    public void check();

    /**
     * Load the information relative to the project from the build file.
     * 
     * @param buildFile
     *            File to be parsed.
     * 
     * @return nothing.
     */
    public void parseProject(File buildFile) throws VaeParsingException;

    /**
     * Returns an object representing the parsed information.
     * 
     * @return a org.vae.core.Project holding all the information.
     */
    public Project getProject();
}