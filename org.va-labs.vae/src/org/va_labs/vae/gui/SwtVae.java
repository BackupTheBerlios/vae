/*
 * Created on Aug 19, 2004
 *
 * $Id: SwtVae.java,v 1.2 2004/09/05 20:32:36 mojo_jojo Exp $
 */
package org.va_labs.vae.gui;

import org.va_labs.vae.core.Vae;
import org.va_labs.vae.gui.tag.ISwtElement;
import org.va_labs.vae.parser.IllegalAttributeException;
import org.va_labs.vae.tag.NoSuchAttributeException;

/**
 * @author mojo_jojo Adds Swt methods to the core Vae class. This class will
 *         serve as input for the projects tree. TODO : See what to do with the
 *         SwtVae. Shall it become the tree input provider.
 */
public class SwtVae implements ISwtElement {

    /**
     * Reference to the core instance of Vae that this class extends for swt.
     */
    private Vae vae;

    public SwtVae(Vae vae) {
        this.vae = vae;
    }

    /**
     * Vae doesn't have any attributes for now. It could be used later as a way
     * to handle the vae preferences.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#addAttribute(java.lang.String,
     *      java.lang.String)
     */
    public void addAttribute(String attributeName, String attributeValue)
            throws IllegalAttributeException {

    }

    /**
     * Vae doesn't have any attributes for now. It could be used later as a way
     * to handle the vae preferences.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#getAttributes()
     */
    public Object[] getAttributes() {
        return null;
    }

    /**
     * Returns the children items (ie projects) that have to be displayed in the
     * projects tree.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#getChildren()
     */
    public Object[] getChildren() {
        return new Object[0];
    }

    /**
     * This element is the root of the tree.
     * 
     * @return this to indicate that we don't have any parent element.
     * @see org.va_labs.vae.gui.tag.ISwtElement#getParent()
     */
    public Object getParent() {
        return this;
    }

    /**
     * This one is not used, but we provide a little something.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#getText()
     */
    public String getText() {
        return "SwtVae";
    }

    /**
     * Indicates wether there are children items (ie projects) to be displayed
     * in the tree.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#hasChildren()
     */
    public boolean hasChildren() {
        return false;
    }

    /**
     * Vae doesn't have any attributes for now. It could be used later as a way
     * to handle the vae preferences.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#removeAttribute(java.lang.String)
     */
    public void removeAttribute(String attributeName)
            throws NoSuchAttributeException {

    }

    /**
     * Vae doesn't have any attributes for now. It could be used later as a way
     * to handle the vae preferences.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#setAttributes(java.lang.Object[])
     */
    public void setAttributes(Object[] attributes)
            throws IllegalAttributeException {

    }

    /**
     * Don't do anythign as the text doesn't get displayed because this is the
     * root attribute.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#setText(java.lang.String)
     */
    public void setText(String newName) {

    }

    /**
     * Vae doesn't have any attributes for now. It could be used later as a way
     * to handle the vae preferences.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#updateAttribute(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void updateAttribute(String originalName, String newName,
            String value) throws NoSuchAttributeException,
            IllegalAttributeException {

    }
}