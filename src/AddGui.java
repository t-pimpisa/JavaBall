import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;

public class AddGui extends JFrame{
	
	private Gui gui;
	
	private JPanel panel;
	
	private JLabel tfName;
	private JLabel tlName;
	private JLabel tquali;
	private JLabel tallocate;
	private JLabel thome;
	private JLabel twilling;
	
	private JTextField fName;
	private JTextField lName;
	private JComboBox quali;
	private JComboBox allocate;
	private JComboBox home;
	private JTextField willing;
	
	private JButton addButton;
	
	public AddGui(Gui gui){
		super("Add Referee");
		this.gui = gui;
		panel = new JPanel();
//		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setLayout(new GridLayout(7, 2));
		
		tfName = new JLabel("   First Name: ");
		fName = new JTextField("", 15);
		panel.add(tfName);
		panel.add(fName);
		
		tlName = new JLabel("   Last Name: ");
		lName = new JTextField("", 15);
		panel.add(tlName);
		panel.add(lName);
		
		tquali = new JLabel("   Qualification: ");
		panel.add(tquali);
		String[]	level = {"NJB1", "NJB2", "NJB3", "NJB4", "IJB1", "IJB2", "IJB3", "IJB4"};
		quali = new JComboBox(level);
		quali.setEditable(false);
		panel.add(quali);
		
		Integer[] allonum = new Integer[100];
		for(int allocnt = 0; allocnt < 100; allocnt++) {
			allonum[allocnt] = allocnt + 1;
		}
		tallocate = new JLabel("   Number of Allocation: ");
		allocate = new JComboBox(allonum);
		panel.add(tallocate);
		panel.add(allocate);

		String[] areaname = {"North", "Central", "South"};
		thome = new JLabel("   Home Area: ");
		home = new JComboBox(areaname);
		panel.add(thome);
		panel.add(home);
		
		twilling = new JLabel("   Willing Area: ");
		willing = new JTextField("", 15);
		panel.add(twilling);
		panel.add(willing);
		
		addButton = new JButton("Add");
		
		addButton.addActionListener(new Handler());
		
		panel.add(new JLabel(""));
		panel.add(addButton);
		
		add(panel);	
	}
	
	public class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == addButton) {
				/* Check Blank Field */
				if(fName.getText().equals("") || lName.getText().equals("") || quali.getSelectedItem().toString().equals("") || allocate.getSelectedItem().toString().equals("") || home.getSelectedItem().toString().equals("") || willing.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Please fill all textfields", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					int checkError = 0;
					String forRefId = "";
					int number = 0;
					String tmpId = fName.getText().charAt(0) + "" + lName.getText().charAt(0);
//					System.out.println("TmpId: " + tmpId);
					
					for(int i = 0; i < gui.reflist.size(); i++) {
						/* Check name, If same, throw error */
						if(fName.getText().equals(gui.reflist.get(i).getfName()) && lName.getText().equals(gui.reflist.get(i).getlName())) {
							JOptionPane.showMessageDialog(null, "This name is already existed.", "Error", JOptionPane.ERROR_MESSAGE);
							System.out.println("Same Name");
							checkError++;
						}
						
						/* Check Id */
						String idInArray = gui.reflist.get(i).getfName().charAt(0) + "" + gui.reflist.get(i).getlName().charAt(0);
//						System.out.println("IdInArray: " + idInArray);
						if(tmpId.equals(idInArray)) {
							number = Integer.parseInt("" + gui.reflist.get(i).getRefID().charAt(2));
//							System.out.println("ID Number: " + number);
						}
					}
					
					if(checkError == 0) {
						/* get RefID */
						forRefId = tmpId + "" + (number + 1);
//						System.out.println("Ref ID for new ref: " + forRefId);
						/* add */
						Referee newRef = new Referee(forRefId, fName.getText(), lName.getText(), quali.getSelectedItem().toString(), Integer.parseInt(allocate.getSelectedItem().toString()), home.getSelectedItem().toString(), willing.getText());
						gui.reflist.add(newRef);
						Collections.sort(gui.reflist);
						gui.table.setModel(new RefereeTable(gui.reflist));
						fName.setText("");
						lName.setText("");
					}
				}
			}
		}
		
	}

}
