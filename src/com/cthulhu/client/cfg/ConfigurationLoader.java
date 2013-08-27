package com.cthulhu.client.cfg;

import java.util.HashMap;

public class ConfigurationLoader
{

	ConfigurationModuleManager manager = new ConfigurationModuleManager();
	ConfigurationUtils utils = new ConfigurationUtils();
	
	HashMap<String, String> configuration = new HashMap<String, String>();
	
	public ConfigurationLoader()
	{
		
	}
	
	public void load()
	{
		utils.mkfolder("Inception");
		utils.mkfile("Inception/configuration.ldc");
		
		manager.registerModule(new ConfigurationModule("CLIENT_NAME", "CLIENT_NAME", "Gets the client name", "CLIENT_NAME(CHAR)VALUE", "1.0.0.0", "Regex_", "8/26/13", "8/26/13", "CLIENT_NAME", configuration));
		manager.registerModule(new ConfigurationModule("CLIENT_VERSION", "CLIENT_VERSION", "Gets the client version", "CLIENT_VERSION(CHAR)VALUE", "1.0.0.0", "Regex_", "8/26/13", "8/26/13", "CLIENT_VERSION", configuration));
	
		for(ConfigurationModule cm : manager.getModules())
		{
			utils.readFile("Inception/configuration.ldc", ":", cm, configuration);
		}
	}
	
}