package net.mt2d2.ariel;

import java.util.ArrayList;
import java.util.List;

public class Program
{	
	private List<Block> blocks;
	
	public Program()
	{
		this.blocks = new ArrayList<Block>();
	}
	
	public Program(Source source)
	{		
		this();
		
		Block main = new Block("main");
		
		String[] lines = source.toString().split("\n");
		
		for (String line : lines)
		{
			line = line.trim();
			
			if (! line.equals("") && ! line.startsWith("//"))
				main.addInstruction(Instruction.fromString(line));
		}
		
		this.blocks.add(this.createVersionBlock());
		this.blocks.add(main);
	}
	
	private Block createVersionBlock()
	{
		Block version = new Block("version");
		
		version.addInstruction(new Instruction(Opcode.LIT_FLOAT, "0.2"));
		version.addInstruction(new Instruction(Opcode.PRINT));
		version.addInstruction(new Instruction(Opcode.RTRN));
		
		return version;
	}

	public Block getBlock(String name)
	{
		for (Block b : this.blocks)
			if (b.getName().equals(name))
				return b;
		
		return null;
	}
}
