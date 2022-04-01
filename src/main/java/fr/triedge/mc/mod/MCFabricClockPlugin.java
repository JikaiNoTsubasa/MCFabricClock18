package fr.triedge.mc.mod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;

public class MCFabricClockPlugin implements ModInitializer{
	
	public static final Logger LOGGER = LoggerFactory.getLogger("fabricclock18");
	
	

	@Override
	public void onInitialize() {
		registerEvents();
		
	}
	
	public void registerEvents() {
		HudRenderCallback.EVENT.register((matrices,delta)->{
			MinecraftClient client = MinecraftClient.getInstance();
			long dayTime = client.world.getTimeOfDay();
			while (dayTime >= 24000) {
				dayTime -= 24000;
			}
			client.textRenderer.draw(matrices, "Time: "+dayTime, 5, 5, -1);
			
			//ResourceManagerHelper.
			//Sprite sp = new Sprite( new SpriteAtlasTexture(null), null, 0, 0, 0, 0, 0, null);
			//Screen.drawSprite(matrices, 0, 0, 0, 0, 0, null)
		});
	}

}
