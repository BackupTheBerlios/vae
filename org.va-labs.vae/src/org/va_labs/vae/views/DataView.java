/*
 * Created on Aug 4, 2004
 *
 * $Id: DataView.java,v 1.1 2004/08/07 10:15:17 mojo_jojo Exp $
 */
package org.va_labs.vae.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.va_labs.vae.Messages;

/**
 * @author mojo_jojo
 * This view gives a tree of the differents tags of the ant project file. 
 */
public class DataView extends ViewPart 
{
    /**
     * Called when the view is created.
     * Sets the title and all that needs to be done.
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) 
    {
        setPartName(Messages.getString("Data_Info"));
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() {
        // TODO Auto-generated method stub
        
    }

}
