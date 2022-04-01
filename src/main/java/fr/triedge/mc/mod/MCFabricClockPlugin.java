package fr.triedge.mc.mod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class MCFabricClockPlugin implements ModInitializer{
	
	public static final Logger LOGGER = LoggerFactory.getLogger("fabricclock18");
	public static final String MODID = "fabricclock18";
	
	private Clock clock;
	
	@Override
	public void onInitialize() {
		registerEvents();
		//registerResources();
	}
	/*
	private void registerResources() {
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {

			@Override
			public Identifier getFabricId() {
				new Identifier("fabricclock", "my_resources");
			}

			@Override
			public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager manager,
					Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor,
					Executor applyExecutor) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void reload(ResourceManager manager) {
				
			}
			
		});
	}*/

	private void registerEvents() {
		clock = new Clock();
		
		HudRenderCallback.EVENT.register((matrices,delta)->{
			clock.render(matrices);
		});
	}

}
