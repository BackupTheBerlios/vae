/*
 * Created on Aug 13, 2004
 *
 * $Id: ProjectTreeContentProvider.java,v 1.1 2004/08/17 22:41:51 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.views;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.va_labs.vae.gui.views.elements.SwtElement;

/**
 * @author mojo_jojo
 * This class handles the content of the Project Tree.
 */
public class ProjectTreeContentProvider implements IContentProvider {

    
    /**
     * Disposes of this ContentProvider.
     * For now nothing to be disposed.
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {

    }

    /** 
	 * Returns children (if any) of the object given as argument.
	 * @param arg0 the TreeItem whom we want the children.
	 * @return The children in a Object array.
	 */
	public Object[] getChildren(Object object) {
		if(object instanceof SwtElement) {
			return ((SwtElement) object).getChildren();
		} else {
			// TODO Implement the exception handler and use it here.
		    return null;
		}
	}

	/**
	 * Returns the parent of the object given as argument.
	 * @param arg0 the TreeItem whom we want the parent.
	 * @return the parent as an Object.
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object arg0) 
	{
		if(arg0 instanceof SwtElement) {
			return ((SwtElement) arg0).getParent();
		} else {
		    	// TODO Implement the exception handler and use it here.
		    return null;
		}
	}

	/**
	 * Indicates wether or not an element has children in the tree.
	 * @param object the element we wish to have an answer on.
	 * @return true if the element has children.
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		if(object instanceof SwtElement) {
			return ((SwtElement) object).hasChildren();
		} else {
		    // TODO Implement the exception handler and use it here.
			return false;
		}
	}

	/**
	 * Called whenever a TreeViewer.setinput() call is made.
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object object) 
	{
		if (object instanceof SwtElement) {
			return ((SwtElement) object).getChildren();
		} 
		// TODO Implement the exception handler and use it here.
	    return null;
	}
	
    /**
     * Notifies this content provider that the given viewer's input  has been 
     * switched to a different element.
     * For now this is not used.
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

    }

}
