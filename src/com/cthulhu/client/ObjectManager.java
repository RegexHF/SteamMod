package com.cthulhu.client;

import java.util.*;

public class ObjectManager 
{

	public ArrayList<Object> objects = new ArrayList<Object>();
	
	public ObjectManager()
	{
		
	}
	
	public void registerObject(Object o)
	{
		objects.add(o);
	}
	
}
