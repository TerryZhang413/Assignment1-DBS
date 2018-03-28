package main;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import data.Page;

public class Output {

	private ArrayList<Page> pageList=new ArrayList<Page>();
	final private int shortByte=2;
	final private int intByte=4;
	
	public Output(ArrayList<Page> pageList)
	{
		this.pageList = pageList;
	}
	
	public void output()
	{
		try {
			DataOutputStream os = new DataOutputStream(new FileOutputStream("binout.dat"));
			for(int pageListLocation=0;pageListLocation<pageList.size();pageListLocation++)//loop pageList
			{
				int recordLocation=pageList.get(pageListLocation).getRecordList().size()*shortByte+intByte; //define location of first record
				os.writeInt(pageList.get(pageListLocation).getRecordList().size());//output number of records in the page
				
				//output index
				for(int indexForRecord=0;indexForRecord<pageList.get(pageListLocation).getRecordList().size();indexForRecord++)
				{
					os.writeShort(recordLocation);
					recordLocation+=pageList.get(pageListLocation).getRecordList().get(indexForRecord).getLength();
				}
				
				for(int recordListLocation=0;recordListLocation<pageList.get(pageListLocation).getRecordList().size();recordListLocation++)//loop recordList
				{
					int fieldLocation=pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().size()*shortByte;//define location of first field
					for(int indexForField=0;indexForField<pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().size();indexForField++)
					{
						os.writeShort(fieldLocation);
						fieldLocation+=pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(indexForField).getLength();
					}
					for(int fieldListLocation=0;fieldListLocation<8;fieldListLocation++) //output fields
					{
						os.writeBytes(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(fieldListLocation).getContentString());
					}
					os.writeDouble(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(8).getContentDouble());
				}
				
				for(int blankArea=0;blankArea<pageList.get(pageListLocation).getRest_length();blankArea++)
				{
					os.writeBytes(" ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
/*		 */
	}
}
