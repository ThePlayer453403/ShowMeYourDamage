package com.damage.showme.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class ModNetworkPayloads {
    public static void register() {
        PayloadTypeRegistry.clientboundPlay().register(DamageNumberS2CPayload.TYPE, DamageNumberS2CPayload.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(DamageTextS2CPayload.TYPE, DamageTextS2CPayload.CODEC);
    }
}
