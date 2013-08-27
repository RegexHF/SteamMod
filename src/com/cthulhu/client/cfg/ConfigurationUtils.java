package com.cthulhu.client.cfg;

import java.io.*;
import java.util.HashMap;

public class ConfigurationUtils 
{

	public ConfigurationUtils()
	{
		
	}
	
	public void mkfolder(String fileName)
	{
		try
		{
			File file = new File(fileName);
			file.mkdir();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void mkfile(String fileName)
	{
		try
		{
			File file = new File(fileName);
			if(!file.exists())file.createNewFile();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void readFile(String fileName, String split, ConfigurationModule cm, HashMap<String, String> hashMap)
	{
		try
		{
			File file = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = br.readLine()) != null)
			{
				if(line.startsWith(cm.getKeyWord()))
				{
					String args[] = line.split(split);
					hashMap.put(args[0], args[1]);
				}
			}
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
