package net.mt2d2.ariel;

public class Value
{
	private Object	o;

	public Value(Object o)
	{
		this.o = o;
	}

	public Object getObject()
	{
		return o;
	}

	public boolean isNumber()
	{
		return this.isInteger() || this.isDouble();
	}

	public boolean isInteger()
	{
		return (o instanceof Integer);
	}

	public boolean isDouble()
	{
		return (o instanceof Double);
	}

	public Double toNumber()
	{
		if (this.isInteger())
			return new Double((Integer)o);
		else if (this.isDouble())
			return (Double) o;
		else
			return Double.class.cast(o);		
	}
	
	@Override
	public String toString()
	{
		return this.o.toString();
	}
}