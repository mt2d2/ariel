package net.mt2d2.ariel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Program implements Iterable<Instruction>
{	
	private List<Instruction> instructions = new ArrayList<Instruction>();
	
	public Program()
	{
		// No defaults, we need user to enter a program
		// We can scan a text file for opcodes and arguments in the future
	}
	
	public void addInstruction(Opcodes opcode)
	{		
		this.instructions.add(new Instruction(opcode));
	}

	public void addInstruction(Opcodes opcode, Object argument)
	{
		this.instructions.add(new Instruction(opcode, new Value(argument)));
	}
	
	@Override
	public Iterator<Instruction> iterator()
	{
		return this.instructions.iterator();
	}
	
	@Override
	public String toString()
	{
		String toReturn = "";
		
		// toReturn += "There are a total of " + this.instructions.size() + " instructions\n";

		for (Instruction instruction : this.instructions)
			toReturn += instruction.toString() + "\n";
		
		return toReturn;
	}
}
