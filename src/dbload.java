import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class dbload {
		
	public static void main(String[] args) {
		
		long startTime=System.currentTimeMillis();
		long endTime;
		int  recordNumber=0;
		int pageAmount=0;
		int pageSize=Integer.valueOf(args[1]); //page size

		String fileAddress=args[2];	          //file address
		String outputAddress="heap."+pageSize;
		
		
		BufferedReader reader = null;
		Page page=new Page(pageSize);


			try {
				reader = new BufferedReader(new FileReader(fileAddress));
				DataOutputStream os = new DataOutputStream(new FileOutputStream(outputAddress));
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
							output(page,os);  //push the page to heap file
							pageAmount++;
							page=new Page(pageSize); //create a new page
							page.addRecord(record);  //store the last record in the new page
						}
						//if (pageList.size()>0) //control pages 
							//break;
						
						recordNumber++; 					
						}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			//System.out.println("Record added number is " +recordNumber);
			
			//recordNumber=0;
			
			System.out.println("Number of records is: " +recordNumber);
			
			System.out.println("Number of pages is: "+pageAmount);
			
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

	
	public static void output(Page page,DataOutputStream os)
	{
		final int shortByte=2;
		final int intByte=4;
		try {			
				int recordLocation=page.getRecordList().size()*shortByte+intByte; //define location of first record
				os.writeInt(page.getRecordList().size());//output number of records in the page
				//System.out.print(pageList.get(pageListLocation).getRecordList().size() +" ");
				
				//output index
				for(int indexForRecord=0;indexForRecord<page.getRecordList().size();indexForRecord++)
				{
					//System.out.print(recordLocation +" ");
					os.writeShort(recordLocation);
					recordLocation+=page.getRecordList().get(indexForRecord).getLength();
				}
				//System.out.println();
				
				//output index and content of the records
				for(int recordListLocation=0;recordListLocation<page.getRecordList().size();recordListLocation++)//loop recordList
				{
					//System.out.print(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().size());
					//os.writeInt(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().size());
					int fieldLocation=page.getRecordList().get(recordListLocation).getFieldList().size()*shortByte;//define location of first field
					for(int indexForField=0;indexForField<page.getRecordList().get(recordListLocation).getFieldList().size();indexForField++)
					{
						//System.out.print(fieldLocation +" ");
						os.writeShort(fieldLocation);
						fieldLocation+=page.getRecordList().get(recordListLocation).getFieldList().get(indexForField).getLength();
					}
					for(int fieldListLocation=0;fieldListLocation<8;fieldListLocation++) //output fields
					{
						//System.out.print(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(fieldListLocation).getContentString() +" ");
						os.writeBytes(page.getRecordList().get(recordListLocation).getFieldList().get(fieldListLocation).getContentString());
					}
					//System.out.println(pageList.get(pageListLocation).getRecordList().get(recordListLocation).getFieldList().get(8).getContentLong());
					os.writeLong(page.getRecordList().get(recordListLocation).getFieldList().get(8).getContentLong());
				}
				
				for(int blankArea=0;blankArea<page.getRest_length();blankArea++)
				{
					os.writeBytes(" ");
					//System.out.print(" ");
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

	




