/*
 * Created on Sep 4, 2004
 *
 * $Id: SaxAntParser.java,v 1.1 2004/09/05 00:30:54 mojo_jojo Exp $
 */
package org.va_labs.vae.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

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
 * Sax implementation that gets the necessary information 
 * from the build file and creates the corresponding Vae 
 * object.
 */
public class SaxAntParser extends DefaultHandler implements IAntParser {

    /**
	 * File object pointing to the build file currently used.
	 */
	private File buildFile;

	/**
	 * We save the currently parsed tag to manage the different
	 * nested tags inside and its properties.
	 */
	private Tag currentTag;
	
	/**
	 * org.vae.cor.Project that will hold all the information 
	 * contained in the build file.
	 */
	private Project project;

	/**
	 * SAXParser used to get the information from the build file. 
	 */
	private SAXParser saxParser;

	/**
	 * We pile up the tags on this Stack to take care of the nested
	 * tags inside tasks.
	 */
	private List tags;
	
	/**
	 * Boolean indicating weather we need to save changes to the
	 * build file or not.
	 */
	private boolean toSave;
	
	/**
	 * The vae instance that is linked to this parser.
	 */
	private Vae vae;

	/**
	 * Saves the reference to the Vae that creates the parser.
	 * Initialization is done in the open() function, as we use
	 * the same parser to parse multiple files.
 	 */
	public SaxAntParser(Vae vae) 
	{
		this.vae = vae;
		tags = new ArrayList();
		// Use the default (non-validating) parser.
		try {
			saxParser = SAXParserFactory.newInstance().newSAXParser();
		} catch(SAXException e) {
			//TODO needs to do something sensible here.
			vae.signifyError("TODO needs to do something sensible here.");
		} catch(ParserConfigurationException e) {
			//TODO needs to do something sensible here.
			vae.signifyError("TODO needs to do something sensible here.");
		} catch(FactoryConfigurationError e) {
			// TODO needs to do something sensible here.
			vae.signifyError("TODO needs to do something sensible here.");
		}
	}
	
	/**
	 * Check that the build file is valid. Defined in org.vae.xml.Xml
	 * @see org.vae.xml.Xml
	 * TODO implement this $@%@#$ method.
	 */
	public void check()
	{

	}

	/**
	 * Close the opened build file. Defined in org.vae.xml.Xml
	 */
	public void close()
	{
		if( toSave ) {
			save();			
		}
	}
	
	/**
	 * Called for each element closing tag.
	 * For now we use it to detect the end of the interresting 
	 * part of the document and make the save of the targets.
	 */
	public void endElement(String namespaceURI,
	String simpleName, // simple name (localName)
	String qualifiedName) // qualified name
	{
		// The file has been entirely parsed : we can register 
		// the information.
		if(qualifiedName.toLowerCase().equals("project")) {
			// All the operations on the project made it dirty, 
			// but it's actually still without any user change.
			project.setClean(true);
		} else {
			currentTag = (Tag) tags.remove(tags.size() -1);
		}
	}
	
	/**
	 * Return a project object corresponding to the build file.
	 * @return a org.vae.core.Project holding all the information
	 * specified in the build file. 
	 */
	public Project getProject()
	{
		return project;
	}
	
	private void parseAttributes(Attributes attributes, Tag tag, 
			String tagType)
	{
		for(int i=0; i<attributes.getLength(); i++) {
			String qName = attributes.getQName(i);
			String value = attributes.getValue(qName);
			try {
				tag.addAttribute(qName, value);
			}catch (IllegalAttributeException e) {
				System.out.println(
						"Illegal "+tagType+" attribute "+qName+" detected");
			}
		}
	}
	
	/**
	 * Initialize the data : it's after this call that it is actually
	 * going to be used.
	 * Opens a specified build file. Defined in org.vae.xml.Xml
	 * @param filename the name of the build file to be opened. 
	 * @throws FileNotFoundException if the file couldn't be opened.
	 */
	public void open( String filename) throws FileNotFoundException
	{
		buildFile = new File(filename);
		toSave = false;
		
		if(! buildFile.exists()) {
			throw new FileNotFoundException(filename);
		}
	}
	
