/*
 * Created on Aug 17, 2004
 *
 * $Id: Project.java,v 1.3 2005/03/06 23:28:04 mojo_jojo Exp $
 */
package org.va_labs.vae.tag.project;

import java.util.List;

import org.va_labs.vae.tag.NoSuchTagException;
import org.va_labs.vae.tag.Tag;
import org.va_labs.vae.tag.property.Property;
import org.va_labs.vae.tag.target.Target;
import org.va_labs.vae.tag.taskdef.TaskDef;

/**
 * @author mojo_jojo
 * 
 * Abstract class that defines the different methods that a project should
 * support.
 */
public abstract class Project extends Tag {

    /**
     * Path to the build file associated to the Project.
     */
    protected String filePath;

    /**
     * Default constructor of project object. Simply calls the constructor of
     * Tag.
     * 
     * @see org.vae.core.Tag
     */
    public Project() {
        super();
    }

    /**
     * Adds a property to the project.
     * 
     * @param property
     *            property to be added.
     */
    public abstract void addProperty(Property property);

    /**
     * Adds a target to the project.
     * 
     * @param target
     *            target to be added.
     */
    public abstract void addTarget(Target target);

    /**
     * Adds a taskDef to the project
     * 
     * @param the
     *            TaskDef to be added to the project.
     */
    public abstract void addTaskDef(TaskDef taskDef);

    /**
     * Adds an unidentified tag to this project.
     * 
     * This tags are basically at the level of targets, but are neigher
     * properties nor targets. At one point this elements need to be integrated
     * specifically, but we want at least to display them in the tree with a
     * specific image.
     * 
     * @param unidentifiedTag
     *            the unidentified Tag to be added.
     */
    public abstract void addUnidentifiedTag(Tag unidentifiedTag);

    /**
     * Gets the base directory of the ant project.
     * 
     * @return the base directory of the project.
     */
    public abstract String getBaseDir();

    /**
     * Get the name of the default target.
     * 
     * @return the name of the default target.
     */
    public abstract String getDefaultTarget();

    /**
     * Gets the description associated to the current project
     * 
     * @return the description of the project.
     */
    public abstract String getDescription();

    /**
     * Gets the path to the build file for this project.
     * 
     * @return the path to the ant file.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Get the taskdefs (user defined ant tasks) of the current project.
     * 
     * @return a java.util.HashMap containing the taskdefs of the project. Note :
     *         can return null.
     */
    public abstract List getTaskDefs();

    /**
     * This tag is a project tag.
     * 
     * It is used so the project tree knows which image to associate to us.
     * 
     * @see org.vae.core.Tag#getType()
     */
    public int getType() {
        return Tag.PROJECT_TAG;
    }

    /**
     * Get the properties of the current project.
     * 
     * @return a java.util.HashMap containing the properties of the project.
     */
    public abstract List getProperties();

    /**
     * Get the targets of the current project.
     * 
     * @return a java.util.Vector containing the targets of the project.
     */
    public abstract List getTargets();

    /**
     * Get "unidentified tags" of the current project.
     * 
     * @param the
     *            list of "unidentified tags" for this project
     * @see #setUnidentifiedTags(List) for more details about what "unidentified
     *      tags" mean.
     */
    public abstract List getUnidentifiedTags();

    /**
     * Removes a property of the property list of this project.
     * 
     * @param propertyName
     * @throws NoSuchPropertyException
     *             if the property does not aooear in the list.
     */
    public abstract void removeProperty(String propertyName)
            throws NoSuchPropertyException;

    /**
     * Removes a target from a project.
     * 
     * @param targetName
     *            the name of the target.
     * @throws NoSuchTargetException
     *             if the target does not appear in the list.
     */
    public abstract void removeTarget(String targetName)
            throws NoSuchTargetException;

    /**
     * Removes a taskDef from a project.
     * 
     * @param taskDefName
     *            the name of the taskDef.
     * @throws NoSuchTaskDefException
     *             if the taskDef does not appear in the list.
     */
    public abstract void removeTaskDef(String taskDefName)
            throws NoSuchTaskDefException;

    /**
     * Removes an UnidentifiedTag from a project.
     * 
     * @param tagName
     *            name of the UnidentifiedTag.
     * @throws NoSuchTagException
     *             if the tag does not appear in the list.
     */
    public abstract void removeUnidentifiedTag(String tagName)
            throws NoSuchTagException;

    /**
     * Sets the base directory of the ant project.
     * 
     * @param the
     *            base directory of the project.
     */
    public abstract void setBaseDir(String baseDir);

    /**
     * Sets the name of the default target.
     * 
     * @param the
     *            name of the default target.
     */
    public abstract void setDefaultTarget(String targetName);

    /**
     * Sets the description of the ant project.
     * 
     * @param the
     *            description of project.
     */
    public abstract void setDescription(String description);

    /**
     * Sets the path to the build file for this project.
     * 
     * @param path
     *            the new path for the build file of this project.
     */
    public void setFilePath(String path) {
        filePath = path;
    }

    /**
     * Sets the properties of the current project.
     * 
     * @param propertyList
     *            the list of properties of this project.
     */
    public abstract void setProperties(List propertyList);

    /**
     * Sets the targets of the project.
     * 
     * @param targets
     *            the list of target of the ant project.
     */
    public abstract void setTargets(List targets);

    /**
     * Sets the taskdefs (user defined ant tasks) of the project.
     * 
     * @param a
     *            list of the taskdefs of the ant project.
     */
    public abstract void setTaskDefs(List taskDefs);

    /**
     * Sets the unidentified Tags of this project.
     * 
     * This tags are basically at the level of targets, but are neigher
     * properties nor targets. At one point this elements need to be integrated
     * specifically, but we want at least to display them in the tree with a
     * specific image.
     * 
     * @param a
     *            list of unidentied tags
     */
    public abstract void setUnidentifiedTags(List tags);
}