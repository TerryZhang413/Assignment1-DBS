package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Input {
	
	private String address;
	private int pageSize;
	private int pageIndex;
	private FileInputStream fileInputStream;
	
	public Input(String address, int pageSize)
	{
		this.address = address;
		this.pageSize = pageSize;
		
		pageIndex = 0;
		File file;
				
		try {
			file = new File(address);
			fileInputStream = new FileInputStream(file);  //get the heap file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	//get new page
	public byte[] readNextPage()
	{
		byte[] page = new byte[pageSize];
		try {
			if(fileInputStream.read(page, pageIndex, pageSize)!=-1) //read new 4096 bytes content
				return page;
			else
				return null; //if it is empty, return null
		} catch (IOException e) {
			return null;
		}
	}

}
