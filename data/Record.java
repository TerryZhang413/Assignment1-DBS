package data;

public class Record {

	private String content;
	private int length;
	
	public Record(String content, int length)
	{
		this.setContent(content);
		this.setLength(length);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	
}
