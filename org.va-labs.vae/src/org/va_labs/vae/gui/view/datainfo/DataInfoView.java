/*
 * Created on Aug 12, 2004
 *
 * $Id: DataInfoView.java,v 1.1 2004/09/05 00:23:04 mojo_jojo Exp $
 */
package org.va_labs.vae.gui.view.datainfo;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.va_labs.vae.Messages;

/**
 * @author mojo_jojo
 * 
 * This views gives an array representation of the tags information.
 */
public class DataInfoView extends ViewPart {
	/**
	 * ID of this view.
	 */
    public final static String ID = 
        "org.va_labs.vae.gui.view.datainfo.DataInfoView";
    
    /**
     * Name of the strings which we need to identify columns for cell
     * modifiers.
     */
    private final String[] properties = new String[] {
			Messages.getString("Name_Column"),
			Messages.getString("Value_Column")
	};

    /**
     * Creation of the Data Information view.
     * Sets the title of the view.
     * @param parent the parent of the View to be created.
     * @see org.eclipse.ui.IWorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
     */
    public void createPartControl(Composite parent) 
    {
        setPartName(Messages.getString("Data_Info"));
        
        // Setting up the TableViewer.
		Table table = new Table (parent, SWT.BORDER|SWT.MULTI);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		TableColumn nameColumn = new TableColumn(table, SWT.NONE);
		nameColumn.setText("Name");
		nameColumn.setWidth(150);
		TableColumn valueColumn = new TableColumn(table, SWT.NONE);
		valueColumn.setText("Value");
		valueColumn.setWidth(150);

		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setColumnProperties(properties);

		CellEditor[] editors = {
			new TextCellEditor(table),
			new TextCellEditor(table)
		};
		
		tableViewer.setCellEditors(editors);
		
		tableViewer.setContentProvider(new DataTableContentProvider());
		tableViewer.setLabelProvider(new DataTableLabelProvider());
		tableViewer.setCellModifier(new DataCellModifier(tableViewer));
    }
    
    /**
     * This method is called upon the view being rendered within the workbench.
     * @see org.eclipse.ui.IWorkbenchPart#setFocus()
     */
    public void setFocus() 
    {

    }
}
