/*
 * Created on Aug 4, 2004
 *
 * $Id: DataTreeView.java,v 1.2 2004/09/05 20:36:31 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.view.datatree;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.va_labs.vae.Messages;
import org.va_labs.vae.gui.Vui;

/**
 * @author mojo_jojo
 * 
 * This view shows a tree of the differents tags of the ant project file.
 */
public class DataTreeView extends ViewPart {
    public final static String ID = "org.va_labs.vae.gui.view.datatree.DataTreeView";

    /**
     * Reference to the treeViewer displaying the project Tree.
     */
    private TreeViewer projectTree;

    /**
     * Register the view to the vui instance.
     */
    public DataTreeView() {
        Vui vui = Vui.getVui();
        vui.registerTreeView(this);
    }

    /**
     * Creates the view.
     * 
     * @param parent
     *            parent Composite of the view to be created.
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        // Indicates the title of the view.
        setPartName(Messages.getString("Data_Tree"));

        parent.setLayout(new FillLayout());

        // Setting up the TreeViewer.
        projectTree = new TreeViewer(parent, SWT.BORDER | SWT.MULTI
                | SWT.V_SCROLL | SWT.H_SCROLL);

        projectTree.setContentProvider(new ProjectTreeContentProvider());
        projectTree.setLabelProvider(new ProjectTreeLabelProvider());
    }

    /**
     * This method is called upon the view being rendered within the workbench.
     * 
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() {

    }

    /**
     * Sets a new input for the project tree.
     * 
     * @param input
     *            new input to be used by the content provider for the project
     *            tree.
     */
    public void setTreeInput(Object input) {
        projectTree.setInput(input);
    }
}