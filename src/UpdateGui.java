import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UpdateGui extends JFrame{
	
	private Gui gui;
	
	private JPanel panel;
	private JTextField fName;
	private JTextField lName;
	private JComboBox quali;
	private JComboBox home;
	private JComboBox willing;
	
	private JButton updateButton;
	
	public UpdateGui(Gui gui) {
		super("Update Referee Detail");
		this.gui = gui;
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		JPanel borderPanel = new JPanel();
		borderPanel.setLayout(new BorderLayout());
		
		JLabel lfName = new JLabel("   Fist Name: ");
		fName = new JTextField("", 15);
		panel.add(lfName);
		panel.add(fName);
		
		JLabel llName = new JLabel("   Last Name: ");
		lName = new JTextField("", 15);
		panel.add(llName);
		panel.add(lName);
		
		JLabel lquali = new JLabel("   Qualification: ");
		String[] qualiname = {"NJB1", "NJB2", "NJB3", "NJB4", "IJB1", "IJB2", "IJB3", "IJB4"};
		quali = new JComboBox(qualiname);
		panel.add(lquali);
		panel.add(quali);
		
		JLabel lhome = new JLabel("   Home Area: ");
		String[] homename = {"North", "Central", "South"};
		home = new JComboBox(homename);
		panel.add(lhome);
		panel.add(home);
		
		JLabel lwilling = new JLabel("   Willing Area: ");
		String[] willname = {"YYY", "YYN", "YNY", "YNN", "NYY", "NYN", "NNY", "NNN"};
		willing = new JComboBox(willname);
		panel.add(lwilling);
		panel.add(willing);
		
		JPanel buttonPanel = new JPanel();
		updateButton = new JButton("Update");
		buttonPanel.add(updateButton);
		
		updateButton.addActionListener(new Handler());
		
		borderPanel.add(panel, BorderLayout.CENTER);
		borderPanel.add(buttonPanel, BorderLayout.SOUTH);
		add(borderPanel);
	}
	
	private class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == updateButton) {
				int checkUpdate = 0;
				if(fName.getText().equals("") || lName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill all textfields", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					for(int i = 0; i < gui.reflist.size(); i++) {
						if(gui.reflist.get(i).getfName().equals(fName.getText()) && gui.reflist.get(i).getlName().equals(lName.getText())) {
							gui.reflist.get(i).setQuali(quali.getSelectedItem().toString());
							gui.reflist.get(i).setHome(home.getSelectedItem().toString());
							gui.reflist.get(i).setWilling(willing.getSelectedItem().toString());
							checkUpdate++;
						}
					}
					if(checkUpdate == 0) {
						JOptionPane.showMessageDialog(null, "There are no referees having this name", "Error", JOptionPane.ERROR_MESSAGE);
					}

					gui.table.setModel(new RefereeTable(gui.reflist));
				}
			}
		}
		
	}

}
