/*
 * Created on Aug 15, 2004
 *
 * $Id: Tag.java,v 1.1 2005/04/05 02:45:25 mojo_jojo Exp $
 */
package org.vae_labs.vae.tag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.vae_labs.vae.core.TagAttribute;
import org.vae_labs.vae.core.Vae;
import org.vae_labs.vae.parser.IllegalAttributeException;
import org.vae_labs.vae.tag.project.Project;

/**
 * @author mojo_jojo
 * 
 * Summaries the methods relative to each tags in the ant build file.
 * 
 * Some of the methods have a base implementation that is supposed to be
 * redefined by children classes for specific tags. Tags can have an xml name
 * and an "usual" name. The Xml name is the name of the tag in the build file,
 * whereas the "usual" name is the value of the attribute name or path if they
 * exist.
 */
public abstract class Tag {

    /**
     * Indicates that the tag is a Project.
     */
    public static final int PROJECT_TAG = 0;

    /**
     * Indicates that the tag is Property.
     */
    public static final int PROPERTY_TAG = 1;

    /**
     * Indicates that the tag is a Target.
     */
    public static final int TARGET_TAG = 2;

    /**
     * Indicates that the tag is a Task.
     */
    public static final int TASK_TAG = 3;

    /**
     * Indicates that the tag is a Taskdef.
     */
    public static final int TASKDEF_TAG = 4;

    /**
     * Indicates that the tag is none of the previous tags.
     */
    public static final int UNIDENTIFIED_TAG = 100;

    /**
     *  
     */
    protected static int tabDepth = 0;

    /**
     * Attributes for this tag.
     */
    protected List attributes;

    /**
     * Characters between the opening and closing tags.
     */
    protected StringBuffer characters;

    /**
     * Indicates wether the tag needs to be saved or not. if false, the project
     * needs to be saved.
     */
    protected boolean clean;

    /**
     * Project to which this tag is connected
     */
    protected Project currentProject;

    /**
     * "usual" name of the tag. Value of the attribute name or path.
     */
    protected StringBuffer name;

    /**
     * Nested Tags for this tag.
     */
    protected List nestedElements;

    /**
     * Xml name of the tag.
     */
    protected StringBuffer tagName;

    /**
     * Sets up the different attributes of the Tag.
     * 
     * The current project of the tag is taken from the vae object.
     */
    public Tag() {
        clean = true;
        currentProject = Vae.getInstance().getCurrentProject();
        attributes = new ArrayList();
        nestedElements = new ArrayList();
        characters = new StringBuffer();
    }

    /**
     * Adds an attribute concerning this tag.
     * 
     * All children class should not have to redefine this function: special
     * treatments to keep internal tag data in sync with the build file info
     * should be coded by redefining setKeyAttributes. Clean status will be set
     * to false during this operation
     * 
     * @param name
     *            Name of the attribute to be added.
     * @param value
     *            Value of the attribute to be added.
     * @throws IllegalAttributeException
     *             if the attribute is not legal for this tag.
     */
    public void addAttribute(String name, String value)
            throws IllegalAttributeException {
        verifyAttribute(name, value);
        TagAttribute attribute = new TagAttribute(this, name, value);
        attributes.add(attribute);
        setClean(false);
    }

    /**
     * Adds an attribute at a certain position in the list of attributes.
     * 
     * Clean status will be set to false during this operation
     * 
     * @param name
     *            name of the attribute.
     * @param value
     *            value of the attribute
     * @param position
     *            position of the attribute in the list of attributes.
     * @throws IllegalAttributeException
     *             if the attribute is not legal for this tag.
     */
    public void addAttribute(String name, String value, int position)
            throws IllegalAttributeException {
        verifyAttribute(name, value);
        TagAttribute attribute = new TagAttribute(this, name, value);
        attributes.add(position, attribute);
        setClean(false);
    }

    /**
     * Add a nested tag for this tag.
     * 
     * Children class should not have to redefine this function: Clean status
     * will be set to false during this operation special treatments to keep
     * internal tag data in sync with the build file info should be coded by
     * redefining setKeyTag.
     * 
     * @param tag
     *            tag nested inside this one.
     */
    public void addNestedElement(Tag tag) {
        verifyTag(tag);
        nestedElements.add(tag);
        setClean(false);
    }

