/*
 * Created on Jul 24, 2004
 *
 * $Id: RcpPerspective.java,v 1.1 2005/04/05 02:45:26 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.vae_labs.vae.gui.view.datainfo.DataInfoView;
import org.vae_labs.vae.gui.view.datatree.DataTreeView;
import org.vae_labs.vae.gui.view.graphicalrepresentation.GraphicalView;

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
        // No Editor Area is needed for the Visual Ant Editor.
        layout.setEditorAreaVisible(false);
        
        // Adding a folder for the project tree, and adding the view assiciated
        // to it.
        IFolderLayout treeFolder = layout.createFolder("treeFolder", 
                IPageLayout.LEFT, 0.25f, IPageLayout.ID_EDITOR_AREA);
        treeFolder.addView(DataTreeView.ID);
        
        // Adding a folder for the tags information array, and adding the view
        // associated to it.
        IFolderLayout infoFolder = layout.createFolder("infoFolder",
                IPageLayout.BOTTOM, 0.50f, "treeFolder");
        infoFolder.addView(DataInfoView.ID);
        
        // Adding the Graphical Representation View to the main area.
        layout.addView(GraphicalView.ID, IPageLayout.RIGHT,
                new Float(0.40).floatValue(), IPageLayout.ID_EDITOR_AREA);
    }
}
