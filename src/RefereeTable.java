import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;

public class RefereeTable implements TableModel{
	
	private Gui gui;
	
	private String[] columnName = {"Referee ID", "FirstName", "LastName", "Qualification", "Number of allocation", "Home Area", "Willing Area"};
	private ArrayList<Referee> reflist;
	
	public DefaultTableModel tableModel;
	
	public RefereeTable(ArrayList<Referee> reflist) {
		this.reflist = reflist;
		
		tableModel = new DefaultTableModel(columnName, 0);
		
		for(int i = 0; i < reflist.size(); i ++) {
			String a = reflist.get(i).getRefID();
			String b = reflist.get(i).getfName();
			String c = reflist.get(i).getlName();
			String d = reflist.get(i).getQuali();
			int e = reflist.get(i).getAllocation();
			String f = reflist.get(i).getHome();
			String g = reflist.get(i).getWilling();
			
			Object[] data = {a, b, c, d, e, f, g};
			
			tableModel.addRow(data);
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return tableModel.getRowCount();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return tableModel.getColumnCount();
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return tableModel.getColumnName(columnIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return tableModel.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return tableModel.isCellEditable(rowIndex, columnIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return tableModel.getValueAt(rowIndex, columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	
	

}