    /**
     * Append characters to the characters associated to this Tag.
     * 
     * @param chars
     *            Array containing the characters.
     * @param start
     *            start index of the interesting chars.
     * @param lenght
     *            number of characters that should be considers.
     */
    public void appendCharacters(char[] chars, int start, int length) {
        for (int i = start, end = start + length; i < end; i++) {
            if (chars[i] != '\t' && chars[i] != '\n' && chars[i] != '\r') {
                // Ugly hack to avoid getting rogue white spaces !
                if (chars[i] != ' ' || characters.length() != 0) {
                    characters.append(chars[i]);
                }
            }
        }
    }

    /**
     * Returns a list of the different attributes of this tag.
     * 
     * @return a list with the different attributes as TagAttribute objects.
     */
    public List getAttributes() {
        return attributes;
    }

    /**
     * Indicates the characters between the openin and closing tag represented
     * by this instance.
     * 
     * @return the string as a StringBuffer.
     */
    public StringBuffer getCharacters() {
        return characters;
    }

    /**
     * Indicates wether this tag has been changed or not.
     * 
     * @return true if the tag is unchanged.
     */
    public boolean getClean() {
        return clean;
    }

    /**
     * Self explanatory.
     * 
     * @return the name of the tag.
     */
    public String getName() {
        if (name != null) {
            return name.toString();
        } else {
            return null;
        }
    }

    /**
     * Returns a list of the nested tags of this tag.
     * 
     * @return a List of the nested tags as NestedElement objects.
     */
    public List getNestedElements() {
        return nestedElements;
    }

    /**
     * Returns the name of the tag for this object in the build file.
     * 
     * @return the xml name in the tag.
     */
    public String getTagName() {
        return tagName.toString();
    }

    /**
     * Returns the type of this tag.
     * 
     * @return the type of the tag.
     */
    public abstract int getType();

    /**
     * Locates the position of an attribute in the attribute list.
     * 
     * @param attributeName
     *            name of the attribute we are searching.
     * @param operation
     *            operation that requested this search. It is used if an
     *            exception is raised.
     * @return the position of the attribute.
     * @throws NoSuchAttributeException
     *             if the attribute is not in the list.
     */
    protected int locateAttribute(String attributeName, String operation)
            throws NoSuchAttributeException {
        Iterator i = attributes.iterator();
        while (i.hasNext()) {
            TagAttribute attribute = (TagAttribute) i.next();
            if (attribute.getName().equalsIgnoreCase(attributeName))
                return attributes.indexOf(attribute);
        }
        throw new NoSuchAttributeException(operation, attributeName, name
                .toString());
    }

    /**
     * Locates the position of an a tag in the nested elements list.
     * 
     * @param tag
     *            that we are searching.
     * @param operation
     *            operation that requested this search. It is used if an
     *            exception is raised.
     * @return the position of the tag.
     * @throws NoSuchTagException
     *             if the tag is not in the list.
     */
    protected int locateTag(Tag tag, String operation)
            throws NoSuchTagException {
        int position = nestedElements.indexOf(tag);
        if (position == -1) {
            throw new NoSuchTagException(tag.getName(), name.toString());
        }
        return position;
    }

    /**
     * Removes an attribute from the attribute list
     * 
     * Clean status will be set to false during this operation
     * 
     * @param position
     *            position of the attribute to be removed.
     * @throws NoSuchAttributeException
     *             if there is no attribute at the specified position.
     */
    public void removeAttribute(int position) throws NoSuchAttributeException {
        try {
            TagAttribute attribute = (TagAttribute) attributes.remove(position);
            removeKeyAttributes(attribute.getName());
            setClean(false);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchAttributeException("removal", "not specified", name
                    .toString());
        }
    }

    /**
     * Removes an attribute of this tag.
     * 
     * Clean status will be set to false during this operation
     * 
     * @param attributeName
     *            name of the attribute to be removed.
     * @throws NoSuchAttributeException
     *             in case this attribute doesn't exist.
     */
    public void removeAttribute(String attributeName)
            throws NoSuchAttributeException {
        int position = locateAttribute(attributeName, "removal");
        attributes.remove(position);
        removeKeyAttributes(attributeName);
        setClean(false);
    }

