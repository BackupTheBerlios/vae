/*
 * Created on Dec 22, 2004
 *
 * $Id: SwtWorkspace.java,v 1.1 2005/01/11 00:03:46 mojo_jojo Exp $
 */
package org.va_labs.vae.tag.workspace;

import java.util.HashMap;

import org.va_labs.vae.gui.tag.ISwtElement;
import org.va_labs.vae.gui.tag.project.SwtProject;
import org.va_labs.vae.parser.IllegalAttributeException;
import org.va_labs.vae.tag.NoSuchAttributeException;

/**
 * @author mojo_jojo
 * 
 * SwtWorkspace reprensents the workspace of the Visual Ant Editor. It keeps a
 * track of the currently opened projects, and will in the future keep
 * additional informations regarding each one of them.
 */
public class SwtWorkspace implements ISwtElement {

    private HashMap projects;

    public SwtWorkspace() {
        projects = new HashMap();
    }

    /**
     * Used to add an attribute concerning the workspace. For now it is not in
     * use.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#addAttribute(java.lang.String,
     *      java.lang.String)
     */
    public void addAttribute(String attributeName, String attributeValue)
            throws IllegalAttributeException {

    }

    /**
     * Returns the attributes concerning the workspace. Note: Not in use for
     * now.
     * 
     * @return the attributes set for this workspace.
     */
    public Object[] getAttributes() {
        return null;
    }

    /**
     * Indicates which projects are being currenty opened in the workspace.
     * 
     * @return the open projects as an array of SwtProjects.
     * @see org.va_labs.vae.gui.tag.ISwtElement#getChildren()
     */
    public Object[] getChildren() {
        return projects.values().toArray();
    }

    /**
     * Indicates if this workspace handles opened projects.
     * 
     * @return true if there are opened projects.
     * @see org.va_labs.vae.gui.tag.ISwtElement#hasChildren()
     */
    public boolean hasChildren() {
        return projects.size() != 0;
    }

    /**
     * Indicates that this element is a parent element of the tree.
     * @return null.
     * @see org.va_labs.vae.gui.tag.ISwtElement#getParent()
     */
    public Object getParent() {
        return null;
    }

    /**
     * Returns the text associated to the workspace. Note: Not in use for now, a
     * workspace is never displayed.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#getText()
     */
    public String getText() {
        return null;
    }

    /**
     * Removes an attribute of the workspace. Note: Not in use for now, a
     * workspace has no attribute.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#removeAttribute(java.lang.String)
     */
    public void removeAttribute(String attributeName)
            throws NoSuchAttributeException {
        
    }

    /**
     * Sets an attribute of the workspace. Note: Not in use for now, a workspace
     * has no attribute.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#setAttributes(java.lang.Object[])
     */
    public void setAttributes(Object[] attributes)
            throws IllegalAttributeException {

    }

    /**
     * The workspace should never be displayed to the user, so this method
     * should never be called.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#setText(java.lang.String)
     */
    public void setText(String newName) {

    }

    /**
     * Updates one of the attributes of the workspace. Note: Not in use for now,
     * a workspace has no attribute.
     * 
     * @see org.va_labs.vae.gui.tag.ISwtElement#updateAttribute(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void updateAttribute(String originalName, String newName,
            String value) throws NoSuchAttributeException,
            IllegalAttributeException {

    }

    /**
     * Add a projects to the workspace
     * 
     * @param project
     *            newly opened that should be added to the workspace.
     */
    public void addProject(SwtProject project) {
        projects.put(project.getText(), project);
    }
}