package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import data.Field;
import data.Page;
import data.Record;

public class dbload {
		
	public static void main(String[] args) {
		

		int pageSize=Integer.valueOf(args[1]);
		String fileAddress=args[2];
		
		Page page=new Page(pageSize);
		ArrayList<Page> pageList= new ArrayList<Page>();

			try {
				BufferedReader reader = new BufferedReader(new FileReader(fileAddress));
				reader.readLine();
				String line = "";
				
			while((line = reader.readLine())!=null)
				{ 
			      	String item[] = line.split("\t");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
			        //System.out.println(item.length);
					if (item.length<9)
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
					field.add(new Field(Double.valueOf(item[8]),"Double",item[8].getBytes("utf-8").length));	
	
					Record record=new Record(field);
					
					
					if(page.getRest_length()>=(record.getLength()+2))
					{
						page.addRecord(record);
					}
					else
					{
						pageList.add(page);
						page=new Page(pageSize); 
						page.addRecord(record);
					}
					if (pageList.size()>50)
						break;
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			pageList.add(page);

			Output output=new Output(pageList);
			output.output();
			
			System.out.println("page size:"+pageList.size());
			
			for(int i=0;i<pageList.size();i++)
			{
				System.out.println("Page "+i+":   "+"First record length:"+pageList.get(i).getRecordList().get(0).getLength());
				for(int j=0;j<pageList.get(i).getRecordList().size();j++)
				{
					System.out.println("Record "+j+":");
					for(int h=0;h<8;h++)
					{
						System.out.print(pageList.get(i).getRecordList().get(j).getFieldList().get(h).getContentString()+" ");
					}
					System.out.println(pageList.get(i).getRecordList().get(j).getFieldList().get(8).getContentDouble());
				}
			}
			//System.out.println(pageList.get(0).getRecordList().get(0).getFieldList().get(0).getAttribute());

	}
	
}



