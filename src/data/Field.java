package data;

public class Field {

	
	private String contentString;
	private Double contentDouble;
	private String attribute;
	private int length;
	
	public Field(String content, String attribute, int length)
	{
		this.setContent(content);
		this.setAttribute(attribute);
		this.setLength(length);		
	}

	public Field(Double contentDouble, String attribute, int length)
	{
		this.setContentDouble(contentDouble);
		this.setAttribute(attribute);
		this.setLength(length);		
	}
	


	public Field() {
		// TODO 自动生成的构造函数存根
	}



	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getContentString() {
		return contentString;
	}

	public void setContent(String contentString) {
		this.contentString = contentString;
	}

	public Double getContentDouble() {
		return contentDouble;
	}

	public void setContentDouble(Double contentDouble) {
		this.contentDouble = contentDouble;
	}
	
}
