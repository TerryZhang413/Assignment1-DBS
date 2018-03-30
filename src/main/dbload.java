package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import data.Field;
import data.Page;
import data.Record;

public class dbload {
		
	public static void main(String[] args) {
		
		long startTime=System.currentTimeMillis();
		long endTime;
		int  recordNumber=0;
		int pageSize=Integer.valueOf(args[1]); //page size
		String fileAddress=args[2];	          //file address
		String outputAddress="heap."+pageSize;
		
		Page page=new Page(pageSize);
		ArrayList<Page> pageList= new ArrayList<Page>();

			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileAddress));
				reader.readLine(); //ignore first line(title)
				String line = "";
				
			while((line = reader.readLine())!=null)
				{ 
			      	String[] item = line.split("\t");//split the csv file by tab
					if (item.length<9) //if columns is not 9,delete this useless line 
						{continue;}
					ArrayList<Field> field = new ArrayList<Field>();
					field.add(new Field(item[0],"String",item[0].getBytes("utf-8").length));
					field.add(new Field(item[1],"String",item[1].getBytes("utf-8").length));
					field.add(new Field(item[2],"String",item[2].getBytes("utf-8").length));
					field.add(new Field(item[3],"String",item[3].getBytes("utf-8").length));
					field.add(new Field(item[4],"String",item[4].getBytes("utf-8").length));
					field.add(new Field(item[5],"String",item[5].getBytes("utf-8").length));
					field.add(new Field(item[6],"String",item[6].getBytes("utf-8").length));
					field.add(new Field(item[7],"String",item[7].getBytes("utf-8").length));
					field.add(new Field(Long.valueOf(item[8]),"Long",8));	//input data into fields
	
					Record record=new Record(field);
					
					// if there is enough space to store next record
					if(page.getRest_length()>=(record.getLength()+2)) 
					{
						page.addRecord(record); //add a new record to page
					}
					else // if not 
					{
						pageList.add(page);  //push this page to page list
						page=new Page(pageSize); //create a new page
						page.addRecord(record);  //store the last record in the new page
					}
					//if (pageList.size()>0) //control pages 
						//break;
				//	recordNumber++; 
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
			pageList.add(page);

			Output output=new Output(pageList,outputAddress);
			output.output();
			
			
			//System.out.println("Record added number is " +recordNumber);
			
			//recordNumber=0;
			for(int i=0;i<pageList.size();i++)
			{
				for(int j=0;j<pageList.get(i).getRecordList().size();j++)
				{
					//System.out.println(pageList.get(i).getRecordList().get(j).getFieldList().get(1).getContentString());
					recordNumber++;
				}
			} 
			
			System.out.println("Number of records is: " +recordNumber);
			
			System.out.println("Number of pages is: "+pageList.size());
			
			endTime=System.currentTimeMillis();
			System.out.println("Number of milliseconds is: "+ (endTime-startTime)+ "ms");
			
			
	/*		for(int i=0;i<pageList.size();i++)  //print every record line by line
			{
				System.out.println("Page "+i+":   "+"First record length:"+pageList.get(i).getRecordList().get(0).getLength());
				for(int j=0;j<pageList.get(i).getRecordList().size();j++)
				{
					System.out.println("Record "+j+":");
					for(int h=0;h<8;h++)
					{
						System.out.print(pageList.get(i).getRecordList().get(j).getFieldList().get(h).getContentString()+" ");
					}
					System.out.println(pageList.get(i).getRecordList().get(j).getFieldList().get(8).getContentLong());
				}
			}*/
	} 
	
}



