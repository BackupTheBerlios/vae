/*
 * Created on Sep 5, 2004
 *
 * $Id: SimpleProject.java,v 1.2 2005/02/26 00:57:23 mojo_jojo Exp $
 */
package org.va_labs.vae.tag.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.va_labs.vae.parser.IllegalAttributeException;
import org.va_labs.vae.tag.NoSuchTagException;
import org.va_labs.vae.tag.Tag;
import org.va_labs.vae.tag.generic.GenericElement;
import org.va_labs.vae.tag.property.Property;
import org.va_labs.vae.tag.target.Target;
import org.va_labs.vae.tag.taskdef.TaskDef;

/**
 * @author mojo_jojo
 * 
 * Simple and rought implementation of the Project interface. Ought to be
 * improved in the future.
 * 
 * This one is used by the Sax parser. When more than one implementation of
 * org.vae.core.Project will be available, the parser will have to use a
 * ProjectFactory.
 */
public class SimpleProject extends Project {

    /**
     * Legal attributes for a Project
     */
    private static String[] legalAttributes = { "name", "default", "basedir" };

    /**
     * Base directory of the ant project. According to the ant specifications,
     * the base directory is optional.
     */
    private StringBuffer baseDir;

    /**
     * Name of the default task of the ant project. According to ant
     * specifications, a project has to have at least one default target.
     */
    private String defaultTarget;

    /**
     * Description of the ant project. According to the ant specifications, a
     * description is optional.
     */
    private StringBuffer description;

    /**
     * Properties defined in the ant project.
     */
    private List properties;

    /**
     * Targets defined in the ant project.
     */
    private List targets;

    /**
     * TaskDefs defined in the ant project.
     */
    private List taskDefs;

    /**
     * Tags that couldn't be indentified by vae in the ant project.
     */
    private List unidentifiedTags;

    /**
     * Just initializes data handlers.
     */
    public SimpleProject() {
        super();
        name = new StringBuffer("Anonymous Project");
        properties = new ArrayList(5);
        targets = new ArrayList();
        taskDefs = new ArrayList(5);
        unidentifiedTags = new ArrayList(5);
    }

    /**
     * Creates a SimpleProject from a parsed Tag. Will be used some time soon to
     * keep the parser very generic.
     * 
     * @param projectTag
     */
    public SimpleProject(Tag projectTag) throws IllegalAttributeException {
        this();
        setName(projectTag.getName());
        setAttributes(projectTag.getAttributes());
        setNestedElements(projectTag.getNestedElements());
    }

    /**
     * Adds a property to this project.
     * 
     * @param property
     *            property to be added.
     * @see org.vae.core.Project#addProperty(org.vae.core.Property)
     */
    public void addProperty(Property property) {
        properties.add(property);
    }

    /**
     * Adds a target to this project.
     * 
     * @param target
     *            to be added.
     * @see org.vae.core.Project#addTarget(org.vae.core.Target)
     */
    public void addTarget(Target target) {
        targets.add(target);
    }

    /**
     * Adds a taskDef (user defined task) to this project. If the taskdef
     * doesn't have a name, it receives a generic name TaskDefX, where X is a
     * number incremented each time a new taskDef is defined.
     * 
     * @param the
     *            taskDef to be added to the project.
     */
    public void addTaskDef(TaskDef taskDef) {
        taskDefs.add(taskDef);
    }

    /**
     * Adds an unidentified tag to this project. This tags are basically at the
     * level of targets, but are neigher properties nor targets. At one point
     * this elements need to be integrated specifically, but we want at least to
     * display them in the tree with a specific image.
     * 
     * @param unidentifiedTag
     *            the unidentified Tag to be added.
     */
    public void addUnidentifiedTag(Tag tag) {
        unidentifiedTags.add(new GenericElement(tag));
    }

    /**
     * Gets the base directory of the ant project.
     * 
     * @return the base directory of the project.
     */
    public String getBaseDir() {
        if (baseDir != null) {
            return baseDir.toString();
        } else {
            return null;
        }
    }

    /**
     * Indicates the name of the default task.
     * 
     * @return the name of the default task.
     */
    public String getDefaultTarget() {
        return defaultTarget;
    }

