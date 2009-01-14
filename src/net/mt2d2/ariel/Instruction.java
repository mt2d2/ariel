package net.mt2d2.ariel;
public class Instruction
{
	public Opcodes	opcode;
	public Value	argument;

	public Instruction(Opcodes opcode)
	{
		this.opcode = opcode;
	}

	public Instruction(Opcodes opcode, Value arg)
	{
		this.opcode = opcode;
		this.argument = arg;
	}

	public Opcodes getOpcode()
	{
		return opcode;
	}

	public Value getArgument()
	{
		return argument;
	}

	@Override
	public String toString()
	{
		String toReturn = new String();

		toReturn += this.opcode.name();

		if (this.argument != null)
			toReturn += "(" + this.argument.toString() + ")";

		return toReturn;
	}
}
