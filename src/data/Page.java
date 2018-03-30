package data;

import java.util.ArrayList;

public class Page {

	private ArrayList<Record> recordList;
	private int length;
	private int max_length;
	private int rest_length;
	
	public Page(int max_length)
	{
		this.max_length=max_length;
		this.length=4; //this space is to store amount of records
		this.rest_length=max_length-this.length;  //initial the rest space of the page
		recordList=new ArrayList<Record>();
	}


	public void addRecord(Record record)
	{
		this.recordList.add(record);
		this.length+=record.getLength()+2;  // 2 represent the space of index for the record
		this.rest_length-=record.getLength()+2; //compute the rest space of the page
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}



	public ArrayList<Record> getRecordList() {
		return recordList;
	}



	public void setRecordList(ArrayList<Record> recordList) {
		this.recordList = recordList;
	}






	public int getMax_length() {
		return max_length;
	}



	public void setMax_length(int max_length) {
		this.max_length = max_length;
	}



	public int getRest_length() {
		return rest_length;
	}



	public void setRest_length(int rest_length) {
		this.rest_length = rest_length;
	}
	
}
