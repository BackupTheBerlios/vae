/*
 * Created on Sep 5, 2004
 *
 * $Id: SwtProject.java,v 1.1 2005/04/05 02:45:27 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.tag.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.vae_labs.vae.core.TagAttribute;
import org.vae_labs.vae.gui.tag.ISwtElement;
import org.vae_labs.vae.gui.tag.property.SwtProperty;
import org.vae_labs.vae.gui.tag.target.SwtTarget;
import org.vae_labs.vae.gui.tag.unidentified.SwtUnidentifiedTag;
import org.vae_labs.vae.parser.IllegalAttributeException;
import org.vae_labs.vae.tag.NoSuchAttributeException;
import org.vae_labs.vae.tag.Tag;
import org.vae_labs.vae.tag.project.Project;
import org.vae_labs.vae.tag.property.Property;
import org.vae_labs.vae.tag.target.Target;

/**
 * @author mojo_jojo
 * 
 * This is used to add Swt specific information to Projects to create the
 * project tree.
 */
public class SwtProject implements ISwtElement {

    /**
     * Reference to the core project this class represents.
     */
    private Project project;

    /**
     * Reference to the workspace handling this project.
     */
    private ISwtElement parent;

    /**
     * Creates a SwtProject from a given project for a given workspace.
     * 
     * @param p
     *            project that will be reprensenting.
     * @param workspace
     *            vae workspace handling the project.
     */
    public SwtProject(Project p, ISwtElement workspace) {
        project = p;
        parent = workspace;
    }

    /**
     * Add an attribute to this project.
     * 
     * @param attributeName
     *            name of the attribute to be added.
     * @param attributeValue
     *            value of the attribute to be added.
     * @throws IllegalAttributeException
     *             if the attribute is not legal for a Project.
     * @see org.vae.gui.swt.components.VaeElement#addAttribute(java.lang.String,
     *      java.lang.String)
     */
    public void addAttribute(String attributeName, String attributeValue)
            throws IllegalAttributeException {
        project.addAttribute(attributeName, attributeValue);
    }

    /**
     * Returns the attributes for this project.
     * 
     * @return the attributes in an array of TagAttributes.
     * @see org.vae.gui.swt.components.VaeElement#getAttributes()
     * @see org.vae.core.TagAttribute
     */
    public Object[] getAttributes() {
        return project.getAttributes().toArray();
    }

    /**
     * Returns the children for this project in an Array of object.
     * 
     * @return an array containing the children of this project.
     */
    public Object[] getChildren() {
        List nestedElements = project.getNestedElements();

        ArrayList children = new ArrayList(nestedElements.size());

        Iterator i = nestedElements.iterator();

        while (i.hasNext()) {
            Tag nestedTag = (Tag) i.next();
            int tagType = nestedTag.getType();
            if (tagType == Tag.PROPERTY_TAG) {
                SwtProperty property = new SwtProperty((Property) nestedTag);
                children.add(property);
            } else if (tagType == Tag.TARGET_TAG) {
                SwtTarget target = new SwtTarget((Target) nestedTag);
                children.add(target);
            } else {
                SwtUnidentifiedTag tag = new SwtUnidentifiedTag((Tag) nestedTag);
                children.add(tag);
            }
        }
        return children.toArray();
    }
    
    /**
     * Indicates the status of the project.
     * 
     * @return false if the project needs to be save.
     */
    public boolean getClean() {
        return project.getClean();
    }
    
    /**
     * Returns the build file of the project.
     * 
     * @return the path the ant build file associated to the project.
     */
    public String getFilePath() {
        return project.getFilePath();
    }

    /**
     * Return the parent in the tree of this element.
     * 
     * @return null in this case as we are in the top element as a project.
     */
    public Object getParent() {
        return parent;
    }

    /**
     * The label that needs to be associated to the tree entry.
     * 
     * @see org.vae.gui.swt.components.VaeElement#getText()
     */
    public String getText() {
        return project.getName();
    }

    /**
     * Indicates if the project has children in the tree.
     * 
     * @return true if the project has children.
     */
    public boolean hasChildren() {
        return project.getNestedElements().size() != 0;
    }

    /**
     * Remove an attribute of this project.
     * 
     * @param attributeName
     *            name the attribute to be removed.
     * @throws NoSuchAttributeException
     *             if the attribute doesn't exists.
     * @see org.vae.gui.swt.components.VaeElement#removeAttribute(java.lang.String)
     */
    public void removeAttribute(String attributeName)
            throws NoSuchAttributeException {
        project.removeAttribute(attributeName);
    }

    /**
     * Sets the attributes for this project. The accepted attributes are :
     * basedir, default and description.
     * 
     * @see org.vae.gui.swt.components.VaeElement#setAttributes(java.lang.Object[])
     */
    public void setAttributes(Object[] attributes)
            throws IllegalAttributeException {
        for (int i = 0; i < attributes.length; i++) {
            TagAttribute tagAttribute = (TagAttribute) attributes[i];
            project.addAttribute(tagAttribute.getName(), tagAttribute
                    .getValue());
        }
    }

    /**
     * Sets the new name of the project.
     * 
     * @see org.vae.gui.swt.components.VaeElement#setText(java.lang.String)
     */
    public void setText(String newName) {
        project.setName(newName);
    }

    /**
     * Updates an attribute for this project.
     * 
     * @param originaName
     *            the original name of the attribute.
     * @param newName
     *            the new name of the attribute.
     * @param value
     *            the value of the attribute.
     * @throws IllegalAttributeException
     *             if the new attribute is not legal for a Project.
     * @throws NoSuchAttributeException
     *             if the original attribute does not exist.
     * @see org.vae.gui.swt.components.VaeElement#updateAttribute(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void updateAttribute(String originalName, String newName,
            String value) throws NoSuchAttributeException,
            IllegalAttributeException {
        project.updateAttribute(originalName, newName, value);
    }

}