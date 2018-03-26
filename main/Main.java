package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import data.Field;
import data.Page;
import data.Record;

public class Main {
		
	public static void main(String[] args) {
		
		ArrayList<String> list= new ArrayList<String>();
		int length=0;
		
		Page page=new Page(list,length);
		

			try {
				BufferedReader reader = new BufferedReader(new FileReader("123.txt"));
				//reader.readLine();
				String line = "";
				
				int count=0;
			while((line = reader.readLine())!=null)
				{ 
		      	String item[] = line.split("\t");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
		        //System.out.println(item.length);
				Field field[]=new Field[9];
				field[0]= new Field(item[0],"String",item[0].getBytes("utf-8").length);
				field[1]= new Field(item[1],"String",item[1].getBytes("utf-8").length);
				field[2]= new Field(item[2],"String",item[2].getBytes("utf-8").length);
				field[3]= new Field(item[3],"String",item[3].getBytes("utf-8").length);
				field[4]= new Field(item[4],"String",item[4].getBytes("utf-8").length);
				field[5]= new Field(item[5],"String",item[5].getBytes("utf-8").length);
				field[6]= new Field(Double.valueOf(item[6]),"Double",item[6].getBytes("utf-8").length);
				field[7]= new Field(item[7],"String",item[7].getBytes("utf-8").length);
				field[8]= new Field(Double.valueOf(item[8]),"Double",item[8].getBytes("utf-8").length);	
				for(int i=0;i<9;i++)
				{
					count+=field[i].getLength()+1;
				}
				Record record=new Record(field[0].getContentString()+','+field[1].getContentString()+','+
						field[2].getContentString()+','+field[3].getContentString()+','+field[4].getContentString()+','+
						field[5].getContentString()+','+field[6].getContentDouble()+','+field[7].getContentString()+','+
						field[8].getContentDouble(),count-1);
				length+=record.getLength();
				page.setLength(length);
				if (page.getLength()>600)
					break;
				list.add(record.getContent());
				page.setContent(list);

				

	/*			if(count<202)s
				{
					for(int i=0;i<9;i++)
					{
						if(field[i].getAttribute()=="String")
							System.out.print(field[i].getContentString()+",");
						else
							System.out.print(field[i].getContentDouble()+",");
					}
					continue;
				}
				else
					break;*/
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		for(int i=0;i<page.getContent().size();i++)
		{
		System.out.println(page.getContent().get(i));
		}
	}
	
}



