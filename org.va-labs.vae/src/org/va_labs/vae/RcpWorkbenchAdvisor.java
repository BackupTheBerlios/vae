/*
 * Created on Jul 24, 2004
 *
 * $Id: RcpWorkbenchAdvisor.java,v 1.1 2004/08/07 10:15:17 mojo_jojo Exp $
 */
package org.va_labs.vae;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;

/**
 * @author mojo_jojo
 * Customize the workbench for the Visual Ant Editor.
 */
public class RcpWorkbenchAdvisor extends WorkbenchAdvisor 
{
    
    public void preWindowOpen(IWorkbenchWindowConfigurer configurer) {
        super.preWindowOpen(configurer);
        configurer.setInitialSize(new Point(400, 300));
        configurer.setTitle(Messages.getString("VAE")); //$NON-NLS-1$
    }
    
    /**
     * Used to get the perspective id that we will be using for the visual
     * ant editor.
     * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
     */
    public String getInitialWindowPerspectiveId() 
    {
        return "org.va_labs.vae.RcpPerspective"; //$NON-NLS-1$
    }

}
