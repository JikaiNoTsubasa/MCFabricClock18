package fr.triedge.mc.mod;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class Clock {
	
	private Identifier idClockBackground;
	private Identifier idClockSun;
	private Identifier idClockMoon;
	
	public Clock() {
		idClockBackground = new Identifier(MCFabricClockPlugin.MODID, "textures/clockbackground1.png");
		idClockSun = new Identifier(MCFabricClockPlugin.MODID, "textures/clocksun.png");
		idClockMoon = new Identifier(MCFabricClockPlugin.MODID, "textures/clockmoon.png");
	}

	public long getTime() {
		@SuppressWarnings("resource")
		long dayTime = MinecraftClient.getInstance().world.getTimeOfDay();
		while (dayTime >= 24000) {
			dayTime -= 24000;
		}
		return dayTime;
	}
	
	public void render(MatrixStack matrices) {
		int w = MinecraftClient.getInstance().getWindow().getScaledWidth();
		int h = MinecraftClient.getInstance().getWindow().getScaledHeight();
		int xLocation = w - 50 - 5;
		int yLocation = h - 10 - 5;
		Identifier icon = idClockSun;
		int iconX = xLocation;
		if (getTime()<13000) {
			icon = idClockSun;
			iconX = xLocation + (int)((getTime()*100/13000)*50/100) -3;
			
		}else {
			icon = idClockMoon;
			long t = getTime() - 13000;
			iconX = xLocation + (int)((t*100/11000)*50/100) -3;
		}
		RenderSystem.setShaderTexture(0, idClockBackground);
		DrawableHelper.drawTexture(matrices, xLocation, yLocation, 0, 0, 0, 50, 10, 50, 10); // Draw back ground
		RenderSystem.setShaderTexture(0, icon);
		DrawableHelper.drawTexture(matrices, iconX, yLocation, 1, 0, 0, 11, 11, 11, 11); // Draw for ground
	}
}
