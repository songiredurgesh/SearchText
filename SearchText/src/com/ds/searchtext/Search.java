package com.ds.searchtext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Search 
{
	static ArrayList<String> fileList = new ArrayList<String>();
	public void listFiles(File directory)
	{
		for(final File fileEntry : directory.listFiles())
		{
			if(fileEntry.isDirectory())
			{
				listFiles(fileEntry);
			}
			else
			{		
					if(fileEntry.getName().contains(".txt"))
					{
						fileList.add(fileEntry.getAbsolutePath());
					//	System.out.println(fileEntry.getAbsolutePath());
					}
				
			}
		}
	}
	
	public static void main(String args[])
	{
		File f1 = new File("D:\\DS");
		Search s1 = new Search();
		s1.listFiles(f1);
		
		Iterator<String> itr = fileList.iterator();
		boolean flag = true;
		while(itr.hasNext())
		{
			String fileName = itr.next();
			File file = new File(fileName);
		
			try {
				
				BufferedReader br = new BufferedReader(new FileReader(file));
				String data;
				String value = "Durgesh";
				int count = 0;
				flag = true;
			//	System.out.println("--------------------"+fileName+"---------------------------");
				while((data= br.readLine())!=null)
				{
					count++;
					if(data.contains(value))
					{
						flag = true;
						System.out.println("Data found in file "+fileName+ " Line Number "+count);
					}
					else
					{
						flag = false;
					}
				
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		if(flag == false)
		{
		System.out.println("Data not found in any file");
		}
		
	}
	
}