    /**
     * Gets the description associated to the current project
     * 
     * @return the description of the project.
     */
    public String getDescription() {
        if (description != null) {
            return description.toString();
        } else {
            return null;
        }
    }

    /**
     * Returns the nested elements for this project.
     * 
     * @return the nested elements as a list of Tag objects.
     * @see org.vae.core.Tag#getNestedElements()
     */
    public List getNestedElements() {
        return nestedElements;
    }

    /**
     * @return the properties of the ant project in a List.
     */
    public List getProperties() {
        return properties;
    }

    /**
     * Returns the xml name of this project.
     * 
     * @see org.vae.core.Tag#getTagName()
     */
    public String getTagName() {
        return tagName.toString();
    }

    /**
     * @return the targets of the ant project.
     */
    public List getTargets() {
        return targets;
    }

    /**
     * Get the taskdefs (user defined ant tasks) of the current project.
     * 
     * @return a java.util.HashMap containing the taskdefs of the project. Note :
     *         can return null.
     */
    public List getTaskDefs() {
        return taskDefs;
    }

    /**
     * Returns the unidentified tags for this project.
     * 
     * @return the tags as a list of Tag objects.
     * @see org.vae.core.Project#getUnidentifiedTages()
     */
    public List getUnidentifiedTags() {
        return unidentifiedTags;
    }

    /**
     * Keeps the internal data in sync to reflect the removal of an attribute.
     * 
     * @param attributeName
     *            name of the attribute that is being removed.
     * @see org.vae.core.Tag#removeAttribute(int)
     */
    protected void removeKeyAttributes(String attributeName) {
        if (attributeName.equalsIgnoreCase("name")) {
            name = null;
        } else if (attributeName.equalsIgnoreCase("default")) {
            defaultTarget = null;
        } else if (attributeName.equalsIgnoreCase("basedir")) {
            baseDir = null;
        }
    }

    /**
     * Does the appropriate job if a key tag has been removed. Calls the
     * adequate removal methods to keep the internal data in sync.
     * 
     * @param tag
     *            tag that is being removed.
     */
    protected void removeKeyTags(Tag tag) {
        try {
            int tagType = tag.getType();
            String tagName = tag.getName();
            if (tagType == Tag.PROPERTY_TAG) {
                removeProperty(tagName);
            } else if (tagType == Tag.TARGET_TAG) {
                removeTarget(tagName);
            } else if (tagType == Tag.TASKDEF_TAG) {
                removeTaskDef(tagName);
            } else {
                addUnidentifiedTag(tag);
            }
        } catch (Exception e) {
            // Should never arrive here : internal method.
            System.out.println("Something went wrong while removing a tag");
        }
    }

    /**
     * Removes a property given its name.
     * 
     * @param propertyName
     *            the name of the property.
     * @throws NoSuchPropertyException
     *             if the property does not exist. TODO Implement RemoveProperty
     *             in SimpleProject.
     * @see org.vae.core.Project#removeProperty(java.lang.String)
     */
    public void removeProperty(String propertyName)
            throws NoSuchPropertyException {
        System.out.println("! Not Implement Yet !");
    }

    /**
     * Removes a Target given its name.
     * 
     * @param targetName
     *            the name of the target.
     * @throws NoSuchTargetException
     *             if the target does not exist. TODO implement RemoveTarget in
     *             SimpleProject.
     * @see org.vae.core.Project#removeTarget(java.lang.String)
     */
    public void removeTarget(String targetName) throws NoSuchTargetException {
        System.out.println("! Not Implement Yet !");
    }

    /**
     * Removes a TaskDef given its name.
     * 
     * @param taskDefName
     *            the name of the TaskDef
     * @throws NoSuchTaskDefException
     *             if the TaskDef does not exist. TODO implement TaskDef in
     *             SimpleProject.
     * @see org.vae.core.Project#RemoveTaskDef(java.lang.String)
     */
    public void removeTaskDef(String taskDefName) throws NoSuchTaskDefException {
        System.out.println("! Not Implement Yet !");
    }

