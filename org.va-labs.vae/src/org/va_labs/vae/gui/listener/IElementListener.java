/*
 * Created on Oct 10, 2004
 *
 * $Id: IElementListener.java,v 1.1 2004/10/11 19:46:44 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.listener;

import org.va_labs.vae.gui.tag.ISwtElement;

/**
 * @author mojo_jojo This interface shall be implemented by all entities that
 *         want to know when the current ISwtElement has been changed.
 */
public interface IElementListener {

    /**
     * Updates the ISwtElement currently considered by the user to the listener.
     * 
     * @param element
     *            currently considered element.
     */
    void setCurrentElement(ISwtElement element);
}