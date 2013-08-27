package com.cthulhu.client.cfg;

import java.util.*;

public class ConfigurationModuleManager 
{

	ArrayList<ConfigurationModule> _modules = new ArrayList<ConfigurationModule>();
	
	public ConfigurationModuleManager()
	{
		
	}
	
	public ArrayList<ConfigurationModule> getModules()
	{
		return _modules;
	}
	
	public void registerModule(ConfigurationModule cm)
	{
		getModules().add(cm);
	}
	
}