    /**
     * Removes an UniditenfiedTag given its name.
     * 
     * @param tagName
     *            the name of the unidentified tag.
     * @throws NoSuchTagException.
     *             TODO implement RemoveUnidentifiedTag in SimpleProject.
     * @see org.vae.core.Project#RemoveUnidentifiedTag(java.lang.String)
     */
    public void removeUnidentifiedTag(String tagName) throws NoSuchTagException {
        System.out.println("! Not Implement Yet !");
    }

    /**
     * Sets the base directory of the ant project.
     * 
     * @param the
     *            base directory of the project.
     */
    public void setBaseDir(String baseDir) {
        this.baseDir = new StringBuffer(baseDir);
    }

    /**
     * Sets the name of the default task of the ant project.
     * 
     * @param the
     *            name of the default task.
     */
    public void setDefaultTarget(String targetName) {
        defaultTarget = targetName;
    }

    /**
     * Sets the description of the ant project.
     * 
     * @param the
     *            description of project.
     */
    public void setDescription(String description) {
        this.description = new StringBuffer(description);
    }

    /**
     * @param the
     *            properties of the ant project.
     */
    public void setProperties(List properties) {
        this.properties = properties;
        setClean(false);
    }

    /**
     * Sets the targets of the project.
     * 
     * @param the
     *            targets of the ant project.
     */
    public void setTargets(List targets) {
        this.targets = targets;
        setClean(false);
    }

    /**
     * Sets the taskDefs (user defined tasks) of this project.
     * 
     * @param the
     *            taskDefs in a HashMap.
     */
    public void setTaskDefs(List taskDefs) {
        this.taskDefs = taskDefs;
    }

    /**
     * Set the unidentified Tags of this project.
     * 
     * @param the
     *            unidentified tags in a List.
     * @see org.vae.core.Project#setUnidentifiedTags(java.util.List)
     */
    public void setUnidentifiedTags(List tags) {
        unidentifiedTags = new ArrayList();
        for (Iterator i = tags.iterator(); i.hasNext();) {
            unidentifiedTags.add(i.next());
        }
    }

    /**
     * Return various information in a String.
     */
    public String toString() {
        StringBuffer summary = new StringBuffer(100);

        summary.append("---\nProject name : " + name + "\n");
        summary.append("Base directory : " + baseDir + "\n");
        summary.append("Description : " + description + "\n");

        if (properties != null) {
            summary.append("---\nProperties : \n");
            for (Iterator i = properties.iterator(); i.hasNext();) {
                Property p = (Property) i.next();
                summary.append(p);
            }
        }

        if (targets != null) {
            summary.append("---\nTargets : \n");
            for (Iterator i = targets.iterator(); i.hasNext();) {
                Target t = (Target) i.next();
                summary.append(t);
            }
        }

        return summary.toString();
    }

    /**
     * Verifies if an attribute is valid and calls the adequate methods when
     * they are.
     * 
     * @param attributeName
     *            name of the attribute.
     * @param attributeValue
     *            value of the attribute.
     * @throws IllegalAttributeException
     *             if the attribute is not legal.
     */
    protected void verifyAttribute(String attributeName, String attributeValue)
            throws IllegalAttributeException {
        if (attributeName.equalsIgnoreCase("name")) {
            setName(attributeValue);
        } else if (attributeName.equalsIgnoreCase("default")) {
            setDefaultTarget(attributeValue);
        } else if (attributeName.equalsIgnoreCase("basedir")) {
            setBaseDir(attributeValue);
        } else {
            throw new IllegalAttributeException(attributeName, name.toString(),
                    legalAttributes);
        }
    }

    /**
     * Sync the local data with the build data.
     * 
     * In this implementation it lets everything go through and we call the
     * relevant functions according to the tag type. It is called from
     * addNestedElement and setNestedElements. Children that needs to keep data
     * in sync ought to redefine this.
     * 
     * @param tag
     *            a nestedTag of this object that has just been inserted in the
     *            data layout.
     */
    protected void verifyTag(Tag tag) {
        int tagType = tag.getType();
        if (tagType == Tag.PROPERTY_TAG) {
            addProperty((Property) tag);
        } else if (tagType == Tag.TARGET_TAG) {
            addTarget((Target) tag);
        } else if (tagType == Tag.TASKDEF_TAG) {
            addTaskDef((TaskDef) tag);
        } else {
            addUnidentifiedTag(tag);
        }
    }
}