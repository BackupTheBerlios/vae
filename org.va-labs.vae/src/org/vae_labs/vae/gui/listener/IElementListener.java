/*
 * Created on Oct 10, 2004
 *
 * $Id: IElementListener.java,v 1.1 2005/04/05 02:45:27 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.listener;

import org.vae_labs.vae.gui.tag.ISwtElement;

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