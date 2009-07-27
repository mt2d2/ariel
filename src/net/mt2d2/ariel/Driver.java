package net.mt2d2.ariel;

public class Driver
{
	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			System.out.println("ariel: no input provided\n" + getUsage());
			System.exit(-1);
		}
	
		Source source = new Source(args[0]);
		Program program = new Program(source);
		Machine machine = new Machine(program);
		
		machine.execute();	
	}
	
	private static String getUsage()
	{
		return "Usage:\n\tariel file.al";
	}
}
