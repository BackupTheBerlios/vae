/*
 * Created on Aug 22, 2004
 *
 * $Id: DataCellModifier.java,v 1.1 2005/04/05 02:45:26 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.view.datainfo;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;
import org.vae_labs.vae.Messages;
import org.vae_labs.vae.core.TagAttribute;
import org.vae_labs.vae.core.Vae;
import org.vae_labs.vae.parser.IllegalAttributeException;
import org.vae_labs.vae.tag.NoSuchAttributeException;
import org.vae_labs.vae.tag.Tag;

/**
 * @author mojo_jojo
 * 
 * Makes the attributes table editable.
 */
public class DataCellModifier implements ICellModifier {

    /**
     * Reference to the table viewer.
     * 
     * Used to refresh the display after a change.
     */
    private TableViewer viewer;

    /**
     * Sets the reference to the exception handler and to the TableViewer.
     *  
     */
    public DataCellModifier(TableViewer tableViewer) {
        viewer = tableViewer;
    }

    /**
     * For now all columns are editable.
     * 
     * This could change on a per element policy.
     * 
     * @param element
     *            element which is currently considered by the user.
     * @param property
     *            name of the column the user wants to edit for the element.
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object,
     *      java.lang.String)
     */
    public boolean canModify(Object element, String property) {
        return true;
    }

    /**
     * Indicates which value should be set when the user starts modifying.
     * 
     * Here we make the user start we the original value.
     * 
     * @param element
     *            the element that is being edited.
     * @param property
     *            property of the element that is being edited.
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object,
     *      java.lang.String)
     */
    public Object getValue(Object element, String property) {
        if (element instanceof TagAttribute) {
            TagAttribute tagAttribute = (TagAttribute) element;
            if (Messages.getString("Name_Column").equals(property)) {
                return tagAttribute.getName();
            } else if (Messages.getString("Value_Column").equals(property)) {
                return tagAttribute.getValue();
            } else {
                // TODO Use the exception handler to do something sensible.
                System.out.println("Wrong column number in DataCellModifier."
                        + "Please notify this problem to the Vae "
                        + "development team.");
            }
        }

        // TODO Use the exception handler to do something sensible.
        System.out
                .println("Problem : asking to edit a non TagAttribute object : "
                        + element);
        System.out
                .println("Please signal this problem to the Vae development team.");
        return null;
    }

    /**
     * We propagate the change to the edited tagAttribute.
     * 
     * @param element
     *            element of the table that has been modified by the user.
     * @param property
     *            property that has been modified in the considered element.
     * @param value
     *            new value for the property.
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object,
     *      java.lang.String, java.lang.Object)
     */
    public void modify(Object element, String property, Object value) {
        TableItem tableItem = (TableItem) element;
        // TODO (modify) use Exception handler in case the cast doesn't work.
        TagAttribute tagAttribute = (TagAttribute) tableItem.getData();
        Tag tag = tagAttribute.getTag();
        if (tag != null) {
            try {
                if (property.equals(Messages.getString("Name_Column"))) {
                    tag.updateAttribute(tagAttribute.getName(), value
                            .toString(), tagAttribute.getValue());
                } else {
                    String name = tagAttribute.getName();
                    tag.updateAttribute(name, name, value.toString());
                }
                Vae.getInstance().getVui().refresh(tagAttribute);
            } catch (IllegalAttributeException e) {
                Vae.getInstance().getExceptionHandler().handle(e);
                return;
            } catch (NoSuchAttributeException e) {
                Vae.getInstance().getExceptionHandler().handle(e);
                return;
            }
        }
    }
}