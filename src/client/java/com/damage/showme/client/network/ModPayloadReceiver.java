package com.damage.showme.client.network;

import com.damage.showme.client.misc.DamageNumberManager;
import com.damage.showme.network.DamageNumberS2CPayload;
import com.damage.showme.network.DamageTextS2CPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ModPayloadReceiver {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(DamageTextS2CPayload.TYPE, (payload, _) -> DamageNumberManager.addDamageNumberInstance(payload));
        ClientPlayNetworking.registerGlobalReceiver(DamageNumberS2CPayload.TYPE, (payload, _) -> DamageNumberManager.addDamageNumberInstance(payload));
    }
}
