/*
 * Created on Aug 19, 2004
 *
 * $Id: SwtVae.java,v 1.1 2004/08/20 19:49:04 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.views.elements;

import org.va_labs.vae.core.Vae;
import org.va_labs.vae.exceptions.IllegalAttributeException;
import org.va_labs.vae.exceptions.NoSuchAttributeException;

/**
 * @author mojo_jojo
 * Adds Swt methods to the core Vae class.
 * This class will serve as input for the projects tree.
 */
public class SwtVae implements SwtElement {
    
    /**
     * Reference to the core instance of Vae that this class extends for swt.
     */
    private Vae vae;
    
    public SwtVae( Vae vae) {
        this.vae = vae;
    }

    /**
     * Vae doesn't have any attributes for now. 
     * It could be used later as a way to handle the vae preferences.
     * @see org.va_labs.vae.gui.views.elements.SwtElement#addAttribute(java.lang.String, java.lang.String)
     */
    public void addAttribute(String attributeName, String attributeValue)
            throws IllegalAttributeException {

    }

    /**
     * Vae doesn't have any attributes for now. 
     * It could be used later as a way to handle the vae preferences.
     * @see org.va_labs.vae.gui.views.elements.SwtElement#getAttributes()
     */
    public Object[] getAttributes() {
        return null;
    }

    /**
     * Returns the children items (ie projects) that have to be displayed in
     * the projects tree.
     * @see org.va_labs.vae.gui.views.elements.SwtElement#getChildren()
     */
    public Object[] getChildren() {
        // TODO Implement getChildren
        return new Object[0];
    }

    /** 
     * This element is the root of the tree.
     * @return this to indicate that we don't have any parent element.
     * @see org.va_labs.vae.gui.views.elements.SwtElement#getParent()
     */
    public Object getParent() {
        return this;
    }

    /**
     * This one is not used, but we provide a little something.
     * @see org.va_labs.vae.gui.views.elements.SwtElement#getText()
     */
    public String getText() {
        return "SwtVae";
    }
    
    /** 
     * Indicates wether there are children items (ie projects) to be
     * displayed in the tree.
     * @see org.va_labs.vae.gui.views.elements.SwtElement#hasChildren()
     */
    public boolean hasChildren() {
        // TODO Implement hasChildren.
        return false;
    }

    /**
     * Vae doesn't have any attributes for now. 
     * It could be used later as a way to handle the vae preferences.
     * @see org.va_labs.vae.gui.views.elements.SwtElement#removeAttribute(java.lang.String)
     */
    public void removeAttribute(String attributeName)
            throws NoSuchAttributeException {
        
    }

    /**
     * Vae doesn't have any attributes for now. 
     * It could be used later as a way to handle the vae preferences.
     * @see org.va_labs.vae.gui.views.elements.SwtElement#setAttributes(java.lang.Object[])
     */
    public void setAttributes(Object[] attributes)
            throws IllegalAttributeException {

    }

    /**
     * Don't do anythign as the text doesn't get displayed because this is the
     * root attribute.
     * @see org.va_labs.vae.gui.views.elements.SwtElement#setText(java.lang.String)
     */
    public void setText(String newName) {

    }

    /**
     * Vae doesn't have any attributes for now. 
     * It could be used later as a way to handle the vae preferences.
     * @see org.va_labs.vae.gui.views.elements.SwtElement#updateAttribute(java.lang.String, java.lang.String, java.lang.String)
     */
    public void updateAttribute(String originalName, String newName,
            String value) throws NoSuchAttributeException,
            IllegalAttributeException {

    }
}