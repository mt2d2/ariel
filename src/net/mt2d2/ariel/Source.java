package net.mt2d2.ariel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Source
{
	private String	source;

	public Source(String path)
	{
		try
		{
			this.source = readFile(path);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static String readFile(String filePath) throws java.io.IOException
	{
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1)
		{
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		
		return fileData.toString();
	}

	@Override
	public String toString()
	{
		return this.source;
	}
}
