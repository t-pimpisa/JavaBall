import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Gui extends JFrame{
	
	private AddGui addgui;
	private DeleteGui deletegui;
	private MatchGui matchgui;
	private UpdateGui updategui;

	private Scanner x;
	
	private JScrollPane refscroll;
	private JPanel panel;
	
	private JPanel addPane;
	private JPanel updatePane;
	private JPanel delPane;
	private JPanel matchPane;
	private JPanel filePane;
	
	private JButton addButton;
	private JButton updateButton;
	private JButton delButton;
	private JButton matchButton;
	private JButton fileButton;
	private JButton exitButton;
	
	private JLabel addLabel;
	private JLabel delLabel;
	private JLabel updateLabel;
	private JLabel matchLabel;
	private JLabel fileLabel;
	
	public ArrayList<Referee> reflist = new ArrayList<Referee>();
	public DefaultTableModel tableModel = new DefaultTableModel();
	public ArrayList<ResultMatch> result = new ArrayList<ResultMatch>();
	public JTable table;
	
	public Gui() {
		super("JavaBall Match");
		openFile();
		readFile(reflist);
		closeFile();
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		createTable();		
		panel.add(refscroll);
		
		addPanel();
		panel.add(addPane);
		
		updatePanel();
		panel.add(updatePane);
		
		deletePanel();
		panel.add(delPane);
		
		matchPanel();
		panel.add(matchPane);
		
		filePane = new JPanel();
		fileLabel = new JLabel("Allocation Detail");
		fileButton = new JButton("Create File");
		filePane.add(fileLabel);
		filePane.add(fileButton);		
		fileButton.addActionListener(new Handler(this));
		panel.add(filePane);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new Handler(this));
		panel.add(exitButton);
		
		add(panel);
		
	}
	
	public void openFile() {
		try {
			x = new Scanner(new File("RefereesIn.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("could not find this file");
		}
	}

	public void readFile(ArrayList<Referee> reflist) {
		while(x.hasNext()) {
			reflist.add(new Referee(x.next(), x.next(), x.next(), x.next(), Integer.parseInt(x.next()), x.next(), x.next()));
		}
	}
	
	public void closeFile() {
		x.close();
	}
	
	public void createTable() {
		
		String[] columnName = {"Referee ID", "FirstName", "LastName", "Qualification", "Number of allocation", "Home Area", "Willing Area"};
		tableModel = new DefaultTableModel(columnName, 0);
		table = new JTable(tableModel);
		
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
		
		refscroll = new JScrollPane(table);
		refscroll.setPreferredSize(new Dimension(500, 200));	
	}
	
	public void addPanel() {

		addPane = new JPanel();
		
		addLabel = new JLabel("Add Referee Here");
		addPane.add(addLabel);	
		
		addButton = new JButton("Add Referee");
		addPane.add(addButton);
		
		addButton.addActionListener(new Handler(this));
	}
	
	public void updatePanel() {

		updatePane = new JPanel();
		
		updateLabel = new JLabel("Update Detail Here");
		updatePane.add(updateLabel);
		
		updateButton = new JButton("Update");
		updatePane.add(updateButton);
		
		updateButton.addActionListener(new Handler(this));
	}
	
	public void deletePanel() {
		
		delPane = new JPanel();
		
		delLabel = new JLabel("Delete Referee Here");
		delPane.add(delLabel);
		
		delButton = new JButton("Delete Referee");	
		delPane.add(delButton);
		
		delButton.addActionListener(new Handler(this));
	}
	
	public void matchPanel() {
		
		matchPane = new JPanel();
		
		matchLabel = new JLabel("Allocate Referee Here");
		matchPane.add(matchLabel);
		
		matchButton = new JButton("Start Matching");	
		matchPane.add(matchButton);
		
		matchButton.addActionListener(new Handler(this));
	}
	
	public class Handler implements ActionListener{
		private Gui gui;
		
		public Handler(Gui gui) {
			this.gui = gui;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == delButton) {
				deletegui = new DeleteGui(this.gui);
				deletegui.setLocation(300, 200);
				deletegui.setSize(300, 150);
				deletegui.setVisible(true);
				System.out.println("Open Delete Gui!!!");
			}
			else if(e.getSource() == addButton) {
				addgui = new AddGui(this.gui);
				addgui.setLocation(300, 200);
				addgui.setSize(320, 250);
				addgui.setVisible(true);
				System.out.println("Open Add Gui!!!");
			}
			
			else if(e.getSource() == updateButton) {
				updategui = new UpdateGui(this.gui);
				updategui.setLocation(300, 200);
				updategui.setSize(300, 250);
				updategui.setVisible(true);
				System.out.println("Open Update Gui");
			}
			
			else if(e.getSource() == matchButton) {
				matchgui = new MatchGui(this.gui);
				System.out.println("Open Match Gui");
				matchgui.setLocation(300, 200);
				matchgui.setSize(300, 300);
				matchgui.setVisible(true);
			}
			else if(e.getSource() == fileButton) {
				System.out.println("Creating MatchAllocs.txt");
				try {
					Formatter xFile = new Formatter("MatchAllocs.txt");
					xFile.format("%s  %s  %s  %s  %s\n", "Week_Number", "Level", "Area", "First_Referee", "Second_Referee");
					for(int count = 0; count < result.size(); count++) {
						xFile.format("%s  %s  %s  %s  %s\n", result.get(count).getWeekNo(), result.get(count).getLevel(), result.get(count).getArea(), result.get(count).getRef1Name(), result.get(count).getRef2Name());
					}
					xFile.close();
					System.out.println("MatchAllocs.txt successful");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("Can not create file MatchAllocs.txt");
				}
			}
			else if(e.getSource() == exitButton) {
				System.out.println("Creating RefereesOut.txt");
				try {
					Formatter finalFile = new Formatter("RefereesOut.txt");
//					finalFile.format("%s  %s  %s  %s  %s  %s  %s\n", "Referee_ID", "First_Name", "Last_Name", "Qualification", "Number_of_Allocation", "Home_Area", "Willing_Area");
					for(int count = 0; count < reflist.size(); count++) {
						finalFile.format("%s %s %s %s %s %s %s\n", reflist.get(count).getRefID(), reflist.get(count).getfName(), reflist.get(count).getlName(), reflist.get(count).getQuali(), reflist.get(count).getAllocation(), reflist.get(count).getHome(), reflist.get(count).getWilling());
					}
					finalFile.close();
					Formatter xFile = new Formatter("MatchAllocs.txt");
					xFile.format("%s  %s  %s  %s  %s\n", "Week_Number", "Level", "Area", "First_Referee", "Second_Referee");
					for(int count = 0; count < result.size(); count++) {
						xFile.format("%s  %s  %s  %s  %s\n", result.get(count).getWeekNo(), result.get(count).getLevel(), result.get(count).getArea(), result.get(count).getRef1Name(), result.get(count).getRef2Name());
					}
					xFile.close();
					System.out.println("RefereesOut.txt successful");
					System.exit(0);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("Can not create file RefereesOut.txt");
				}
			}
		}
		
	}
	
	
}
