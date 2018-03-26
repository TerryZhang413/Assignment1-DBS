package data;

import java.util.ArrayList;

public class Page {

	private ArrayList<String> content= new ArrayList<String>();
	private int length;
	
	public Page(ArrayList<String> content, int length)
	{
		this.setContent(content);
		this.setLength(length);
	}



	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}



	public ArrayList<String> getContent() {
		return content;
	}



	public void setContent(ArrayList<String> content) {
		this.content = content;
	}
	
}
