/*
 * Created on Aug 4, 2004
 *
 * $Id: DataTreeView.java,v 1.1 2004/08/17 22:41:51 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.views;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.va_labs.vae.Messages;
import org.va_labs.vae.gui.views.ProjectTreeContentProvider;
import org.va_labs.vae.gui.views.ProjectTreeLabelProvider;

/**
 * @author mojo_jojo
 * This view shows a tree of the differents tags of the ant project file.
 * TODO Implement the DataTreeView. 
 */
public class DataTreeView extends ViewPart 
{
    public final static String ID = "org.va_labs.vae.gui.views.DataTreeView";
    
    /**
     * Creates the view.
     * @param parent parent Composite of the view to be created.
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) 
    {
        // Indicates the title of the view.
        setPartName(Messages.getString("Data_Tree"));
        
        // Setting up the TreeViewer.
//		TreeViewer treeViewer = new TreeViewer(parent, 
//			SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
//		
//		treeViewer.setContentProvider(new ProjectTreeContentProvider());
//		treeViewer.setLabelProvider(new ProjectTreeLabelProvider());
    }

    /**
     * This method is called upon the view being rendered within the workbench.
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() 
    {
        
    }
}
