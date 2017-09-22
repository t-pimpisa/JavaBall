import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class DeleteGui extends JFrame{
	
	private Gui gui;
	private RefereeTable reftable;
	
	private JPanel panel;
	private JPanel pfname;
	private JPanel plname;

	private JButton delButton;
	
	private JLabel tfName;
	private JLabel tlName;
	
	private JTextField delfName;
	private JTextField dellName;
	
	private JLabel delLabel;
	
	public DeleteGui(Gui gui) {
		super("Delete Referee");
		this.gui = gui;
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
//		delLabel = new JLabel("Delete Referee Here");
		
		pfname = new JPanel();
		pfname.setPreferredSize(new Dimension(500, 50));
		pfname.setMaximumSize(new Dimension(500, 50));
		tfName = new JLabel("First Name: ");
		delfName = new JTextField("", 15);
		pfname.add(tfName);
		pfname.add(delfName);
		
		plname = new JPanel();
		plname.setPreferredSize(new Dimension(500, 150));
		plname.setMaximumSize(new Dimension(500, 150));
		tlName = new JLabel("Last Name: ");
		dellName = new JTextField("", 15);
		plname.add(tlName);
		plname.add(dellName);
		
		delButton = new JButton("Delete");
		
		delButton.addActionListener(new Handler());

//		panel.add(delLabel);	
		panel.add(pfname);
		panel.add(plname);
		panel.add(delButton);
		add(panel);
	}
	
	public class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == delButton) {
				System.out.println("This is delete button");
				if(delfName.getText().equals("") || dellName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill first name and last name", "Error", JOptionPane.ERROR_MESSAGE);
					System.out.println("delBlank");
				}
				else {
					int check = 0;
					System.out.println("First: " + delfName.getText());
					System.out.println("Last: " + dellName.getText());
					System.out.println("BeforeModelSize: " + gui.tableModel.getRowCount());
					System.out.println("BeforeArraySize: " + gui.reflist.size());
					
					for(int i = 0; i < gui.reflist.size(); i++) {
						if(gui.reflist.get(i).getfName().equals(delfName.getText()) && gui.reflist.get(i).getlName().equals(dellName.getText())) {
							System.out.println("Match then delete!!!");
//							System.out.println("BeforeDelSize: " + gui.reflist.size());
							gui.reflist.remove(i);
//							System.out.println("AfterDelSize: " + gui.reflist.size());
							gui.table.setModel(new RefereeTable(gui.reflist));
							check++;
							delfName.setText("");
							dellName.setText("");
						}
					}
					if(check == 0) {
//						String s = JOptionPane.showInputDialog(null, "Type");
						JOptionPane.showMessageDialog(null, "There are no referees having this name", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		
	}
}
