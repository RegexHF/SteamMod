package com.cthulhu.client.hooks.hook;

import com.cthulhu.client.Inception;
import com.cthulhu.client.hooks.events.EventRenderGui;

import net.minecraft.src.ScaledResolution;

public class HookGuiIngame {
	
	public static void renderGameOverlay(ScaledResolution sc)
	{
		for(Object o : Inception.getWrapper().getObjectManager().objects)
		{
			if(o instanceof EventRenderGui)
			{
				((EventRenderGui) o).renderGameOverlay(sc);
			}
		}
	}

}
