/*
 * Created on Jul 24, 2004
 *
 * $Id: RcpVae.java,v 1.1 2005/04/05 02:45:23 mojo_jojo Exp $
 */
package org.vae_labs.vae;

import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;

/**
 * @author mojo_jojo
 *
 * This class is ran by the eclipse Ide when the user launches the Vae 
 * plugin.
 */
public class RcpVae implements IPlatformRunnable 
{

	/**
	 * This is the actual method that get called by eclipse.
	 * Creates the workbench advisor, the display and launch the whole
	 * thing.
	 * @see org.eclipse.core.runtime.IPlatformRunnable#run(java.lang.Object)
	 */
	public Object run(Object args)
	{
		WorkbenchAdvisor workbenchAdvisor = new RcpWorkbenchAdvisor();
		Display display = PlatformUI.createDisplay();
		try {
		    int returnCode = PlatformUI.createAndRunWorkbench(display,
		            workbenchAdvisor);
		    if (returnCode == PlatformUI.RETURN_RESTART) {
		        return IPlatformRunnable.EXIT_RESTART;
		    } else {
		        return IPlatformRunnable.EXIT_OK;
		    }
		} finally {
		    // It's over so let's dispose of the display.
		    display.dispose();
		}
	}
}
