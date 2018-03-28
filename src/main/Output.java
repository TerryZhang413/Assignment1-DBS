package main;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
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
			for(int pageListLocation=0;pageListLocation<pageList.size();pageListLocation++)
			{
				int recordLocation=pageList.get(pageListLocation).getRecordList().size()*shortByte+intByte;
				os.writeInt(pageList.get(pageListLocation).getRecordList().size());
				for(int indexForRecord=0;indexForRecord<pageList.get(pageListLocation).getRecordList().size();indexForRecord++)
				{
					os.writeShort(recordLocation);
					recordLocation+=pageList.get(pageListLocation).getRecordList().get(indexForRecord).getLength();
				}
				
				for(int recordListLocation=0;recordListLocation<pageList.get(pageListLocation).getRecordList().size();recordListLocation++)
				{
					int fieldLocation=pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().size()*shortByte;
					for(int indexForField=0;indexForField<pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().size();indexForField++)
					{
						os.writeShort(fieldLocation);
						fieldLocation+=pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(indexForField).getLength();
					}
					for(int fieldListLocation=0;fieldListLocation<8;fieldListLocation++)
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
/*		 */
	}
}
