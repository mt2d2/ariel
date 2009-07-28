package net.mt2d2.ariel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Source
{
	private static Pattern WHITESPACE_REGEX = Pattern.compile("^\\s*$");
	private static Pattern COMMENT_REGEX = Pattern.compile("^\\s*//.*");
	private static Pattern LBL_REGEX = Pattern.compile("^\\s*lbl\\s(\\w+)", Pattern.CASE_INSENSITIVE);
	private static Pattern DEF_REGEX = Pattern.compile("^\\s*def\\s+(\\w+)", Pattern.CASE_INSENSITIVE);
	private static Pattern END_REGEX = Pattern.compile("^\\s*end\\s*$", Pattern.CASE_INSENSITIVE);
	private String source;

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

	public Program parse()
	{
		Program p = new Program();
		boolean inBlock = false;
		Block b = null;

		String[] lines = this.source.split("\n");
		for (String line : lines)
		{
			line = line.trim();

			Matcher def = DEF_REGEX.matcher(line);
			Matcher end = END_REGEX.matcher(line);
			Matcher lbl = LBL_REGEX.matcher(line);
			Matcher whitespace = WHITESPACE_REGEX.matcher(line);
			Matcher comment = COMMENT_REGEX.matcher(line);

			if (def.find())
			{
				if (inBlock)
					System.out.println("ERROR: Nested blocks are not allowed.");

				b = new Block(def.group(1));
				inBlock = true;
			}
			else if (end.matches())
			{
				p.addBlock(b);
				inBlock = false;
			}
			else if (lbl.find())
			{
				b.addLabel(lbl.group(1));
			}
			else if (!whitespace.matches() && !comment.matches())
			{
				b.addInstruction(Instruction.fromString(line));
			}
		}

		return p;
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
