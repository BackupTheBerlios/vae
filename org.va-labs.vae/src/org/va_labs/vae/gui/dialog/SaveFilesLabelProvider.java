/*
 * Created on Sep 4, 2004
 *
 * $Id: SaveFilesLabelProvider.java,v 1.1 2004/09/05 00:16:43 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.dialog;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.va_labs.vae.tag.project.Project;

/**
 * @author mojo_jojo
 *
 * Provides label info concerning the Save Files dialog.
 */
public class SaveFilesLabelProvider implements ILabelProvider {
    /**
	 * Returns an image associated to a build file.
	 * We don't use this for now, but we can allow in the future to associate 
	 * images to some builds. 
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object project) 
	{
		return null;
	}

	/**
	 * Returns the text that needs to be associated to the project.
	 * @param project project that has to be saved.
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object project) 
	{
		return ((Project) project).getName();
	}

	/**
	 * Adds a listener to this label provider. Has no effect if an identical
	 * listener is already registered. 
	 * Label provider listeners are informed about state changes that affect 
	 * the rendering of the viewer that uses this label provider. 
	 * Not used here at all so not implemented.
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener arg0) 
	{
	
	}

	/**
	 * This class doesn't allocate any ressource, so we don't need to do 
	 * anything here.
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() 
	{

	}

	/**
	 * Returns whether the label would be affected by a change to the given 
	 * property of the given element. This can be used to optimize a 
	 * non-structural viewer update. If the property mentioned in the update
	 * does not affect the label, then the viewer need not update the label.
	 * Not used in this case so we return false all the time.
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) 
	{
		return false;
	}

	/**
	 * Removes a listener to this label provider. Has no affect if an identical
	 * listener is not registered.
	 * Not used in this case, so not implemented.
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener arg0) 
	{
		
	}
}
