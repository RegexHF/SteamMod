package com.cthulhu.client.hooks.overrides;

import java.awt.Color;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.src.Block;
import net.minecraft.src.Chunk;
import net.minecraft.src.Direction;
import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.EnumSkyBlock;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Gui;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.GuiPlayerInfo;
import net.minecraft.src.I18n;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Potion;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.Score;
import net.minecraft.src.ScoreObjective;
import net.minecraft.src.ScorePlayerTeam;
import net.minecraft.src.StringUtils;

public class OverrideGuiIngame extends GuiIngame 
{	

	public OverrideGuiIngame(Minecraft par1Minecraft) 
	{
		super(par1Minecraft);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void renderGameOverlay(float par1, boolean par2, int par3, int par4)
	{
		ScaledResolution var5 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int var6 = var5.getScaledWidth();
        int var7 = var5.getScaledHeight();
        FontRenderer var8 = this.mc.fontRenderer;
        this.mc.entityRenderer.setupOverlayRendering();
        GL11.glEnable(GL11.GL_BLEND);

        if (Minecraft.isFancyGraphicsEnabled())
        {
            this.renderVignette(this.mc.thePlayer.getBrightness(par1), var6, var7);
        }
        else
        {
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        }

        ItemStack var9 = this.mc.thePlayer.inventory.armorItemInSlot(3);

        if (this.mc.gameSettings.thirdPersonView == 0 && var9 != null && var9.itemID == Block.pumpkin.blockID)
        {
            this.renderPumpkinBlur(var6, var7);
        }

        if (!this.mc.thePlayer.isPotionActive(Potion.confusion))
        {
            float var10 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * par1;

            if (var10 > 0.0F)
            {
                this.func_130015_b(var10, var6, var7);
            }
        }

        int var11;
        int var12;
        int var13;

        if (!this.mc.playerController.enableEverythingIsScrewedUpMode())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.func_110434_K().func_110577_a(field_110330_c);
            InventoryPlayer var31 = this.mc.thePlayer.inventory;
            this.zLevel = -90.0F;
            this.drawTexturedModalRect(var6 / 2 - 91, var7 - 22, 0, 0, 182, 22);
            this.drawTexturedModalRect(var6 / 2 - 91 - 1 + var31.currentItem * 20, var7 - 22 - 1, 0, 22, 24, 22);
            this.mc.func_110434_K().func_110577_a(field_110324_m);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
            this.drawTexturedModalRect(var6 / 2 - 7, var7 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(GL11.GL_BLEND);
            this.mc.mcProfiler.startSection("bossHealth");
            this.renderBossHealth();
            this.mc.mcProfiler.endSection();

            if (this.mc.playerController.shouldDrawHUD())
            {
                this.func_110327_a(var6, var7);
            }

            GL11.glDisable(GL11.GL_BLEND);
            this.mc.mcProfiler.startSection("actionBar");
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.enableGUIStandardItemLighting();

            for (var11 = 0; var11 < 9; ++var11)
            {
                var12 = var6 / 2 - 90 + var11 * 20 + 2;
                var13 = var7 - 16 - 3;
                this.renderInventorySlot(var11, var12, var13, par1);
            }

            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            this.mc.mcProfiler.endSection();
        }

        int var32;

        if (this.mc.thePlayer.getSleepTimer() > 0)
        {
            this.mc.mcProfiler.startSection("sleep");
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            var32 = this.mc.thePlayer.getSleepTimer();
            float var34 = (float)var32 / 100.0F;

            if (var34 > 1.0F)
            {
                var34 = 1.0F - (float)(var32 - 100) / 10.0F;
            }

            var12 = (int)(220.0F * var34) << 24 | 1052704;
            drawRect(0, 0, var6, var7, var12);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            this.mc.mcProfiler.endSection();
        }

        var32 = 16777215;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        var11 = var6 / 2 - 91;
        int var14;
        int var15;
        int var17;
        int var16;
        float var33;
        short var37;

        if (this.mc.thePlayer.func_110317_t())
        {
            this.mc.mcProfiler.startSection("jumpBar");
            this.mc.func_110434_K().func_110577_a(Gui.field_110324_m);
            var33 = this.mc.thePlayer.func_110319_bJ();
            var37 = 182;
            var14 = (int)(var33 * (float)(var37 + 1));
            var15 = var7 - 32 + 3;
            this.drawTexturedModalRect(var11, var15, 0, 84, var37, 5);

            if (var14 > 0)
            {
                this.drawTexturedModalRect(var11, var15, 0, 89, var14, 5);
            }

            this.mc.mcProfiler.endSection();
        }
        else if (this.mc.playerController.func_78763_f())
        {
            this.mc.mcProfiler.startSection("expBar");
            this.mc.func_110434_K().func_110577_a(Gui.field_110324_m);
            var12 = this.mc.thePlayer.xpBarCap();

            if (var12 > 0)
            {
                var37 = 182;
                var14 = (int)(this.mc.thePlayer.experience * (float)(var37 + 1));
                var15 = var7 - 32 + 3;
                this.drawTexturedModalRect(var11, var15, 0, 64, var37, 5);

                if (var14 > 0)
                {
                    this.drawTexturedModalRect(var11, var15, 0, 69, var14, 5);
                }
            }

            this.mc.mcProfiler.endSection();

            if (this.mc.thePlayer.experienceLevel > 0)
            {
                this.mc.mcProfiler.startSection("expLevel");
                boolean var35 = false;
                var14 = var35 ? 16777215 : 8453920;
                String var42 = "" + this.mc.thePlayer.experienceLevel;
                var16 = (var6 - var8.getStringWidth(var42)) / 2;
                var17 = var7 - 31 - 4;
                var8.drawString(var42, var16 + 1, var17, 0);
                var8.drawString(var42, var16 - 1, var17, 0);
                var8.drawString(var42, var16, var17 + 1, 0);
                var8.drawString(var42, var16, var17 - 1, 0);
                var8.drawString(var42, var16, var17, var14);
                this.mc.mcProfiler.endSection();
            }
        }

        String var36;

        if (this.mc.gameSettings.heldItemTooltips)
        {
            this.mc.mcProfiler.startSection("toolHighlight");

            if (this.remainingHighlightTicks > 0 && this.highlightingItemStack != null)
            {
                var36 = this.highlightingItemStack.getDisplayName();
                var13 = (var6 - var8.getStringWidth(var36)) / 2;
                var14 = var7 - 59;

                if (!this.mc.playerController.shouldDrawHUD())
                {
                    var14 += 14;
                }

                var15 = (int)((float)this.remainingHighlightTicks * 256.0F / 10.0F);

                if (var15 > 255)
                {
                    var15 = 255;
                }

                if (var15 > 0)
                {
                    GL11.glPushMatrix();
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    var8.drawStringWithShadow(var36, var13, var14, 16777215 + (var15 << 24));
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glPopMatrix();
                }
            }

            this.mc.mcProfiler.endSection();
        }

        if (this.mc.isDemo())
        {
            this.mc.mcProfiler.startSection("demo");
            var36 = "";

            if (this.mc.theWorld.getTotalWorldTime() >= 120500L)
            {
                var36 = I18n.func_135053_a("demo.demoExpired");
            }
            else
            {
                var36 = I18n.func_135052_a("demo.remainingTime", new Object[] {StringUtils.ticksToElapsedTime((int)(120500L - this.mc.theWorld.getTotalWorldTime()))});
            }

            var13 = var8.getStringWidth(var36);
            var8.drawStringWithShadow(var36, var6 - var13 - 10, 5, 16777215);
            this.mc.mcProfiler.endSection();
        }

        int var21;
        int var23;
        int var22;

        if (this.mc.gameSettings.showDebugInfo)
        {
            this.mc.mcProfiler.startSection("debug");
            GL11.glPushMatrix();
            var8.drawStringWithShadow("Steam Modification" + this.mc.debug + ")", 2, 2, 16777215);
            var8.drawStringWithShadow(this.mc.debugInfoRenders(), 2, 12, 16777215);
            var8.drawStringWithShadow(this.mc.getEntityDebug(), 2, 22, 16777215);
            var8.drawStringWithShadow(this.mc.debugInfoEntities(), 2, 32, 16777215);
            var8.drawStringWithShadow(this.mc.getWorldProviderName(), 2, 42, 16777215);
            long var38 = Runtime.getRuntime().maxMemory();
            long var40 = Runtime.getRuntime().totalMemory();
            long var39 = Runtime.getRuntime().freeMemory();
            long var46 = var40 - var39;
            String var20 = "Used memory: " + var46 * 100L / var38 + "% (" + var46 / 1024L / 1024L + "MB) of " + var38 / 1024L / 1024L + "MB";
            var21 = 14737632;
            this.drawString(var8, var20, var6 - var8.getStringWidth(var20) - 2, 2, 14737632);
            var20 = "Allocated memory: " + var40 * 100L / var38 + "% (" + var40 / 1024L / 1024L + "MB)";
            this.drawString(var8, var20, var6 - var8.getStringWidth(var20) - 2, 12, 14737632);
            var22 = MathHelper.floor_double(this.mc.thePlayer.posX);
            var23 = MathHelper.floor_double(this.mc.thePlayer.posY);
            int var24 = MathHelper.floor_double(this.mc.thePlayer.posZ);
            this.drawString(var8, String.format("x: %.5f (%d) // c: %d (%d)", new Object[] {Double.valueOf(this.mc.thePlayer.posX), Integer.valueOf(var22), Integer.valueOf(var22 >> 4), Integer.valueOf(var22 & 15)}), 2, 64, 14737632);
            this.drawString(var8, String.format("y: %.3f (feet pos, %.3f eyes pos)", new Object[] {Double.valueOf(this.mc.thePlayer.boundingBox.minY), Double.valueOf(this.mc.thePlayer.posY)}), 2, 72, 14737632);
            this.drawString(var8, String.format("z: %.5f (%d) // c: %d (%d)", new Object[] {Double.valueOf(this.mc.thePlayer.posZ), Integer.valueOf(var24), Integer.valueOf(var24 >> 4), Integer.valueOf(var24 & 15)}), 2, 80, 14737632);
            int var25 = MathHelper.floor_double((double)(this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            this.drawString(var8, "f: " + var25 + " (" + Direction.directions[var25] + ") / " + MathHelper.wrapAngleTo180_float(this.mc.thePlayer.rotationYaw), 2, 88, 14737632);

            if (this.mc.theWorld != null && this.mc.theWorld.blockExists(var22, var23, var24))
            {
                Chunk var26 = this.mc.theWorld.getChunkFromBlockCoords(var22, var24);
                this.drawString(var8, "lc: " + (var26.getTopFilledSegment() + 15) + " b: " + var26.getBiomeGenForWorldCoords(var22 & 15, var24 & 15, this.mc.theWorld.getWorldChunkManager()).biomeName + " bl: " + var26.getSavedLightValue(EnumSkyBlock.Block, var22 & 15, var23, var24 & 15) + " sl: " + var26.getSavedLightValue(EnumSkyBlock.Sky, var22 & 15, var23, var24 & 15) + " rl: " + var26.getBlockLightValue(var22 & 15, var23, var24 & 15, 0), 2, 96, 14737632);
            }

            this.drawString(var8, String.format("ws: %.3f, fs: %.3f, g: %b, fl: %d", new Object[] {Float.valueOf(this.mc.thePlayer.capabilities.getWalkSpeed()), Float.valueOf(this.mc.thePlayer.capabilities.getFlySpeed()), Boolean.valueOf(this.mc.thePlayer.onGround), Integer.valueOf(this.mc.theWorld.getHeightValue(var22, var24))}), 2, 104, 14737632);
            GL11.glPopMatrix();
            this.mc.mcProfiler.endSection();
        }
        else
        {
        	
        }

        if (this.recordPlayingUpFor > 0)
        {
            this.mc.mcProfiler.startSection("overlayMessage");
            var33 = (float)this.recordPlayingUpFor - par1;
            var13 = (int)(var33 * 255.0F / 20.0F);

            if (var13 > 255)
            {
                var13 = 255;
            }

            if (var13 > 8)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(var6 / 2), (float)(var7 - 68), 0.0F);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                var14 = 16777215;

                if (this.recordIsPlaying)
                {
                    var14 = Color.HSBtoRGB(var33 / 50.0F, 0.7F, 0.6F) & 16777215;
                }

                var8.drawString(this.recordPlaying, -var8.getStringWidth(this.recordPlaying) / 2, -4, var14 + (var13 << 24 & -16777216));
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
            }

            this.mc.mcProfiler.endSection();
        }

        ScoreObjective var43 = this.mc.theWorld.getScoreboard().func_96539_a(1);

        if (var43 != null)
        {
            this.func_96136_a(var43, var7, var6, var8);
        }

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, (float)(var7 - 48), 0.0F);
        this.mc.mcProfiler.startSection("chat");
        this.persistantChatGUI.drawChat(this.updateCounter);
        this.mc.mcProfiler.endSection();
        GL11.glPopMatrix();
        var43 = this.mc.theWorld.getScoreboard().func_96539_a(0);

        if (this.mc.gameSettings.keyBindPlayerList.pressed && (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.playerInfoList.size() > 1 || var43 != null))
        {
            this.mc.mcProfiler.startSection("playerList");
            NetClientHandler var41 = this.mc.thePlayer.sendQueue;
            List var44 = var41.playerInfoList;
            var15 = var41.currentServerMaxPlayers;
            var16 = var15;

            for (var17 = 1; var16 > 20; var16 = (var15 + var17 - 1) / var17)
            {
                ++var17;
            }

            int var45 = 300 / var17;

            if (var45 > 150)
            {
                var45 = 150;
            }

            int var19 = (var6 - var17 * var45) / 2;
            byte var47 = 10;
            drawRect(var19 - 1, var47 - 1, var19 + var45 * var17, var47 + 9 * var16, Integer.MIN_VALUE);

            for (var21 = 0; var21 < var15; ++var21)
            {
                var22 = var19 + var21 % var17 * var45;
                var23 = var47 + var21 / var17 * 9;
                drawRect(var22, var23, var22 + var45 - 1, var23 + 8, 553648127);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_ALPHA_TEST);

                if (var21 < var44.size())
                {
                    GuiPlayerInfo var49 = (GuiPlayerInfo)var44.get(var21);
                    ScorePlayerTeam var48 = this.mc.theWorld.getScoreboard().getPlayersTeam(var49.name);
                    String var52 = ScorePlayerTeam.formatPlayerName(var48, var49.name);
                    var8.drawStringWithShadow(var52, var22, var23, 16777215);

                    if (var43 != null)
                    {
                        int var27 = var22 + var8.getStringWidth(var52) + 5;
                        int var28 = var22 + var45 - 12 - 5;

                        if (var28 - var27 > 5)
                        {
                            Score var29 = var43.getScoreboard().func_96529_a(var49.name, var43);
                            String var30 = EnumChatFormatting.YELLOW + "" + var29.getScorePoints();
                            var8.drawStringWithShadow(var30, var28 - var8.getStringWidth(var30), var23, 16777215);
                        }
                    }

                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    this.mc.func_110434_K().func_110577_a(field_110324_m);
                    byte var53 = 0;
                    byte var50;

                    if (var49.responseTime < 0)
                    {
                        var50 = 5;
                    }
                    else if (var49.responseTime < 150)
                    {
                        var50 = 0;
                    }
                    else if (var49.responseTime < 300)
                    {
                        var50 = 1;
                    }
                    else if (var49.responseTime < 600)
                    {
                        var50 = 2;
                    }
                    else if (var49.responseTime < 1000)
                    {
                        var50 = 3;
                    }
                    else
                    {
                        var50 = 4;
                    }

                    this.zLevel += 100.0F;
                    this.drawTexturedModalRect(var22 + var45 - 12, var23, 0 + var53 * 10, 176 + var50 * 8, 10, 8);
                    this.zLevel -= 100.0F;
                }
            }
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
	}

}
