/*
 * Created on Sep 4, 2004
 *
 * $Id: SaxAntParser.java,v 1.3 2005/02/20 12:36:31 mojo_jojo Exp $
 */
package org.va_labs.vae.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.va_labs.vae.VaeInitException;
import org.va_labs.vae.core.Vae;
import org.va_labs.vae.tag.Tag;
import org.va_labs.vae.tag.generic.GenericElement;
import org.va_labs.vae.tag.project.Project;
import org.va_labs.vae.tag.project.SimpleProject;
import org.va_labs.vae.tag.property.SimpleProperty;
import org.va_labs.vae.tag.target.SimpleTarget;
import org.va_labs.vae.tag.task.SimpleTask;
import org.va_labs.vae.tag.taskdef.SimpleTaskDef;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author mojo_jojo
 * 
 * Sax implementation that gets the necessary information from the build file
 * and creates the corresponding Vae object.
 */
public class SaxAntParser extends DefaultHandler implements IAntParser {

    /**
     * We save the currently parsed tag to manage the different nested tags
     * inside and its properties.
     */
    private Tag currentTag;

    /**
     * org.vae.cor.Project that will hold all the information contained in the
     * build file.
     */
    private Project project;

    /**
     * SAXParser used to get the information from the build file.
     */
    private SAXParser saxParser;

    /**
     * We pile up the tags on this Stack to take care of the nested tags inside
     * tasks.
     */
    private List tags;

    /**
     * Saves the reference to the Vae that creates the parser. Initialization is
     * done in the open() function, as we use the same parser to parse multiple
     * files.
     * 
     * @throws VaeInitException
     *             in case anything goes wrong in the init of the sax parser.
     */
    public SaxAntParser() throws VaeInitException {
        tags = new ArrayList();
        // Use the default (non-validating) parser.
        try {
            saxParser = SAXParserFactory.newInstance().newSAXParser();
        } catch (Exception e) {
            throw new VaeInitException("Ant Parser",
                    "Cannot initialize the xml" + "parser",
                    "Couldn't get a sax parser instance",
                    Vae.VAE__MODULE__ERROR);
        }
    }

    /**
     * Check that the build file is valid. Defined in org.vae.xml.Xml
     * 
     * @see org.vae.xml.Xml
     * 
     * TODO implement this $@%@#$ method.
     */
    public void check() {

    }

    /**
     * Called for each element closing tag. For now we use it to detect the end
     * of the interresting part of the document and make the save of the
     * targets.
     */
    public void endElement(String namespaceURI, String simpleName,
            String qualifiedName) {
        // The file has been entirely parsed : we can register
        // the information.
        if (qualifiedName.toLowerCase().equals("project")) {
            // All the operations on the project made it dirty,
            // but it's actually still without any user change.
            project.setClean(true);
        } else {
            currentTag = (Tag) tags.remove(tags.size() - 1);
        }
    }

    /**
     * Return a project object corresponding to the build file.
     * 
     * @return a org.vae.core.Project holding all the information specified in
     *         the build file.
     */
    public Project getProject() {
        return project;
    }

    private void parseAttributes(Attributes attributes, Tag tag, String tagType) {
        for (int i = 0; i < attributes.getLength(); i++) {
            String qName = attributes.getQName(i);
            String value = attributes.getValue(qName);
            try {
                tag.addAttribute(qName, value);
            } catch (IllegalAttributeException e) {
                System.out.println("Illegal " + tagType + " attribute " + qName
                        + " detected");
            }
        }
    }

    /**
     * Create a Project object containing the information specified in the build
     * file. Defined in org.vae.xml.Xml
     * 
     * @see org.vae.xml.Xml
     * 
     * TODO Handle the exceptions in a better way.
     */
    public void parseProject(File buildFile) throws VaeParsingException {
        tags.clear(); // Clears the tag in case it was already used.

        try {
            saxParser.parse(buildFile, this);
        } catch (SAXException e) {
            throw new VaeParsingException("SaxXml", "Error while parsing", e
                    .getMessage(), 0);
        } catch (IOException e) {
            throw new VaeParsingException("SaxXml", "I/O error while parsing",
                    e.getMessage(), 0);
        }
    }

    /**
     * Callback that SAX uses when it parses the xml file. We build the correct
     * project object in this function
     * 
     * @param namespaceURI
     *            the current namespace (not used here).
     * @param simpleName
     *            the simple name of the currently parsed element (not used
     *            here).
     * @param qualifiedName
     *            the qualified name of the currently parsed element.
     * @param attributes
     *            the attributes corresponding to the currently parsed element.
     * 
     * TODO make a better handle of the exceptions here.
     */
    public void startElement(String namespaceURI, String simpleName, // simple
            // name
            // (localName)
            String qualifiedName, // qualified name
            Attributes attributes) throws SAXException {
        if (qualifiedName.equalsIgnoreCase("project")) {
            project = new SimpleProject();
            project.setTagName(qualifiedName);
            parseAttributes(attributes, project, qualifiedName);
            currentTag = project;
        } else if (qualifiedName.equalsIgnoreCase("target")) {
            //			System.out.print("Parsing a target ... ");
            SimpleTarget target = new SimpleTarget();
            target.setCurrentProject(project);
            target.setTagName(qualifiedName);
            parseAttributes(attributes, target, qualifiedName);
            currentTag.addNestedElement(target);
            tags.add(currentTag);
            currentTag = target;
            //			System.out.println("[OK]");
        } else if (qualifiedName.equalsIgnoreCase("property")) {
            //			System.out.print("Parsing a property ...");
            SimpleProperty property = new SimpleProperty();
            property.setCurrentProject(project);
            property.setTagName(qualifiedName);
            parseAttributes(attributes, property, qualifiedName);
            currentTag.addNestedElement(property);
            tags.add(currentTag);
            currentTag = property;
            //			System.out.println("[OK]");
        } else if (qualifiedName.equalsIgnoreCase("taskdef")) {
            //			System.out.print("Parsing a taskdef ...");
            SimpleTaskDef taskdef = new SimpleTaskDef();
            taskdef.setTagName(qualifiedName);
            taskdef.setCurrentProject(project);
            parseAttributes(attributes, taskdef, qualifiedName);
            currentTag.addNestedElement(taskdef);
            tags.add(currentTag);
            currentTag = taskdef;
            //			System.out.println("[OK]");
        } else {
            if (currentTag.getType() == Tag.TARGET_TAG) {
                //				System.out.println("Parsing a task ... ");
                // Dealing with a task here
                SimpleTask task = new SimpleTask(qualifiedName);
                task.setTagName(qualifiedName);
                task.setCurrentProject(project);
                parseAttributes(attributes, task, qualifiedName);
                currentTag.addNestedElement(task);
                tags.add(currentTag);
                currentTag = task;
                //				System.out.println("[OK]");
            } else {
                // Either parsing an unidentified target , or a nested tag.
                //				System.out.print("Parsing a generic tag ... ");
                GenericElement element = new GenericElement(qualifiedName);
                element.setTagName(qualifiedName);
                element.setCurrentProject(project);
                parseAttributes(attributes, element, qualifiedName);
                currentTag.addNestedElement(element);
                tags.add(currentTag);
                currentTag = element;
                //				System.out.println("[OK]");
            }
        }
    }
}