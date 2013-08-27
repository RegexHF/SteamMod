package com.cthulhu.client.hooks.hook;

import com.cthulhu.client.Inception;

public class HookMinecraft 
{

	public static void startGame()
	{
		Inception.getInception().init();
		Inception.getWrapper().getConfigurationLoader().load();
	}
	
	public static void onKeyPress(int eventKey)
	{
		
	}
	
}
