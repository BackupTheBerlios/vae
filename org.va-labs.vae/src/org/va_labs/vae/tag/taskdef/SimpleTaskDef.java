/*
 * Created on Sep 5, 2004
 *
 * $Id: SimpleTaskDef.java,v 1.1 2004/09/05 00:29:00 mojo_jojo Exp $
 */
package org.va_labs.vae.tag.taskdef;

import org.va_labs.vae.parser.IllegalAttributeException;
import org.va_labs.vae.tag.Tag;

/**
 * @author mojo_jojo
 *
 * Holds the information regarding a taskdef ant task.
 * More informations about taskdef ant tasks at
 * http://ant.apache.org/manual/CoreTasks/taskdef.html
 */
public class SimpleTaskDef extends TaskDef {

    private static final String [] legalAttributes = new String [] {
            "name", "classname", "resource", "file", "classpath", 
            "classpathref", "loaderref"
    };
                                                         	
    /**
     * Name of the class implementing the ant task. 
     */
    private StringBuffer className;
    
    /**
     * the classpath to use when looking up classname or resource.
     */
    private StringBuffer classPath;
    
    /**
     * Reference to a classPath to use when looking up a classname or resource.
     */
    private StringBuffer classPathRef;
    
    /**
     * Name of the property file to load taskname/classname pairs from.
     */
    private StringBuffer file;
    
    /**
     * Name of the loader that is used to load the class, constructed from 
     * the specified classpath.
     */
    private StringBuffer loaderReference;
    
    /**
     * Name of the property resource to load taskname/classname pairs from.
     */
    private StringBuffer resource;
    
    public SimpleTaskDef()
    {
        super();
        // Sets the name to anonymous in case it's not set in one of 
        // the attributes.
        name = new StringBuffer("Anonymous Taskdef");
    }
    
    /**
     * Creates a Taskdef from a generic Tag.
     * @param taskdefTag the generic tag that holds the info we need.
     */
    public SimpleTaskDef(Tag taskdefTag)
    {
        this();
        setNestedElements(taskdefTag.getNestedElements());
        try {
            setAttributes(taskdefTag.getAttributes());
        } catch (IllegalAttributeException e) {
            // TODO should send a warning to the user through a Validator.
            System.out.println("Invalid taskdef attribute detected !");
        }
    }
    
    /**
     * Gets the name of the class implementing the ant task in a string.
     * @return the name of the class implementing the ant task in a string.
     * Note : can return null.
     */
    public String getClassName() 
    {
        if(className != null) {
            return className.toString();
        } else {
            return null;
        }
    }
    
    /**
     * Get the class path.
     * @return the class path in a String.
     * Note : can return null.
     */
    public String getClassPath() 
    {
        if(classPath != null) {
            return classPath.toString();
        } else {
            return null;
        }
    }
    
    /**
     * Gets a reference to a class path.
     * @return a reference to a class path in a String.
     * Note : can return null.
     */
    public String getClassPathRef() 
    {
        if(classPathRef != null) {
            return classPathRef.toString();
        } else {
            return null;
        }
    }
    
    /**
     * Gets the name of the property file to load taskname/classname pairs from.
     * @return a filename in a String.
     * Note : can return null.
     */
    public String getFile() 
    {
        if(file != null) {
            return file.toString();
        } else {
            return null;
        }
    }
    
    /**
     * Gets the Loader reference in a String.
     * @return a loader reference in a String.
     * Nore : Can return null.
     */
    public String getLoaderReference() 
    {
        if( loaderReference != null) {
            return loaderReference.toString();
        } else {
            return null;
        }
    }
    
    /**
     * Get the name of the property resource to load taskname/classname 
     * pairs from.
     * @return a property resource name in a String.
     * Note : Can return null.
     */
    public String getResource() 
    {
        if(resource != null ) {
            return resource.toString();
        } else {
            return null;
        }
    }
    
    /**
     * Gets the name of the defined ant task.
     * @return the name of the defined ant task in a String.
     * Note : can return null.
     */
    public String getTaskdefName() 
    {
        return getName();
    }
    
    protected void removeKeyAttributes(String attributeName)
    {
        if(attributeName.equalsIgnoreCase("name")) {
            name = null;
        } else if(attributeName.equals("name")) {
            name = null;
        } else if (attributeName.equals("classname")) {
            className = null;
        } else if (attributeName.equals("resource")) {
            resource = null;
        } else if (attributeName.equals("file")) {
            file = null;
        } else if (attributeName.equals("classpath")) {
            // Watchout the path can be a nested tag as well.
            classPath = null;
        } else if (attributeName.equals("classpathref")) {
            classPathRef = null;
        } else if (attributeName.equals("loaderref")) {
            loaderReference = null;
        } 
    }
    
    /**
     * Sets the name of the class implementing the ant task.
     * @param the name of the class implementing the ant task in a string.
     */
    public void setClassName(String className) 
    {
        this.className = new StringBuffer(className);
    }
    
    /**
     * Sets the class Path.
     * @param the class Path in a String.
     */
    public void setClassPath(String classPath) 
    {
        this.classPath = new StringBuffer(classPath);
    }
    
    /**
     * Sets a reference to a class path.
     * @param a reference to a class path in a String.
     */
    public void setClassPathRef(String classPathRef) 
    {
        this.classPathRef = new StringBuffer(classPathRef);
    }
    
    /**
     * Sets the name of the property file to load taskname/classname pairs from.
     * @param a property filename in a String.
     */
    public void setFile(String file) 
    {
        this.file = new StringBuffer(file);
    }
    
    /**
     * Sets the Loader reference.
     * @param the loader reference in a String.
     */
    public void setLoaderReference(String loaderReference) 
    {
        this.loaderReference = new StringBuffer(loaderReference);
    }
    
    /**
     * Set the name of the property resource to load taskname/classname pairs from.
     * @param a property resource name in a String. 
     */
    public void setResource(String resource) 
    {
        this.resource = new StringBuffer(resource);
    }
    
    /**
     * Sets the xml name of this tag.
     * @see org.vae.core.Tag#setTagName(java.lang.String)
     */
    public void setTagName(String xmlName)
    {
        tagName = new StringBuffer(xmlName);
    }
    
    /**
     * Sets that name of the defined ant task.
     * @param the name of the ant task in a String.
     */
    public void setTaskdefName(String taskName) 
    {
        name = new StringBuffer(taskName);
    }
    
    /**
     * Verify that this attribute is a valid TaskDef attribute.
     * @param attribName name of the attribute.
     * @param attribValue value of the attribute.
     * @throws IllegalAttributeException if the attribute is not a legal
     * TaskDef attribute.
     */
    protected void verifyAttribute(String attribName, String attribValue)
    throws IllegalAttributeException
    {
        if(attribName.equals("name")) {
            setName(attribValue);
        } else if (attribName.equals("classname")) {
            setClassName(attribValue);
        } else if (attribName.equals("resource")) {
            setResource(attribValue);
        } else if (attribName.equals("file")) {
            setFile(attribValue);
        } else if (attribName.equals("classpath")) {
            //TODO: Watch out the classpath can be set with nested tag.  
            setClassPath(attribValue);
        } else if (attribName.equals("classpathref")) {
            setClassPathRef(attribValue);
        } else if (attribName.equals("loaderref")) {
            setLoaderReference(attribValue);
        } else {
            throw new IllegalAttributeException(attribName, 
                    name.toString(), legalAttributes);
        }
    }
}
