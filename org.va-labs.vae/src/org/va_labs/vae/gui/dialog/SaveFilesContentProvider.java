/*
 * Created on Sep 4, 2004
 *
 * $Id: SaveFilesContentProvider.java,v 1.1 2004/09/05 00:16:43 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.dialog;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.va_labs.vae.core.Vae;

/**
 * @author mojo_jojo
 *
 * This classe provides content to the save files dialog.
 */
public class SaveFilesContentProvider implements IStructuredContentProvider {
    /**
	 * Returns the elements to be displayed.
	 * @param input vae instance that pilots the app.
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object input) 
	{
		return ((Vae) input).getDirtyProjects().toArray();
	}

	/**
	 * Nothing to be done as we don't allocate any resource in this class.
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() 
	{
		
	}

	/**
	 * This should never happen and therefore is not implemented.
	 * Note : Maybe an exception should be thrown to catch such cases.
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
	{
		
	}
}
