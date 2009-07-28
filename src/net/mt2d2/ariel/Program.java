package net.mt2d2.ariel;

import java.util.ArrayList;
import java.util.List;

public class Program
{
	private List<Block> blocks;

	public Program()
	{
		this.blocks = new ArrayList<Block>();
		
		// install default builtins
		this.addBlock(this.createVersionBlock());
	}

	private Block createVersionBlock()
	{
		Block version = new Block("version");

		version.addInstruction(new Instruction(Opcode.LIT_FLOAT, Driver.VERSION));
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

	public void addBlock(Block block)
	{
		this.blocks.add(block);
	}
}
