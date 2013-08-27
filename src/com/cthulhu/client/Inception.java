package com.cthulhu.client;

public class Inception 
{
	
	private static Inception _inception = new Inception();
	private Wrapper _wrapper = new Wrapper();
	
	public Inception()
	{
		
	}
	
	public static Inception getInception()
	{
		return _inception;
	}
	
	public static Wrapper getWrapper()
	{
		return _inception._wrapper;
	}
	
	public void init()
	{
		_wrapper.getObjectManager().registerObject(null);
	}

}