	/**
	 * Create a Project object containing the information specified in the
	 * build file. Defined in org.vae.xml.Xml
	 * @see org.vae.xml.Xml
	 * TODO Handle the exceptions in a better way.
	 */
	public void parseProject() throws VaeParsingException
	{
		tags.clear(); // Clears the tag in case it was already used.
		
		try {
			saxParser.parse( buildFile, this);
		}  catch(SAXException e){
			throw new VaeParsingException("SaxXml", 
					"Error while parsing", e.getMessage(), 0);
		} catch(IOException e) {
			throw new VaeParsingException("SaxXml", 
				"I/O error while parsing", e.getMessage(), 0);
		}
	}
	
	/**
	 * Save the changes done to the build file. Defined in org.vae.xml.Xml
	 * @see org.vae.xml.Xml
	 */
	public void save()
	{
		//TODO Develop the save method.
	}
	
	/**
	 * Callback that SAX uses when it parses the xml file.
	 * We build the correct project object in this function
	 * @param namespaceURI the current namespace (not used here).
	 * @param simpleName the simple name of the currently parsed element 
	 * (not used here).
	 * @param qualifiedName the qualified name of the currently parsed element.
	 * @param attributes the attributes corresponding to the currently parsed
	 * element.
	 * TODO make a better handle of the exceptions here.
	 */
	public void startElement(String namespaceURI,
				String simpleName, // simple name (localName)
				String qualifiedName, // qualified name
				Attributes attributes) throws SAXException
	{
		if( qualifiedName.equalsIgnoreCase("project") ) {
			project = new SimpleProject();
			project.setTagName(qualifiedName);
			parseAttributes(attributes, project, qualifiedName);
			currentTag = project;
		} else if( qualifiedName.equalsIgnoreCase("target")) {
//			System.out.print("Parsing a target ... ");
			SimpleTarget target = new SimpleTarget();
			target.setTagName(qualifiedName);
			parseAttributes(attributes, target, qualifiedName);
			currentTag.addNestedElement(target);
			tags.add(currentTag);
			currentTag = target;
//			System.out.println("[OK]");
		} else if( qualifiedName.equalsIgnoreCase("property")) {
//			System.out.print("Parsing a property ...");
			SimpleProperty property = new SimpleProperty();
			property.setTagName(qualifiedName);
			parseAttributes(attributes, property, qualifiedName);
			currentTag.addNestedElement(property);
			tags.add(currentTag);
			currentTag = property;
//			System.out.println("[OK]");
		} else if(qualifiedName.equalsIgnoreCase("taskdef")) {
//			System.out.print("Parsing a taskdef ...");
			SimpleTaskDef taskdef = new SimpleTaskDef();
			taskdef.setTagName(qualifiedName);
			parseAttributes(attributes, taskdef, qualifiedName);
			currentTag.addNestedElement(taskdef);
			tags.add(currentTag);
			currentTag = taskdef;
//			System.out.println("[OK]");
		} else {
			if(currentTag.getType() == Tag.TARGET_TAG ) {
//				System.out.println("Parsing a task ... ");
				// Dealing with a task here
				SimpleTask task = new SimpleTask(qualifiedName);
				task.setTagName(qualifiedName);
				parseAttributes(attributes, task, qualifiedName);
				currentTag.addNestedElement(task);
				tags.add(currentTag);
				currentTag = task;
//				System.out.println("[OK]");
			} else {
				// Either parsing an unidentified target , or a nested tag.
//				System.out.print("Parsing a generic tag ... ");
				GenericElement element = new 
					GenericElement(qualifiedName);
				element.setTagName(qualifiedName);
				parseAttributes(attributes, element, qualifiedName);
				currentTag.addNestedElement(element);
				tags.add(currentTag);
				currentTag = element;
//				System.out.println("[OK]");
			}
		}
	}
}