    /**
     * Does the appropriate job if a key attribute has been removed.
     * 
     * Name is the only key attribute common to all Tag subclasses. Subclasses
     * that have other data to sync should redefine this method.
     * 
     * @param attributeName
     *            name of the removed attribute.
     */
    protected void removeKeyAttributes(String attributeName) {
        if (attributeName.equalsIgnoreCase("name")) {
            name = null;
        }
    }

    /**
     * Does the appropriate job if a key tag has been removed.
     * 
     * It's up to the subclasses to redefine this method.
     * 
     * @param tag
     *            tag that is being removed.
     */
    protected void removeKeyTags(Tag tag) {

    }

    /**
     * Removes a nested tag from the tag list.
     * 
     * Clean status will be set to false during this operation.
     * 
     * @param tag
     *            Tag to be removed from the nested tags list
     * @throws NoSuchTagException
     *             if the tag is not in the nested elements list. TODO: We need
     *             to verify if the call to locateTag is really needed. Maybe a
     *             simple call to contains and remove could do the trick.
     */
    public void removeTag(Tag tag) throws NoSuchTagException {
        int position = locateTag(tag, "removal");
        nestedElements.remove(position);
        removeKeyTags(tag);
        setClean(false);
    }

    /**
     * Sets the attributes of this tag.
     * 
     * Clean status will be set to false during this operation
     * 
     * @param attributes
     *            a list of the different attributes as TagAttribute objects.
     * @throws IllegalAttributeException
     *             If one or more of the attributes are not legal for this tag.
     */
    public void setAttributes(List attributeList)
            throws IllegalAttributeException {
        Iterator i = attributeList.iterator();
        attributes = new ArrayList(attributeList.size());
        while (i.hasNext()) {
            TagAttribute attrib = (TagAttribute) i.next();
            addAttribute(attrib.getName(), attrib.getValue());
        }
        setClean(false);
    }

    /**
     * Sets the characters for this tag.
     * 
     * In the beguinning users won't be allowed to change this, so this method
     * will only be called by the parser. However as this may change in the
     * future, we mark the tag, and consequently the project as dirty.
     * 
     * @param chars
     *            characters located between the opening and closing tag.
     */
    public void setCharacters(StringBuffer chars) {
        characters = chars;
        setClean(false);
    }

    /**
     * Sets wether the project containing this tag need to be saved or not.
     * 
     * @param status,
     *            false means project needs to be saved.
     */
    public void setClean(boolean status) {
        clean = status;

        // Sets the status of the project it is linked to if there is one.
        if (currentProject != null) {
            currentProject.setClean(status);
        }
    }

    /**
     * Sets the current project for this tag.
     * 
     * The given project is set as the current project for all the children
     * elements of this tag.
     * 
     * @param project
     *            Project to which this tag is linked.
     */
    public void setCurrentProject(Project project) {
        currentProject = project;
        Iterator i = nestedElements.iterator();
        while (i.hasNext()) {
            Tag tag = (Tag) i.next();
            tag.setCurrentProject(project);
        }
    }

    /**
     * Sets the name of the tag.
     * 
     * Clean status will be set to false during this operation
     * 
     * @param name,
     *            name of the tag.
     */
    public void setName(String name) {
        if (name != null) {
            this.name = new StringBuffer(name);
        }
        setClean(false);
    }

    /**
     * Sets the nested Elements for this tag.
     * 
     * Clean status will be set to false during this operation
     * 
     * @param nestedTags
     *            the nested tags for this tag as a list of Tag objects.
     */
    public void setNestedElements(List nestedTags) {
        Iterator i = nestedTags.iterator();
        nestedElements = new ArrayList(nestedTags.size());
        while (i.hasNext()) {
            addNestedElement((Tag) i.next());
        }
        setClean(false);
    }

    /**
     * Set the xml name of this tag.
     * 
     * Clean status will be set to false during this operation.
     */
    public void setTagName(String xmlName) {
        tagName = new StringBuffer(xmlName);
        setClean(false);
    }

