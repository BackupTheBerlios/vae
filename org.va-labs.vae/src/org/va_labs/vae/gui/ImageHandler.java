/*
 * Created on Aug 16, 2004
 *
 * $Id: ImageHandler.java,v 1.1 2004/08/17 22:41:51 mojo_jojo Exp $
 */
package org.va_labs.vae.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;

/**
 * @author mojo_jojo
 * Handles all kind of image related resources.
 */
public class ImageHandler {

    /**
     * Actual ImageRegistry we are going to use to handle the image
     */
    private static ImageRegistry registry;
	
    /**
     * Path to the Image resources directory.
     */
	private static StringBuffer resourceDir; 
	
	static {
		resourceDir=new StringBuffer("org/va_labs/gui/resources/");
	}
	
	/**
	 * This class is not meant to be instanciated, but accessed directly 
	 * through its static members (image utility class).
	 */
	private ImageHandler()
	{
	
	}
	
	/**
	 * Returns the imageRegistry of the Swt Vae User Interface.
	 * This one is created only once for all components that uses it (singleton).
	 * At creation, all the images present in the resource directory are loaded in the
	 * registry. We do not go inside subdirectories : they are just ignored.
	 * @return The ImageRegistry
	 */
	public static ImageRegistry getRegistry()
	{
		if(registry == null) {
			
			registry = new ImageRegistry();
			// Loads all the image that are in the ressource directory.
			File[] resources = (new File(resourceDir.toString())).listFiles();
			if(resources.length > 0) {
				for(int i = 0; i < resources.length; i++) {
					File resource = resources[i];
					if( resource.isFile()) {
						addToRegistry(resource.getName());
					} 
				}
			}
		}
		return registry;
	}

	/**
	 * Add to the registry an image.
	 * The image is loaded in the registry with a key equal to the name without
	 * the final extension.
	 * open.png is registered with the key {open}.
	 * @param imagePath the relative path to the image from the ressource 
	 * directory.
	 */
	private static void addToRegistry(String imagePath)
	{
		try {
			StringBuffer url = new StringBuffer("file:"+resourceDir+imagePath);
			int finalDot = imagePath.lastIndexOf(".");
			registry.put(imagePath.substring(0, finalDot), 
			        ImageDescriptor.createFromURL(new URL(url.toString())));
		} catch (MalformedURLException ex) {
			throw new RuntimeException( "Image not found error", ex);
		}
	}
}
