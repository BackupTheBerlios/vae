/*
 * Created on Aug 16, 2004
 *
 * $Id: ImageHandler.java,v 1.6 2005/02/27 23:51:00 mojo_jojo Exp $
 */
package org.va_labs.vae.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.va_labs.vae.Messages;
import org.va_labs.vae.VaeException;
import org.va_labs.vae.core.Vae;

/**
 * @author mojo_jojo 
 * 
 * Handles all kind of image related resources.
 */
public class ImageHandler {

    /**
     * Actual ImageRegistry we are going to use to handle the image
     */
    private static ImageRegistry registry;

    /**
     * This class is not meant to be instanciated, but accessed directly through
     * its static members (image utility class).
     */
    private ImageHandler() {

    }

    /**
     * Returns the imageRegistry of the Swt Vae User Interface.
     * 
     * This one is created only once for all components that uses it
     * (singleton). At creation, all the images present in the resource
     * directory are loaded in the registry.
     * 
     * We do not go inside subdirectories : they are just ignored.
     * 
     * @return The ImageRegistry
     */
    public static ImageRegistry getRegistry() {
        if (registry == null) {
            registry = new ImageRegistry();
            // Gets the resourceDir from the Messages.

            String resourceDir = Messages.getString("Resource_Dir");
            // Loads all the image that are in the ressource directory.
            File[] resources = (new File(resourceDir)).listFiles();
            if (resources != null) {
                for (int i = 0; i < resources.length; i++) {
                    File resource = resources[i];
                    if (resource.isFile()) {
                        addToRegistry(resource.getName());
                    }
                }
            } else {
                VaeException e = new VaeException("Vae User Interface",
                        "Image registery init failed.",
                        "Couldn't load the resource directory", Vae.VAE__OK);
                Vae.getInstance().acknowledgeVaeException(e);
            }
        }
        return registry;
    }

    /**
     * Add to the registry an image.
     * 
     * The image is loaded in the registry with a key equal to the name without
     * the final extension. open.png is registered with the key {open}.
     * 
     * @param imagePath
     *            the relative path to the image from the ressource directory.
     * 
     * TODO (addToRegistry): Use VaeException instead of RuntimeException.
     */
    private static void addToRegistry(String imagePath) {
        try {
            StringBuffer url = new StringBuffer("file://"
                    + Messages.getString("Resource_Dir") + "/" + imagePath);
            int finalDot = imagePath.lastIndexOf(".");
            System.out.println("Url: "+url.toString());
            registry.put(imagePath.substring(0, finalDot), ImageDescriptor
                    .createFromURL(new URL(url.toString())));
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Image not found error", ex);
        }
    }
}