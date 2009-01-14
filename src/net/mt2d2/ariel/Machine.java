package net.mt2d2.ariel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

public class Machine
{
	private Stack<Value>			stack	= new Stack<Value>();
	private Map<String, Value>		memory	= new HashMap<String, Value>();
	private Program					program;

	public Machine()
	{
		this.program = new Program();
	}

	public Machine(Program userDefinedProgram)
	{
		this.program = userDefinedProgram;
	}

	public int getStackSize()
	{
		return this.stack.size();
	}
	
	public int getMemorySize()
	{
		return this.memory.size();
	}

	public void run() throws Exception
	{
		Iterator<Instruction> itr = this.program.iterator();

		while (itr.hasNext())
		{
			Instruction nextInstruction = itr.next();

			switch (nextInstruction.getOpcode())
			{
				case PUSH:
					this.handlePush(nextInstruction);
					break;
				case POP:
					this.handlePop();
					break;
				case PRINT:
					this.handlePrint();
					break;
				case LOAD:
					this.handleLoad(nextInstruction);
					break;
				case STORE:
					this.handleStore(nextInstruction);
					break;
				case ADD:
					this.handleAdd();
					break;
				case SUBTRACT:
					this.handleSubtract();
					break;
				case MULTIPLY:
					this.handleMultiply();
					break;
				case DIVIDE:
					this.handleDivide();
					break;
				default:
					System.out.println("Unidentified Opcode...");
					break;

			}
		}
		
		// Clean the stack and memory
		this.stack.clear();
		this.memory.clear();
	}

	private void handleStore(Instruction nextInstruction)
	{
		this.memory.put(nextInstruction.getArgument().toString(), this.stack.pop());
	}

	private void handleLoad(Instruction nextInstruction)
	{
		this.stack.push(this.memory.get(nextInstruction.getArgument().toString()));
	}

	private void handleDivide()
	{
		Value arg1 = this.stack.pop();
		Value arg2 = this.stack.pop();

		if (arg1.isNumber() && arg2.isNumber())
			this.stack.push(new Value(arg1.toNumber() / arg2.toNumber()));
	}

	private void handleMultiply()
	{
		Value arg1 = this.stack.pop();
		Value arg2 = this.stack.pop();

		if (arg1.isNumber() && arg2.isNumber())
			this.stack.push(new Value(arg1.toNumber() * arg2.toNumber()));
	}

	private void handleSubtract()
	{
		Value arg1 = this.stack.pop();
		Value arg2 = this.stack.pop();

		if (arg1.isNumber() && arg2.isNumber())
			this.stack.push(new Value(arg1.toNumber() - arg2.toNumber()));
	}

	private void handleAdd()
	{
		Value arg1 = this.stack.pop();
		Value arg2 = this.stack.pop();

		if (arg1.isNumber() && arg2.isNumber())
			this.stack.push(new Value(arg1.toNumber() + arg2.toNumber()));
	}

	private void handlePrint()
	{
		System.out.println(this.stack.peek());
	}

	private void handlePop()
	{
		this.stack.pop();
	}

	private void handlePush(Instruction nextInstruction)
	{
		this.stack.push(nextInstruction.getArgument());
	}

	public String getSystemStatus()
	{
		String toReturn = new String();

		toReturn += "\n";
		toReturn += "--------------------------\n";
		toReturn += "Final system stack size: " + this.getStackSize() + "\n";
		toReturn += "Final system memory size: " + this.getMemorySize() + "\n";
		
		return toReturn;
	}
}
