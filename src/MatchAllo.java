
public class MatchAllo implements Comparable<MatchAllo>{

	private String fName;
	private String lName;
	private int allo;
	
	public MatchAllo(String fName, String lName, int allo) {
		this.fName = fName;
		this.lName = lName;
		this.allo = allo;
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

	public int getAllo() {
		return allo;
	}

	public void setAllo(int allo) {
		this.allo = allo;
	}

	@Override
	public int compareTo(MatchAllo o) {
		// TODO Auto-generated method stub
		return this.allo - o.getAllo();
	}

}
