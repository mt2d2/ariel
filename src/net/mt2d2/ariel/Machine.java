package net.mt2d2.ariel;

import java.lang.reflect.Method;
import java.util.Stack;

public class Machine
{
	private Program			program;

	private Stack<Object>	stack;
	private int				ip	= 0;
	private boolean			halt;

	public Machine(Program program)
	{
		this.program = program;
		this.stack = new Stack<Object>();
		this.ip = 0;
	}

	public void execute()
	{
		Block block = this.program.getBlock("main");

		while (!this.halt)
		{
			Instruction instr = block.getInstruction(ip++);

			switch (instr.getOpcode())
			{
				case LIT_INT:
					pushInteger(instr.getArgument());
					break;
				case LOAD_INT:
					push((Integer) block.getMemory(instr.getArgument()));
					break;
				case STORE_INT:
					block.addMemory(instr.getArgument(), popInteger());
					break;
				case ADD_INT:
					push(popInteger() + popInteger());
					break;
				case SUB_INT:
					push(popInteger() - popInteger());
					break;
				case MUL_INT:
					push(popInteger() * popInteger());
					break;
				case DIV_INT:
					push(popInteger() / popInteger());
					break;
				case MOD_INT:
					push(popInteger() % popInteger());
					break;
				case LE_INT:
					push(popInteger() < popInteger());
					break;
				case LEQ_INT:
					push(popInteger() <= popInteger());
					break;
				case GE_INT:
					push(popInteger() > popInteger());
					break;
				case GEQ_INT:
					push(popInteger() >= popInteger());
					break;
				case EQ_INT:
					push(popInteger() == popInteger());
					break;
				case NEQ_INT:
					push(popInteger() != popInteger());
					break;
				case LIT_FLOAT:
					pushFloat(instr.getArgument());
					break;
				case LOAD_FLOAT:
					push((Float) block.getMemory(instr.getArgument()));
					break;
				case STORE_FLOAT:
					block.addMemory(instr.getArgument(), popFloat());
					break;
				case ADD_FLOAT:
					push(popFloat() + popFloat());
					break;
				case SUB_FLOAT:
					push(popFloat() - popFloat());
					break;
				case MUL_FLOAT:
					push(popFloat() * popFloat());
					break;
				case DIV_FLOAT:
					push(popFloat() / popFloat());
					break;
				case MOD_FLOAT:
					push(popFloat() % popFloat());
					break;
				case LE_FLOAT:
					push(popFloat() < popFloat());
					break;
				case LEQ_FLOAT:
					push(popFloat() <= popFloat());
					break;
				case GE_FLOAT:
					push(popFloat() > popFloat());
					break;
				case GEQ_FLOAT:
					push(popFloat() >= popFloat());
					break;
				case EQ_FLOAT:
					push(popFloat() == popFloat());
					break;
				case NEQ_FLOAT:
					push(popFloat() != popFloat());
					break;
				case JMP:
					if (popBoolean())
						ip = block.getLabel(instr.getArgument());
					break;
				case RTRN:
					if (this.stack.size() != 0)
					{
						block = (Block) pop();
						this.ip = block.getIp();
					}
					else
					{
						this.halt = true;
					}
					break;
				case CALL:
					// Save the spot in the old stack
					block.setIp(this.ip);
					push(block);

					// start at ip 0 of new block
					block = this.program.getBlock(instr.getArgument());
					this.ip = 0;
					break;
				case INVOKE:
					String[] commands = instr.getArgument().split("#");

					try
					{
						Class<?> cl = Class.forName(commands[0]);
						Method mthd = cl.getMethod(commands[1]);
						push(mthd.invoke(cl.newInstance()));
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					break;
				case PRINT:
					System.out.println(pop());
					break;
			}
		}
	}

	private void pushFloat(String source)
	{
		push(Float.parseFloat(source));
	}

	private Float popFloat()
	{
		return (Float) this.stack.pop();
	}

	private void pushInteger(String source)
	{
		this.stack.push(Integer.parseInt(source));
	}

	private void push(Object obj)
	{
		this.stack.push(obj);
	}

	private Boolean popBoolean()
	{
		return (Boolean) this.stack.pop();
	}

	private Integer popInteger()
	{
		return (Integer) this.stack.pop();
	}

	private Object pop()
	{
		return this.stack.pop();
	}
}
