/*
 * Created on Aug 12, 2004
 *
 * $Id: DataInfoView.java,v 1.3 2005/01/11 00:07:42 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.view.datainfo;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.va_labs.vae.Messages;
import org.va_labs.vae.gui.Vui;
import org.va_labs.vae.gui.listener.IElementListener;
import org.va_labs.vae.gui.tag.ISwtElement;

/**
 * @author mojo_jojo
 * 
 * This views gives an array representation of the tags information.
 */
public class DataInfoView extends ViewPart implements IElementListener {
    /**
     * ID of this view.
     */
    public final static String ID = "org.va_labs.vae.gui.view.datainfo.DataInfoView";

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
        Vui vui = Vui.getVui();
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
        Table table = new Table(parent, SWT.BORDER | SWT.MULTI);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        TableColumn nameColumn = new TableColumn(table, SWT.NONE);
        nameColumn.setText(Messages.getString("Name_Column"));
        nameColumn.setWidth(70);
        TableColumn valueColumn = new TableColumn(table, SWT.NONE);
        valueColumn.setText(Messages.getString("Value_Column"));
        valueColumn.setWidth(70);

        tableViewer = new TableViewer(table);
        tableViewer.setColumnProperties(properties);

        CellEditor[] editors = { new TextCellEditor(table),
                new TextCellEditor(table) };

        tableViewer.setCellEditors(editors);

        tableViewer.setContentProvider(new DataTableContentProvider());
        tableViewer.setLabelProvider(new DataTableLabelProvider());
        tableViewer.setCellModifier(new DataCellModifier(tableViewer));
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