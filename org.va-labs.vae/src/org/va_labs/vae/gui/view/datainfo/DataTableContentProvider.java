/*
 * Created on Aug 22, 2004
 *
 * $Id: DataTableContentProvider.java,v 1.1 2004/09/05 00:23:04 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.view.datainfo;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.va_labs.vae.gui.tag.ISwtElement;

/**
 * @author mojo_jojo
 *
 * Content provider for the attribute table.
 */
public class DataTableContentProvider implements IStructuredContentProvider {

	/**
	 * Disposes of this AttributesTableContentProvider
	 * For now nothing to be done here.
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {

	}

	/**
	 * Called by jface/swt if a change of input occures.
	 * For now nothing to be done : we don't listen to any change of the inputs
	 * yet.
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}
	
	/**
	 * Gets the attributes to be displayed for a tree element.
	 * @param treeElement the currently selected tree element as an object.
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object treeElement)
	{
		if(treeElement instanceof ISwtElement) {
			return ((ISwtElement)treeElement).getAttributes();
		} else {
			System.out.println("Problem : Asked for the elements of an " +
					"unidentified object, please signal this to the " +
					"Vae team.");
			// TODO Use the exception handler to do something sensible here.
			return null;
		}
	}

}
