package net.mt2d2.ariel;

public class Main
{
	public static void main(String[] args)
	{
		Reader r = new Reader("test.al");
		Program p = r.parseProgram();
		
		System.out.println(p);
		
		Machine vm = new Machine(p);
		
		try
		{
			vm.run();
			System.out.println(vm.getSystemStatus());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println();
			e.printStackTrace();
		}
	}
}
