/*
 * Created on Aug 12, 2004
 *
 * $Id: DataInfoView.java,v 1.2 2006/05/07 10:49:15 mojo_jojo Exp $
 */
package org.vae_labs.vae.gui.view.datainfo;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.vae_labs.vae.Messages;
import org.vae_labs.vae.core.TagAttribute;
import org.vae_labs.vae.gui.Vui;
import org.vae_labs.vae.gui.listener.IElementListener;
import org.vae_labs.vae.gui.tag.ISwtElement;

/**
 * @author mojo_jojo
 * 
 * This views gives an array representation of the tags information.
 */
public class DataInfoView extends ViewPart implements IElementListener {
    /**
     * ID of this view.
     */
    public final static String ID = "org.vae_labs.vae.gui.view.datainfo.DataInfoView";

    /**
     * Name of the strings which we need to identify columns for cell modifiers.
     */
    private final String[] properties = new String[] {
            Messages.getString("Name_Column"),
            Messages.getString("Value_Column") };

    /**
     * Reference to the TableViewer of this view.
     */
    private TableViewer tableViewer;

    /**
     * Registers the view to the vui instance.
     */
    public DataInfoView() {
        Vui vui = Vui.getInstance();
        vui.registerInfoView(this);
        // We will be kept informed of the user's focus.
        vui.addElementListener(this);
    }

    /**
     * Creation of the Data Information view. Sets the title of the view.
     * 
     * @param parent
     *            the parent of the View to be created.
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) {
        setPartName(Messages.getString("Data_Info"));

        // Setting up the TableViewer.
        tableViewer = new TableViewer(parent, SWT.BORDER | SWT.MULTI);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(50, true));
        layout.addColumnData(new ColumnWeightData(50, true));

        tableViewer.getTable().setLayout(layout);
        tableViewer.getTable().setLinesVisible(true);
        tableViewer.getTable().setHeaderVisible(true);

        tableViewer.setContentProvider(new DataTableContentProvider());
        tableViewer.setLabelProvider(new DataTableLabelProvider());
        tableViewer.setCellModifier(new DataCellModifier());
        tableViewer.setCellEditors(new CellEditor[] {
                new TextCellEditor(tableViewer.getTable()),
                new TextCellEditor(tableViewer.getTable()) });
        tableViewer.setColumnProperties(properties);

        TableColumn nameColumn = new TableColumn(tableViewer.getTable(),
                SWT.NONE);
        nameColumn.setText(Messages.getString("Name_Column"));
        TableColumn dataColumn = new TableColumn(tableViewer.getTable(),
                SWT.NONE);
        dataColumn.setText(Messages.getString("Value_Column"));

    }

    /**
     * Returns the table viewer of the view.
     * 
     * @return the table viewer.
     */
    public StructuredViewer getTableViewer() {
        return tableViewer;
    }

    /**
     * Refreshes the Viewer concerning the given tagAttribute.
     * 
     * @param tagAttribute
     *            the edited TagAttribute.
     */
    public void refresh(TagAttribute tagAttribute) {
        tableViewer.refresh(tagAttribute);
    }

    /**
     * Called when the user has changed the element he is considering.
     * 
     * @param element
     *            the element that has the user's attention.
     */
    public void setCurrentElement(ISwtElement element) {
        tableViewer.setInput(element);
    }

    /**
     * This method is called upon the view being rendered within the workbench.
     * 
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() {

    }

}