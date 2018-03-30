package main;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import data.Page;

public class Output {

	private ArrayList<Page> pageList=new ArrayList<Page>();
	final private int shortByte=2;
	final private int intByte=4;
	private String outputAddress;
	
	public Output(ArrayList<Page> pageList,String outputAddress)
	{
		this.pageList = pageList;
		this.outputAddress=outputAddress;
	}
	
	public static byte[] longtobyteArray(long longNum) {  
		byte[] byteRet = new byte[8];  
		for (int i = 0; i < 8; i++) {  
			int offset = (byteRet.length - 1 - i) * 8; 
			byteRet[i] = (byte) ((longNum >>> offset) & 0xff);
        }  
        return byteRet;  
    }  
	
	public void output()
	{
		try {
			DataOutputStream os = new DataOutputStream(new FileOutputStream(outputAddress));
			for(int pageListLocation=0;pageListLocation<pageList.size();pageListLocation++)//loop pageList
			{
				int recordLocation=pageList.get(pageListLocation).getRecordList().size()*shortByte+intByte; //define location of first record
				os.writeInt(pageList.get(pageListLocation).getRecordList().size());//output number of records in the page
				//System.out.print(pageList.get(pageListLocation).getRecordList().size() +" ");
				
				//output index
				for(int indexForRecord=0;indexForRecord<pageList.get(pageListLocation).getRecordList().size();indexForRecord++)
				{
					//System.out.print(recordLocation +" ");
					os.writeShort(recordLocation);
					recordLocation+=pageList.get(pageListLocation).getRecordList().get(indexForRecord).getLength();
				}
				//System.out.println();
				
				//output index and content of the records
				for(int recordListLocation=0;recordListLocation<pageList.get(pageListLocation).getRecordList().size();recordListLocation++)//loop recordList
				{
					//System.out.print(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().size());
					//os.writeInt(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().size());
					int fieldLocation=pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().size()*shortByte;//define location of first field
					for(int indexForField=0;indexForField<pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().size();indexForField++)
					{
						//System.out.print(fieldLocation +" ");
						os.writeShort(fieldLocation);
						fieldLocation+=pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(indexForField).getLength();
					}
					for(int fieldListLocation=0;fieldListLocation<8;fieldListLocation++) //output fields
					{
						//System.out.print(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(fieldListLocation).getContentString() +" ");
						os.writeBytes(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(fieldListLocation).getContentString());
					}
					//System.out.println(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(8).getContentLong());
					os.writeLong(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(8).getContentLong());
				}
				
				for(int blankArea=0;blankArea<pageList.get(pageListLocation).getRest_length();blankArea++)
				{
					os.writeBytes(" ");
					//System.out.print(" ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
/*		 */
	}
}
