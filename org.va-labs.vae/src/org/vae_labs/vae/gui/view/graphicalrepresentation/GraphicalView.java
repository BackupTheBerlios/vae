/*
 * Created on Aug 12, 2004
 *
 * $Id: GraphicalView.java,v 1.1 2005/04/05 02:45:26 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.view.graphicalrepresentation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.vae_labs.vae.Messages;

/**
 * @author mojo_jojo
 * 
 * View of the graphical representation of the ant projects.
 * TODO Implement the GraphicalView class.
 */
public class GraphicalView extends ViewPart 
{

    public static final String ID = 
        "org.vae_labs.vae.gui.view.graphicalrepresentation.GraphicalView";

    /**
     * Creation of the Graphical Representation View.
     * Sets the title of the view.
     * @param parent the parent Composite of the view.
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) 
    {
        setPartName(Messages.getString("Data_Graph"));
    }

    /**
     * This method is called upon the view being rendered within the workbench.
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() 
    {

    }
}
