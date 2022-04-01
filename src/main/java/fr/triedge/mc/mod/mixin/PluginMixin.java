package fr.triedge.mc.mod.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class PluginMixin {
	
	@Accessor("client")
    protected abstract MinecraftClient getClient();

	@Inject(method = { "renderStatusEffectOverlay" }, at = { @At("RETURN") })
	private void onRenderStatusEffectOverlay(CallbackInfo ci) {
		//MinecraftClient.getInstance().renderer.//draw("THIS IS SPARTA", 50, 50, 16777215);
	}
	
	@SuppressWarnings({ "resource", "static-access" })
	@Inject(method = "render", at = @At("RETURN"), cancellable = true) 
	public void onRender (MatrixStack matrices, float tickDelta, CallbackInfo info) {
		MinecraftClient client = getClient();
		long dayTime = client.world.getTimeOfDay();
		while (dayTime >= 24000) {
			dayTime -= 24000;
		}
		client.getInstance().textRenderer.draw(matrices, "Time: "+dayTime, 5, 5, -1);
	}
	
}
