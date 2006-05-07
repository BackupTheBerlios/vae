/*
 * Created on Sep 6, 2004
 *
 * $Id: ProjectTreeListener.java,v 1.2 2006/05/07 10:49:15 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.view.datatree.listener;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.vae_labs.vae.gui.Vui;
import org.vae_labs.vae.gui.tag.ISwtElement;

/**
 * @author mojo_jojo This listener catch selection events. If an element of the
 *         tree has been clicked on twice (but not double-clicked), and edition
 *         of the element is launched. But if the element is newly clicked it
 *         serves as input to the data table so it can show its attributes.
 */
public class ProjectTreeListener implements ISelectionChangedListener {

    /**
     * Reference to the vae user interface that we will keep updated of the
     * changes.
     */
    private Vui vui;

    /**
     * Reference to the previously selected object. We use this to see if the
     * user wants to start editing the current object or not.
     */
    private Object previousSelection;

    /**
     * Listens to the changes on the project tree, and transmit them to the Vae
     * user interface.
     * 
     */
    public ProjectTreeListener() {
        vui = Vui.getInstance();
    }

    /**
     * We catch the selection and send the first selected element to the
     * registered viewer.
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        IStructuredSelection selection = (IStructuredSelection) event
                .getSelection();
        Object o = selection.getFirstElement();

        if (o != previousSelection) {
            vui.setCurrentElement((ISwtElement) o);
            previousSelection = o;
        }
    }

}