    /**
     * Return the xml info corresponding to the entity.
     * 
     * @return a StringBuffer that contains what concerns this entity.
     */
    public StringBuffer toXml() {
        StringBuffer content = new StringBuffer();

        for (int i = 0; i < tabDepth; i++) {
            content.append("\t");
        }
        content.append("<" + tagName);

        // Putting the attributes if any.
        for (Iterator i = attributes.iterator(); i.hasNext();) {
            TagAttribute attribute = (TagAttribute) i.next();
            content.append(" " + attribute.getName() + "=\"");
            content.append(attribute.getValue() + "\"");
        }

        int length = characters.length();
        // Early check for nice text formating.
        if (length == 0 && nestedElements.isEmpty()) {
            content.append("/>");
            tabDepth--;
        } else {
            content.append(">\n");
            // Putting characters if any.
            if (characters.length() != 0) {
                for (int i = -1; i < tabDepth; i++) {
                    content.append("\t");
                }
                content.append(characters + "\n");
            }

            // Putting nested tags if any.
            for (Iterator i = nestedElements.iterator(); i.hasNext();) {
                Tag tag = (Tag) i.next();
                tabDepth++;
                content.append(tag.toXml() +"\n");
            }

            for (int i = 0; i < tabDepth; i++) {
                content.append("\t");
            }
            tabDepth--;

            content.append("</" + tagName + ">");
        }
        return content;
    }

    /**
     * Update an attribute of the tag, but keeps it at its position.
     * 
     * Clean status will be set to false during this operation
     * 
     * @param originalName
     *            the original name of the attribute.
     * @param newName
     *            the new name of the attribute (may be the same as
     *            originalName).
     * @param value
     *            the new value of the attribute (may be the same as the
     *            original value).
     * @throws NoSuchAttributeException
     *             if the attribute to be updated does not exist.
     * @throws IllegalAttributeException
     *             if the attribute is not legal for this tag.
     */
    public void updateAttribute(String originalName, String newName,
            String value) throws NoSuchAttributeException,
            IllegalAttributeException {
        int position = locateAttribute(originalName, "update");
        TagAttribute attrib = (TagAttribute) attributes.get(position);
        verifyAttribute(newName, value);
        attrib.setName(newName);
        attrib.setValue(value);
        setClean(false);
    }

    /**
     * Update an attribute of the tag.
     * 
     * Clean status will be set to false during this operation
     * 
     * @param originalName
     *            the original name of the attribute.
     * @param newName
     *            the new name of the attribute (may be the same as
     *            originalName).
     * @param value
     *            the new value of the attribute (may be the same as the
     *            original value).
     * @param position
     *            position of the attribute among the other attributes.
     * @throws NoSuchAttributeException
     *             if the attribute to be updated does not exist.
     * @throws IllegalAttributeException
     *             if the attribute is not legal for this tag.
     */
    public void updateAttribute(String originalName, String newName,
            String value, int position) throws NoSuchAttributeException,
            IllegalAttributeException {
        removeAttribute(originalName);
        addAttribute(newName, value, position);
        setClean(false);
    }

    /**
     * Verifies that the attributes are legal for the tag and calls the
     * appropriate methods.
     * 
     * The implementation at this level is very permissive (everything goes and
     * only the name gets updated). Children ought to redefine this to get a
     * finer control on what attributes are legal or not.
     * 
     * @param name
     *            name of the attribute that is set/added.
     * @param value
     *            value of the attribute that is set/added.
     * @throws IllegalAttributeException
     *             if the attribute is not legal. Legal attributes are name
     *             (mandatory), depends, if, unless, description.
     */
    protected void verifyAttribute(String name, String value)
            throws IllegalAttributeException {
        if (name.equalsIgnoreCase("name")) {
            setName(value);
        }
    }

    /**
     * Verify that the nested tag is acceptable for this tag, and sync the local
     * data with the build data.
     * 
     * In this implementation it lets everything go through and doesn't update
     * any data.
     * 
     * It is called from addNestedElement and setNestedElements. Children that
     * needs to keep data in sync ought to redefine this.
     * 
     * @param tag
     *            a nestedTag of this object that has just been inserted in the
     *            data layout.
     */
    protected void verifyTag(Tag tag) {

    }
}