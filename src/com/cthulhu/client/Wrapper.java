package com.cthulhu.client;

import com.cthulhu.client.cfg.ConfigurationLoader;

public class Wrapper 
{
	
	private ConfigurationLoader _configurationLoader = new ConfigurationLoader();
	private ObjectManager _objectManager = new ObjectManager();
	
	public Wrapper()
	{
		
	}
	
	public ConfigurationLoader getConfigurationLoader()
	{
		return _configurationLoader;
	}
	
	public ObjectManager getObjectManager()
	{
		return _objectManager;
	}
	
}
