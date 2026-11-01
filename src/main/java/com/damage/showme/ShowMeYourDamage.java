package com.damage.showme;

import com.damage.showme.network.ModNetworkPayloads;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowMeYourDamage implements ModInitializer {
	public static final String MOD_ID = "showmeyourdamage";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModNetworkPayloads.register();
		LOGGER.info("Show Me Your Damage Has Successfully Loaded!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
