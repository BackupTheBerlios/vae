/*
 * Created on Aug 4, 2004
 *
 * $Id: DataTreeView.java,v 1.1 2004/09/05 00:23:22 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.view.datatree;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.va_labs.vae.Messages;
import org.va_labs.vae.core.Vae;
import org.va_labs.vae.gui.SwtVae;

/**
 * @author mojo_jojo
 * 
 * This view shows a tree of the differents tags of the ant project file.
 */
public class DataTreeView extends ViewPart 
{
    public final static String ID = 
        "org.va_labs.vae.gui.view.datatree.DataTreeView";
    
    /**
     * Creates the view.
     * @param parent parent Composite of the view to be created.
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) 
    {
        // Indicates the title of the view.
        setPartName(Messages.getString("Data_Tree"));
        
        parent.setLayout(new FillLayout());
        
        // Setting up the TreeViewer.
		TreeViewer treeViewer = new TreeViewer(parent, 
			SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		
		// Initialise the main component and it's Swt extention.
		SwtVae swtVae = new SwtVae(new Vae());
		
		treeViewer.setContentProvider(new ProjectTreeContentProvider());
		treeViewer.setLabelProvider(new ProjectTreeLabelProvider());
		treeViewer.setInput(swtVae);
    }

    /**
     * This method is called upon the view being rendered within the workbench.
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() 
    {
        
    }
}
