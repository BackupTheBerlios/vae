/*
 * Created on Aug 13, 2004
 *
 * $Id: ProjectTreeLabelProvider.java,v 1.8 2005/03/07 21:22:35 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.view.datatree;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.va_labs.vae.gui.ImageHandler;
import org.va_labs.vae.gui.tag.ISwtElement;
import org.va_labs.vae.gui.tag.SwtNestedElement;
import org.va_labs.vae.gui.tag.project.SwtProject;
import org.va_labs.vae.gui.tag.property.SwtProperty;
import org.va_labs.vae.gui.tag.target.SwtTarget;
import org.va_labs.vae.gui.tag.task.SwtTask;

/**
 * @author mojo_jojo 
 * 
 * Provides labels to the project tree from the node.
 */
public class ProjectTreeLabelProvider implements ILabelProvider {

    /**
     * Adds a listener to this label provider.
     * 
     * Has no effect if an identical listener is already registered. Label
     * provider listeners are informed about state changes that affect the
     * rendering of the viewer that uses this label provider. We don't change
     * the rendering of the viewer for now, so there is nothing in here.
     * 
     * @param listener
     *            a label provider listener that wants to be kept informed.
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void addListener(ILabelProviderListener listener) {

    }

    /**
     * Disposing of the resources used by this class. Nothing to be done for
     * now.
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose() {

    }

    /**
     * Utility of this method still unclear. TODO: Investigate what
     * isLabelProperty is used for.
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
     *      java.lang.String)
     */
    public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Returns an image corresponding the element.
     * 
     * @param object
     *            the object we are looking for the corresponding image.
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(Object object) {
        if (object instanceof SwtTask || object instanceof SwtNestedElement) {
            return ImageHandler.getRegistry().get("vae_task");
        } else if (object instanceof SwtTarget) {
            return ImageHandler.getRegistry().get("vae_target");
        } else if (object instanceof SwtProperty) {
            return ImageHandler.getRegistry().get("vae_property");
        } else {
            return ImageHandler.getRegistry().get("vae_unidentified_tag");
        }
    }

    /**
     * Provides the text corresponding to the given element.
     * 
     * @param The
     *            element we are seeking the text associated to.
     * @return The text associated to the text in a string.
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    public String getText(Object element) {
        if (element instanceof SwtProject) {
            String name = ((ISwtElement) element).getText();
            if (!((SwtProject) element).getClean()) {
                name += " *";
            }
            return name;
        } else if (element instanceof ISwtElement) {
            return ((ISwtElement) element).getText();
        } else {
            // TODO Use the exception handler to do something sensible here.
            System.err.println("Asked for the label of unsupported element.");
            return null;
        }
    }

    /**
     * Removes a listener from the list of those that need to be updated in case
     * of a change that affects the rendering of the view. As we don't use this,
     * nothing is made here.
     * 
     * @param listener
     *            listener to be deleted from the list.
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void removeListener(ILabelProviderListener listener) {

    }

}