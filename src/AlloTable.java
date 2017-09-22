import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;

public class AlloTable implements TableModel{
	
	private Gui gui;
	
	private String[] columnName = {"First Name", "Last Name", "Number of Allocation"};
	private ArrayList<MatchAllo> allo;
	
	public DefaultTableModel tableModel;
	
	public AlloTable(ArrayList<MatchAllo> allo) {
		this.allo = allo;
		
		tableModel = new DefaultTableModel(columnName, 0);
		
		for(int i = 0; i < allo.size(); i ++) {
			String a = allo.get(i).getfName();
			String b = allo.get(i).getlName();
			int c = allo.get(i).getAllo();
			
			Object[] data = {a, b, c};
			
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
