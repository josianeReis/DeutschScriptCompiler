package lexicalAnalyzer;

public class Token {
	
	private String image;
	private String classId;
	private Integer id;
	private Integer line;
	private Integer column;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLine() {
		return line;
	}
	public void setLine(Integer line) {
		this.line = line;
	}
	public Integer getColumn() {
		return column;
	}
	public void setColumn(Integer column) {
		this.column = column;
	}
	
	@Override
	public String toString() {
		return "Image: " + this.image + ", Classe: " + this.classId +
			   ", Id: " + this.id + ", Line: " + this.line +
			   ", Column: " + this.column;
	}
}