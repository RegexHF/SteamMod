package com.cthulhu.client;

import com.cthulhu.client.cfg.ConfigurationLoader;

public class Wrapper 
{
	
	private ConfigurationLoader _configurationLoader = new ConfigurationLoader();
	
	public Wrapper()
	{
		
	}
	
	public ConfigurationLoader getConfigurationLoader()
	{
		return _configurationLoader;
	}
	
}
