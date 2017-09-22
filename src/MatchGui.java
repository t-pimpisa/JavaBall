import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MatchGui extends JFrame{

	private Gui gui;
	
	private JComboBox weekList;
	private JComboBox areaList;
	private JComboBox levelList;
	private JTable jtable;
	private JButton startButton;
	private JLabel allocateWk;
	private JLabel ref1Name;
	private JLabel ref2Name;

	private static ArrayList<String> weekNo = new ArrayList<String>();
	private ArrayList<MatchAllo> suitRef;
	private DefaultTableModel resultModel;
	
	public MatchGui(Gui gui) {
		super("Referee Allocation");
		this.gui = gui;
		JPanel panelInput = new JPanel();
		panelInput.setLayout(new GridLayout(2, 3));
		JPanel panelOutput = new JPanel();
		panelOutput.setLayout(new BorderLayout());
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new GridLayout(1, 3));
		
		/* Input Panel */
		JLabel week = new JLabel("Week");
		week.setHorizontalAlignment(JLabel.CENTER);
		JLabel area = new JLabel("Area");
		area.setHorizontalAlignment(JLabel.CENTER);
		JLabel Level = new JLabel("Level");
		Level.setHorizontalAlignment(JLabel.CENTER);
		
		for(int i = 1; i <= 52; i++) {
			weekNo.add(Integer.toString(i));
		}
		String[] num = weekNo.toArray(new String[0]);
		weekList = new JComboBox(num);
		
		String[] areaName = {"North", "Central", "South"};
		areaList = new JComboBox(areaName);
		
		String[] levelName = {"Junior", "Senior"};
		levelList = new JComboBox(levelName);
		
		panelInput.add(week);
		panelInput.add(area);
		panelInput.add(Level);
		panelInput.add(weekList);
		panelInput.add(areaList);
		panelInput.add(levelList);
		
		/* Output Panel */
		JPanel subOutput = new JPanel();
		subOutput.setLayout(new GridLayout(3, 2));
		JLabel topicList = new JLabel("Suitable Referee List");
		allocateWk = new JLabel("");
		JLabel tref1Name = new JLabel("First Referee: ");
		ref1Name = new JLabel("");
		JLabel tRef2Name = new JLabel("Second Referee: ");
		ref2Name = new JLabel("");
		subOutput.add(topicList);
		subOutput.add(allocateWk);
		subOutput.add(tref1Name);
		subOutput.add(ref1Name);
		subOutput.add(tRef2Name);
		subOutput.add(ref2Name);
		String[] columnName = {"First Name", "Last Name", "Number of Allocation"};
		resultModel = new DefaultTableModel(columnName, 0);
		jtable = new JTable(resultModel);	
		panelOutput.add(subOutput, BorderLayout.NORTH);
		panelOutput.add(new JScrollPane(jtable), BorderLayout.CENTER);
		
		/* Button Panel */
		JLabel emp1 = new JLabel("");
		JLabel emp2 = new JLabel("");
		startButton = new JButton("Start");
		startButton.addActionListener(new Handler());
		
		panelButton.add(emp1);
		panelButton.add(emp2);
		panelButton.add(startButton);
		
		
		add(panelInput, BorderLayout.NORTH);
		add(panelOutput, BorderLayout.CENTER);
		add(panelButton, BorderLayout.SOUTH);
	}
	
	private class Handler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {	
			if(e.getSource() == startButton) {
				System.out.println("Press Matching");
				suitRef = new ArrayList<MatchAllo>();
				jtable.setModel(new AlloTable(suitRef));
				ArrayList<MatchAllo> junior1 = new ArrayList<MatchAllo>();
				ArrayList<MatchAllo> junior2 = new ArrayList<MatchAllo>();
				ArrayList<MatchAllo> junior3 = new ArrayList<MatchAllo>();
				ArrayList<MatchAllo> senior1 = new ArrayList<MatchAllo>();
				ArrayList<MatchAllo> senior2 = new ArrayList<MatchAllo>();
				ArrayList<MatchAllo> senior3 = new ArrayList<MatchAllo>();
				
				ArrayList<String> junior = new ArrayList<String>();
				junior.add("NJB1");
				junior.add("IJB1"); 
				junior.add("NJB2");
				junior.add("IJB2");
				junior.add("NJB3");
				junior.add("IJB3"); 
				junior.add("NJB4");
				junior.add("IJB4"); 
				ArrayList<String> senior = new ArrayList<String>();
				senior.add("NJB2");
				senior.add("IJB2");
				senior.add("NJB3");
				senior.add("IJB3"); 
				senior.add("NJB4");
				senior.add("IJB4"); 
				/* Junior Match -> all referees */
				if(levelList.getSelectedItem().toString().equals("Junior")) {
					for(int i = 0; i < gui.reflist.size(); i++) {
						if(junior.contains(gui.reflist.get(i).getQuali())) {
							/* choose referee from home area */
							if(gui.reflist.get(i).getHome().equals(areaList.getSelectedItem().toString())) {
								MatchAllo matcheRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								junior1.add(matcheRef);
							}
							/* Adjacent - North - Central */
							else if(areaList.getSelectedItem().toString().equals("North") && gui.reflist.get(i).getHome().equals("Central") && (gui.reflist.get(i).getWilling().charAt(0) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								junior2.add(tmpNRef);
							}
							/* Adjacent - Central - North */
							else if(areaList.getSelectedItem().toString().equals("Central") && gui.reflist.get(i).getHome().equals("North") && (gui.reflist.get(i).getWilling().charAt(1) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								junior2.add(tmpNRef);
							}
							/* Adjacent - Central - South */
							else if(areaList.getSelectedItem().toString().equals("Central") && gui.reflist.get(i).getHome().equals("South") && (gui.reflist.get(i).getWilling().charAt(1) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								junior2.add(tmpNRef);
							}
							/* Adjacent - South - Central */
							else if(areaList.getSelectedItem().toString().equals("South") && gui.reflist.get(i).getHome().equals("Central") && (gui.reflist.get(i).getWilling().charAt(2) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								junior2.add(tmpNRef);
							}
							/* Not adjacent - North - South */
							else if(areaList.getSelectedItem().toString().equals("North") && gui.reflist.get(i).getHome().equals("South") && (gui.reflist.get(i).getWilling().charAt(0) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								junior3.add(tmpNRef);
							}
							/* Not adjacent - South - North */
							else if(areaList.getSelectedItem().toString().equals("South") && gui.reflist.get(i).getHome().equals("North") && (gui.reflist.get(i).getWilling().charAt(2) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								junior3.add(tmpNRef);
							}
						}
					}
					Collections.sort(junior1);
					Collections.sort(junior2);
					Collections.sort(junior3);
					for(MatchAllo j1: junior1)
						suitRef.add(j1);
					for(MatchAllo j2: junior2)
						suitRef.add(j2);
					for(MatchAllo j3: junior3)
						suitRef.add(j3);
					
					if(suitRef.size() >= 2) {
						/* Keep Result */
						String r1 = weekList.getSelectedItem().toString();
						String r2 = levelList.getSelectedItem().toString();
						String r3 = areaList.getSelectedItem().toString();
						String r4 = suitRef.get(0).getfName() + " " + suitRef.get(0).getlName();
						String r5 = suitRef.get(1).getfName() + " " + suitRef.get(1).getlName();
						System.out.println("Ref1: " + r4);
						System.out.println("Ref2: " + r5);
						ResultMatch resultAllocate = new ResultMatch(r1, r2, r3, r4, r5);
						gui.result.add(resultAllocate);
						
						/* Update allocation */
						for(int num = 0; num < gui.reflist.size(); num++) {
							if(gui.reflist.get(num).getfName().equals(suitRef.get(0).getfName()) && gui.reflist.get(num).getlName().equals(suitRef.get(0).getlName())) {
								int increase = gui.reflist.get(num).getAllocation() + 1;
								gui.reflist.get(num).setAllocation(increase);
							}
							else if(gui.reflist.get(num).getfName().equals(suitRef.get(1).getfName()) && gui.reflist.get(num).getlName().equals(suitRef.get(1).getlName())) {
								int increase = gui.reflist.get(num).getAllocation() + 1;
								gui.reflist.get(num).setAllocation(increase);
							}
						}
						gui.table.setModel(new RefereeTable(gui.reflist));
					}
					else
						JOptionPane.showMessageDialog(null, "JavaBall needs 2 referees for each match.", "Error", JOptionPane.ERROR_MESSAGE);
					
					jtable.setModel(new AlloTable(suitRef));
					
					/* Show 2 Referees Name */
					allocateWk.setText("Week: " + weekList.getSelectedItem().toString());
					ref1Name.setText(suitRef.get(0).getfName() + " " + suitRef.get(0).getlName());
					ref2Name.setText(suitRef.get(1).getfName() + " " + suitRef.get(1).getlName());
					
					/* Check week */
					weekNo.remove(weekList.getSelectedItem().toString());
					DefaultComboBoxModel model = new DefaultComboBoxModel(weekNo.toArray());
					weekList.setModel(model);
				}
				
				/* Senior Match -> level 2-4 */
				else if(levelList.getSelectedItem().toString().equals("Senior")){
					for(int i = 0; i < gui.reflist.size(); i++) {
						if(senior.contains(gui.reflist.get(i).getQuali())) {
							/* choose referee from home area */
							if(gui.reflist.get(i).getHome().equals(areaList.getSelectedItem().toString())) {
								MatchAllo matcheRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								senior1.add(matcheRef);
							}
							/* Adjacent - North - Central */
							else if(areaList.getSelectedItem().toString().equals("North") && gui.reflist.get(i).getHome().equals("Central") && (gui.reflist.get(i).getWilling().charAt(0) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								senior2.add(tmpNRef);
							}
							/* Adjacent - Central - North */
							else if(areaList.getSelectedItem().toString().equals("Central") && gui.reflist.get(i).getHome().equals("North") && (gui.reflist.get(i).getWilling().charAt(1) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								senior2.add(tmpNRef);
							}
							/* Adjacent - Central - South */
							else if(areaList.getSelectedItem().toString().equals("Central") && gui.reflist.get(i).getHome().equals("South") && (gui.reflist.get(i).getWilling().charAt(1) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								senior2.add(tmpNRef);
							}
							/* Adjacent - South - Central */
							else if(areaList.getSelectedItem().toString().equals("South") && gui.reflist.get(i).getHome().equals("Central") && (gui.reflist.get(i).getWilling().charAt(2) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								senior2.add(tmpNRef);
							}
							/* Not adjacent - North - South */
							else if(areaList.getSelectedItem().toString().equals("North") && gui.reflist.get(i).getHome().equals("South") && (gui.reflist.get(i).getWilling().charAt(0) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								senior3.add(tmpNRef);
							}
							/* Not adjacent - South - North */
							else if(areaList.getSelectedItem().toString().equals("South") && gui.reflist.get(i).getHome().equals("North") && (gui.reflist.get(i).getWilling().charAt(2) == 'Y')) {
								MatchAllo tmpNRef = new MatchAllo(gui.reflist.get(i).getfName(), gui.reflist.get(i).getlName(), gui.reflist.get(i).getAllocation());
								senior3.add(tmpNRef);
							}
						}
					}

					Collections.sort(senior1);
					Collections.sort(senior2);
					Collections.sort(senior3);
					for(MatchAllo s1: senior1)
						suitRef.add(s1);
					for(MatchAllo s2: senior2)
						suitRef.add(s2);
					for(MatchAllo s3: senior3)
						suitRef.add(s3);
					
					if(suitRef.size() >= 2) {
						/* Keep Result */
						String r1 = weekList.getSelectedItem().toString();
						String r2 = levelList.getSelectedItem().toString();
						String r3 = areaList.getSelectedItem().toString();
						String r4 = suitRef.get(0).getfName() + " " + suitRef.get(0).getlName();
						String r5 = suitRef.get(1).getfName() + " " + suitRef.get(1).getlName();
						System.out.println("Ref1: " + r4);
						System.out.println("Ref2: " + r5);
						ResultMatch resultAllocate = new ResultMatch(r1, r2, r3, r4, r5);
						gui.result.add(resultAllocate);
						
						/* Update allocation */
						for(int num = 0; num < gui.reflist.size(); num++) {
							if(gui.reflist.get(num).getfName().equals(suitRef.get(0).getfName()) && gui.reflist.get(num).getlName().equals(suitRef.get(0).getlName())) {
								int increase = gui.reflist.get(num).getAllocation() + 1;
								gui.reflist.get(num).setAllocation(increase);
							}
							else if(gui.reflist.get(num).getfName().equals(suitRef.get(1).getfName()) && gui.reflist.get(num).getlName().equals(suitRef.get(1).getlName())) {
								int increase = gui.reflist.get(num).getAllocation() + 1;
								gui.reflist.get(num).setAllocation(increase);
							}
						}
						gui.table.setModel(new RefereeTable(gui.reflist));

						/* Show 2 Referees Name */
						allocateWk.setText("Week: " + weekList.getSelectedItem().toString());
						ref1Name.setText(suitRef.get(0).getfName() + " " + suitRef.get(0).getlName());
						ref2Name.setText(suitRef.get(1).getfName() + " " + suitRef.get(1).getlName());
						
						/* Check week */
						weekNo.remove(weekList.getSelectedItem().toString());
						DefaultComboBoxModel model = new DefaultComboBoxModel(weekNo.toArray());
						weekList.setModel(model);
					}
					else
						JOptionPane.showMessageDialog(null, "JavaBall needs 2 referees for each match.", "Error", JOptionPane.ERROR_MESSAGE);
					
					jtable.setModel(new AlloTable(suitRef));
				}
				
			}
		}
		
	}
}
