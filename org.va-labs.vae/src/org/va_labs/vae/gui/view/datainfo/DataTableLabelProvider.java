/*
 * Created on Aug 22, 2004
 *
 * $Id: DataTableLabelProvider.java,v 1.1 2004/09/05 00:23:04 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.view.datainfo;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.va_labs.vae.core.TagAttribute;

/**
 * @author amine
 *
 * Provides display info for the attributes table.
 */
public class DataTableLabelProvider implements ITableLabelProvider {

    /**
     * For now no images for attributes.
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    /**
     * Get the text to be displayed from the tagAttribute information.
     * @param element element that is currently selected in the project tree.
     * @param columnIndex index of the column which information needs to be 
     * retrieved.
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     */
    public String getColumnText(Object element, int columnIndex) {
        if (element instanceof TagAttribute) {
			TagAttribute tagAttribute = (TagAttribute) element;
			if (columnIndex ==0 ) {
				// Display the name of the attribute
				return tagAttribute.getName();
			} else if ( columnIndex == 1) {
				// Display the value of the attribute
				return tagAttribute.getValue(); 
			} else {
				// TODO : Use the exception handler to do something sensible 
			    // here.
			}
		}
		System.out.println("Problem : asking to display info in attribute table from an object that is not a TagAttribute.");
		System.out.println("Please signal this problem to the Vae development team.");
		// TODO : Use the exception handler to do something sensible here.
		return null;
    }

    /**
     * Label provider listeners are informed about state changes that affect
     * the rendering of the viewer that uses this label provider.
     * For now, this feature is unavailable.
     * @param listener listener that should be kept informed.
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void addListener(ILabelProviderListener listener) {

    }

    /**
     * Disposes of this Label Provider.
     * For now, nothing to be done.
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose() {
        
    }

    /**
     * Returns whether the label would be affected by a change to the given
     * property of the given element.
     * The properties follow the changes of the element.
     * TODO Figure out what is this thing really about.
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    public boolean isLabelProperty(Object element, String property) {
        System.out.println("islabelProperty captured :");
        System.out.println("element : "+element+" property : "+property);
        return true;
    }

    /**
     * Removes a listener from the listener list.
     * Right now, listeners can't be used with this labelProvider.
     * @param listener listener to be removed from the list.
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void removeListener(ILabelProviderListener listener) {

    }

}
