/*
 * Created on Sep 4, 2004
 *
 * $Id: IAntParser.java,v 1.1 2004/09/05 00:30:54 mojo_jojo Exp $
 */
package org.va_labs.vae.parser;

import java.io.FileNotFoundException;

import org.va_labs.vae.tag.project.Project;

/**
 * @author mojo_jojo
 *
 * Interface that hold the operation that needs to be performed by the
 * Xml parser on the build.xml file...
 */
public interface IAntParser {
    /**
	 * Opens a build file.
	 * @param filename the name of the file to be opened.
	 */
	public void open(String filename) throws FileNotFoundException;
	
	/**
	 * Closes the current build file. It will save the 
	 * file it needs to.
	 */
	public void close();
	
	/**
	 * Saves the current build file.
	 */
	public void save();
	
	/**
	 * Checks if the build file is correct according to the
	 * Ant build files dtd.
	 * Note : This method is optional as no official dtd is avalaible for ant
	 * files.
	 */
	public void check();
	
	/**
	 * Load the information relative to the project from the
	 * build file.
	 * @return nothing.
	 */
	public void parseProject() throws VaeParsingException;
	
	/**
	 * Returns the project as it is described in the build file.
	 * @return a org.vae.core.Project holding all the information.
	 */
	public Project getProject();
}
