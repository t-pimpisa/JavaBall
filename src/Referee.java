
public class Referee implements Comparable<Referee>{
	
	private String refID;
	private String fName;
	private String lName;
	private String quali;
	private int allocation;
	private String home;
	private String willing;
	
	public Referee() {
		
	}
	
	public Referee(String refID, String fName, String lName, String quali, int allocation, String home, String willing) {
		this.refID = refID;
		this.fName = fName;
		this.lName = lName;
		this.quali= quali;
		this.allocation = allocation;
		this.home = home;
		this.willing = willing;
	}

	public String getRefID() {
		return refID;
	}

	public void setRefID(String refID) {
		this.refID = refID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getQuali() {
		return quali;
	}

	public void setQuali(String quali) {
		this.quali = quali;
	}

	public int getAllocation() {
		return allocation;
	}

	public void setAllocation(int allocation) {
		this.allocation = allocation;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getWilling() {
		return willing;
	}

	public void setWilling(String willing) {
		this.willing = willing;
	}

	@Override
	public int compareTo(Referee o) {
		// TODO Auto-generated method stub
		return this.refID.compareTo(o.getRefID());
	}
}
