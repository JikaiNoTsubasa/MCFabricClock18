package fr.triedge.mc.mod.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import com.mojang.blaze3d.systems.RenderSystem;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class PluginMixin {
	
	private static final Identifier TEXTURE = new Identifier("minecraft", "textures/gui/container/dispenser.png");
	
	@Accessor("client")
    protected abstract MinecraftClient getClient();
	
	@SuppressWarnings({ "resource", "static-access" })
	@Inject(method = "render", at = @At("RETURN"), cancellable = true) 
	public void onRender (MatrixStack matrices, float tickDelta, CallbackInfo info) {
		/*
		MinecraftClient client = getClient();
		long dayTime = client.world.getTimeOfDay();
		while (dayTime >= 24000) {
			dayTime -= 24000;
		}
		client.getInstance().textRenderer.draw(matrices, "Time: "+dayTime, 5, 5, -1);
		*/
	}
	
}
