package com.damage.showme;

import com.damage.showme.data.ModDynamicRegistry;
import com.damage.showme.misc.DamageNumber;
import com.damage.showme.network.ModNetworkPayloads;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowMeYourDamage implements ModInitializer {
	public static final String MOD_ID = "showmeyourdamage";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModNetworkPayloads.register();
		ModDynamicRegistry.register();

		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((_, _, _) -> DamageNumber.reload());

		LOGGER.info("Show Me Your Damage Has Successfully Loaded!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
