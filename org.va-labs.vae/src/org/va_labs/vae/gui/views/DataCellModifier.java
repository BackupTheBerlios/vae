/*
 * Created on Aug 22, 2004
 *
 * $Id: DataCellModifier.java,v 1.1 2004/09/02 22:48:04 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.views;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.internal.ExceptionHandler;
import org.va_labs.vae.Messages;
import org.va_labs.vae.core.TagAttribute;
import org.va_labs.vae.core.definitions.Project;
import org.va_labs.vae.core.definitions.Tag;
import org.va_labs.vae.exceptions.IllegalAttributeException;
import org.va_labs.vae.exceptions.NoSuchAttributeException;

/**
 * @author amine
 *
 * Makes the attributes table editable.
 */
public class DataCellModifier implements ICellModifier {

    /**
     * Project to which this modifier is linked to.
     * This is used to propagate the changes from gui to core components.
     */
    private Project project;
    
    /**
     * Reference to the table viewer.
     * Used to refresh the display after a change.
     */
    private TableViewer viewer;
    
    /**
     * Reference to the exception handler. 
     */
    private ExceptionHandler exceptionHandler;

    public DataCellModifier(TableViewer tableViewer) {
        exceptionHandler = ExceptionHandler.getInstance();
    }
    
    /**
     * For now all columns are editable.
     * This could change on a per element policy.
     * @param element element which is currently considered by the user.
     * @param property name of the column the user wants to edit for the 
     * element.
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element, String property) {
        return true;
    }

    /**
     * Indicates which value should be set when the user starts modifying.
     * Here we make the user start we the original value
     * @param element the element that is being edited.
     * @param property property of the element that is being edited.
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
     */
    public Object getValue(Object element, String property) {
        if (element instanceof TagAttribute) {
			TagAttribute tagAttribute = (TagAttribute) element;
			if(property.equals(Messages.getString("Name_Column")) ) {
				return tagAttribute.getName();
			} else if( property.equals(Messages.getString("Name_Column"))) {
				return tagAttribute.getValue(); 
			} else {
			    // TODO Use the exception handler to do something sensible.
			}
		}
		
        // TODO Use the exception handler to do something sensible.
		System.out.println("Problem : asking edition for a non TagAttribute " +
				"object.");
		System.out.println("Please signal this problem to the Vae development " +
				"team.");
		return null;
    }

    /**
     * We propagate the change to the edited tagAttribute.
	 * @param element element of the table that has been modified by the user.
	 * @param property property that has been modified in the considered element.
	 * @param value new value for the property.
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
     */
    public void modify(Object element, String property, Object value) {
        TableItem tableItem = (TableItem) element;
		// TODO use Exception handler in case the cast doesn't work.
		TagAttribute tagAttribute = (TagAttribute) tableItem.getData();
		Tag tag = tagAttribute.getTag();
		if (tag != null) {
			try {
				if (property.equals(Messages.getString("Name_Column"))) {
					tag.updateAttribute(tagAttribute.getName(), 
							(String)value, tagAttribute.getValue());
				} else {
					String name = tagAttribute.getName();
					tag.updateAttribute(name, name, 
							(String)value);
				}
				project.setClean(false);
			} catch (IllegalAttributeException e) {
				exceptionHandler.handleException(e);
				return;
			} catch (NoSuchAttributeException e) {
				exceptionHandler.handleException(e);
				return;
			}
		}
		// Updates the table viewer to show the new value.
		viewer.update(tagAttribute, new String[] { property} );
    }

    public void setProject(Project p) {
        project = p;
    }
}
