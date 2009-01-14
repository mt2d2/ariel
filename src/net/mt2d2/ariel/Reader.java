package net.mt2d2.ariel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader
{
	private String	source;

	public Reader(String filePath)
	{
		try
		{
			this.source = readFileAsString(filePath);
		}
		catch (IOException e)
		{
			System.out.println("Error reading file: " + filePath);
			e.printStackTrace();
		}
	}
	
	public Program parseProgram()
	{
		Program p = new Program();
		String[] tokens = this.source.split("\n| ");
		
		for (int i = 0; i < tokens.length; i++)
		{
			if (tokens[i].equalsIgnoreCase(Opcodes.PUSH.name()))
			{
				p.addInstruction(Opcodes.PUSH, Double.parseDouble(tokens[++i]));
			}
			else if (tokens[i].equalsIgnoreCase(Opcodes.POP.name()))
			{
				p.addInstruction(Opcodes.POP);
			}
			else if (tokens[i].equalsIgnoreCase(Opcodes.STORE.name()))
			{
				p.addInstruction(Opcodes.STORE, tokens[++i]);
			}
			else if (tokens[i].equalsIgnoreCase(Opcodes.LOAD.name()))
			{
				p.addInstruction(Opcodes.LOAD, tokens[++i]);
			}
			else if (tokens[i].equalsIgnoreCase(Opcodes.PRINT.name()))
			{
				p.addInstruction(Opcodes.PRINT);
			}
			else if (tokens[i].equalsIgnoreCase(Opcodes.ADD.name()))
			{
				p.addInstruction(Opcodes.ADD);
			}
			else if (tokens[i].equalsIgnoreCase(Opcodes.SUBTRACT.name()))
			{
				p.addInstruction(Opcodes.SUBTRACT);
			}
			else if (tokens[i].equalsIgnoreCase(Opcodes.MULTIPLY.name()))
			{
				p.addInstruction(Opcodes.MULTIPLY);
			}
			else if (tokens[i].equalsIgnoreCase(Opcodes.DIVIDE.name()))
			{
				p.addInstruction(Opcodes.DIVIDE);
			}
		}
		
		return p;
	}

	private static String readFileAsString(String filePath) throws IOException
	{
		final StringBuilder fileData = new StringBuilder(1000);
		final BufferedReader reader = new BufferedReader(new FileReader(filePath));
		
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
}
