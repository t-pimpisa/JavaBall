
public class ResultMatch {
	
	private String weekNo;
	private String level;
	private String area;
	private String ref1Name;
	private String ref2Name;
	
	public ResultMatch(String weekNo, String level, String area, String ref1Name, String ref2Name) {
		this.weekNo = weekNo;
		this.level = level;
		this.area = area;
		this.ref1Name = ref1Name;
		this.ref2Name = ref2Name;
	}

	public String getWeekNo() {
		return weekNo;
	}

	public void setWeekNo(String weekNo) {
		this.weekNo = weekNo;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRef1Name() {
		return ref1Name;
	}

	public void setRef1Name(String ref1Name) {
		this.ref1Name = ref1Name;
	}

	public String getRef2Name() {
		return ref2Name;
	}

	public void setRef2Name(String ref2Name) {
		this.ref2Name = ref2Name;
	}
}
