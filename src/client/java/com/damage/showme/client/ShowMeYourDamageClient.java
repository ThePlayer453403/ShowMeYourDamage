package com.damage.showme.client;

import com.damage.showme.client.network.ModPayloadReceiver;
import com.damage.showme.client.render.ModRendering;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class ShowMeYourDamageClient implements ClientModInitializer {
	public static boolean installedModernUI = FabricLoader.getInstance().isModLoaded("modernui");
	@Override
	public void onInitializeClient() {
		ModRendering.register();
		ModPayloadReceiver.register();
	}
}