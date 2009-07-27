package net.mt2d2.ariel;

public class Instruction
{
	public Opcode opcode;
	public String argument;
	
	public Instruction(Opcode op, String arg)
	{
		this.opcode = op;
		this.argument = arg;
	}
	
	public Instruction(Opcode op)
	{
		this(op, null);
	}
	
	public static Instruction fromString(String line)
	{	
		String[] chunks = line.split(" ");
		chunks[0] = chunks[0].toUpperCase();
		
		if (chunks.length == 2)
			return new Instruction(Opcode.valueOf(chunks[0]), chunks[1]);
		else
			return new Instruction(Opcode.valueOf(chunks[0]));
	}
	
	public Opcode getOpcode()
	{
		return this.opcode;
	}
	
	public String getArgument()
	{
		return this.argument;
	}
	
	@Override
	public String toString()
	{
		return this.opcode.toString() + ((this.argument == null) ? "" : " [" + this.argument +"]");
	}
}
