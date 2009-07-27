package net.mt2d2.ariel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Block
{
	private String name;
	private Map<String, Object> memory;
	private List<Instruction> instructions;
	private Map<String, Integer> labels;
	private int ip;

	public Block(String name)
	{
		this.name = name;
		this.memory = new HashMap<String, Object>();
		this.labels = new HashMap<String, Integer>();
		this.instructions = new ArrayList<Instruction>();
	}
	
	public int getLabel(String key)
	{
		return this.labels.get(key);
	}
	
	public void addLabel(String key)
	{
		this.labels.put(key, this.instructions.size());
	}

	public void addInstruction(Instruction instr)
	{
		this.instructions.add(instr);
	}

	public void addMemory(String key, Object value)
	{
		this.memory.put(key, value);
	}

	public Object getMemory(String key)
	{
		return this.memory.get(key);
	}

	public Instruction getInstruction(int i)
	{
		return this.instructions.get(i);
	}

	public boolean hasNext(int ip)
	{
		return ip < this.instructions.size();
	}

	public String getName()
	{
		return this.name;
	}

	public int getIp()
	{
		return this.ip;
	}

	public void setIp(int ip)
	{
		this.ip = ip;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for (Instruction instr : this.instructions)
			sb.append(instr.toString()).append('\n');

		return sb.toString();
	}
}
