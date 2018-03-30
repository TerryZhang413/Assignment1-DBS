package data;

import java.util.ArrayList;

public class Record {

	private ArrayList<Field> fieldList;
	private int length;
	
	public Record(ArrayList<Field> fieldList)
	{                           
		this.fieldList=fieldList;
		this.length=18; //this space is for index
		for(int i=0;i<fieldList.size();i++)
		{
			this.length+=fieldList.get(i).getLength();  //add length of every field
		}
	}


	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}


	public ArrayList<Field> getFieldList() {
		return fieldList;
	}


	public void setFieldList(ArrayList<Field> fieldList) {
		this.fieldList = fieldList;
	}
	
	public void addField(Field field)
	{
		this.fieldList.add(field);
	}
}
