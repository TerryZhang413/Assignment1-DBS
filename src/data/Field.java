package data;

public class Field {

	
	private String contentString;
	private Long contentLong;
	private String attribute;
	private int length;
	
	public Field(String content, String attribute, int length)
	{
		this.setContent(content);
		this.setAttribute(attribute);
		this.setLength(length);		
	}

	public Field(Long contentLong, String attribute, int length)
	{
		this.setContentLong(contentLong);
		this.setAttribute(attribute);
		this.setLength(length);		
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

	public Long getContentLong() {
		return contentLong;
	}

	public void setContentLong(Long contentLong) {
		this.contentLong = contentLong;
	}
	
}
