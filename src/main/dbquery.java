package main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import data.Record;

public class dbquery {

	public static void main(String[] args) {
		
		String queryKeyWord=args[0];  //get the key words
		int pageSize=Integer.valueOf(args[1]); //get the pagesize in order to direct at the target heap file
		System.out.println("Key words searched is: "+queryKeyWord);
		byte[] pageContent;
		int recordNumber;
		int judgement=0;
		
		Input input= new Input("heap."+pageSize,pageSize); //get the stream from heap file
		
		dbquery dbquery=new dbquery();
		
		while(true)
		{
			pageContent= input.readNextPage(); //get next page 
			
			if(pageContent != null) //if the page is not empty 
			{
				recordNumber=dbquery.getRecordNumber(pageContent); //get how many records in this page
				
				ArrayList<byte[]> recordList=dbquery.getRecord(recordNumber, pageContent);  //push records in a arraylist
				for(int i=0;i<recordList.size();i++)
				{
					//System.out.println("number:"+i);
					if (dbquery.getBN_NAME(recordList.get(i)).contains(queryKeyWord))  //check the keyword
					{
						System.out.println("This BN_NAME is founded as "+dbquery.getBN_NAME(recordList.get(i))); 
						System.out.println("All information is below:");
						System.out.println(dbquery.getOtherInfo(recordList.get(i)));
						System.out.println();
						judgement++;
					}
				}
			}
			else
				break;
		}
		System.out.println("All pages have been viewed!");
		if(judgement==0)
		System.out.println("There is no record matched!");
	}
	
	//get size of page
	public int getRecordNumber(byte[] pageContent)
	{
		byte[] pageSizeByte = new byte[4];		
		System.arraycopy(pageContent,0,pageSizeByte,0,4);
		int recordNumber=byteArrayToInt(pageSizeByte);
		//System.out.println(recordNumber);
		return recordNumber;
	}
	
	//get records in a page
	public ArrayList<byte[]> getRecord(int recordNumber,byte[] pageContent)
	{
		ArrayList<byte[]> recordList = new ArrayList<byte[]>();
		
		int recordLength;
		byte[] startLocation = new byte[2];
		byte[] endLocation = new byte[2];
	    int startRecordLocation;
	    int endRecordLocation = 0;
	    
		//get record by using index
		for(int i=0;i<recordNumber-1;i++)  
		{
			//System.out.println(i+" :");
			System.arraycopy(pageContent,4+i*2,startLocation,0,2);
			System.arraycopy(pageContent,4+(i+1)*2,endLocation,0,2);
			startRecordLocation=byteArrayToShort(startLocation);
			//System.out.println(startRecordLocation);			
			endRecordLocation=byteArrayToShort(endLocation);
			//System.out.println(endRecordLocation);
			recordLength=endRecordLocation-startRecordLocation;
			byte[] record = new byte[recordLength];
			System.arraycopy(pageContent,startRecordLocation,record,0,recordLength);
			recordList.add(record);
		}
		
		byte[] record = new byte[4096-endRecordLocation];
		System.arraycopy(pageContent,endRecordLocation,record,0,4096-endRecordLocation);   //get last record
		recordList.add(record);
		
		return recordList;
	}
	
	//get BN_NAME in a record for checking
	public String getBN_NAME(byte[] record)
	{
		int startFieldLocation;
		int endFieldLocation;
		int fieldLength;
		byte[] startLocation = new byte[2];
		byte[] endLocation = new byte[2];
		
		for(int i=0;i<9;i++)
		{
			System.arraycopy(record,2*i,startLocation,0,2);
			startFieldLocation=byteArrayToShort(startLocation);
			//System.out.println(startFieldLocation);
		}
		
	
		System.arraycopy(record,2,startLocation,0,2);
		startFieldLocation=byteArrayToShort(startLocation);
		//System.out.println(startFieldLocation);
		
		System.arraycopy(record,4,endLocation,0,2);
		endFieldLocation=byteArrayToShort(endLocation);
		//System.out.println(endFieldLocation);
		
		fieldLength=endFieldLocation-startFieldLocation;
		//System.out.println(fieldLength);
		byte[] field = new byte[fieldLength];
		System.arraycopy(record,startFieldLocation,field,0,fieldLength);
		String fieldContent=new String(field);
		//System.out.println(fieldContent);
		return fieldContent;
	}
	
	
	//print all information in a record
	public String getOtherInfo(byte[] record)
	{
		int startFieldLocation=18;
		int endFieldLocation = 0;
		int fieldLength;
		byte[] startLocation = new byte[2];
		byte[] endLocation = new byte[2];
		String info="";
		
		for(int i=0;i<8;i++)
		{
			//System.out.println("loop: "+i*2);
			System.arraycopy(record,i*2,startLocation,0,2);
			startFieldLocation=byteArrayToShort(startLocation);	
			//System.out.println("start "+startFieldLocation);
			
			System.arraycopy(record,(i+1)*2,endLocation,0,2);
			endFieldLocation=byteArrayToShort(endLocation);
			//System.out.println("end " +endFieldLocation);
			
			fieldLength=endFieldLocation-startFieldLocation;
			
			//System.out.println("field endloaction is "+endFieldLocation);

			byte[] field = new byte[fieldLength];
			System.arraycopy(record,startFieldLocation,field,0,fieldLength);
			info+= new String(field)+" ";
		}
		
		byte[] LongContent=new byte[8];
		System.arraycopy(record,endFieldLocation,LongContent,0,8);
		
		
		return info+byteArrayToLong(LongContent);
	}
	
	//convert byte[] to int
	public static int byteArrayToInt(byte[] b) {   
		return   b[3] & 0xFF |   
		            (b[2] & 0xFF) << 8 |   
		            (b[1] & 0xFF) << 16 |   
		            (b[0] & 0xFF) << 24;   
		}  
	
	//convert byte[] to short
	public static int byteArrayToShort(byte[] b) {   
		return   b[1] & 0xFF |   
		            (b[0] & 0xFF) << 8; 
		} 
	
	
	
	//convert byte[] to long
    public long byteArrayToLong(byte[] b) { 
        long result = 0; 
        for (int i = 0; i < 8; i++) { 
            result <<= 8; 
            result |= (b[i] & 0xff); 
        } 
        return result; 
    } 
}
