/*
 * Created on Aug 12, 2004
 *
 * $Id: DataInfoView.java,v 1.1 2004/08/17 22:41:51 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.va_labs.vae.Messages;

/**
 * @author mojo_jojo
 * This views gives an array representation of the tags information.
 * TODO Implement the DataInfoView class.
 */
public class DataInfoView extends ViewPart 
{

    public final static String ID = "org.va_labs.vae.gui.views.DataInfoView";

    /**
     * Creation of the Data Information view.
     * Sets the title of the view.
     * @param parent the parent of the View to be created.
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) 
    {
        setPartName(Messages.getString("Data_Info"));
    }
    
    /**
     * This method is called upon the view being rendered within the workbench.
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() 
    {

    }
}
