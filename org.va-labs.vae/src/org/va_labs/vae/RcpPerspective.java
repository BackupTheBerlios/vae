/*
 * Created on Jul 24, 2004
 *
 * $Id: RcpPerspective.java,v 1.1 2004/08/07 10:15:17 mojo_jojo Exp $
 */
package org.va_labs.vae;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @author mojo_jojo
 * Pilots the Visual Ant Editor eclipse perspective.
 */
public class RcpPerspective implements IPerspectiveFactory 
{
    /**
     * Default constructor.
     * Doesn't do anythign at all for now.
     */
    public RcpPerspective() 
    {
        
    }
    
    /**
     * Creates the initial screen of the Vae plugin.
     * @param layout layout provided by eclipse so we can take care of what
     * we have to do.
     * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
     */
    public void createInitialLayout(IPageLayout layout) 
    {
    
    }

}
