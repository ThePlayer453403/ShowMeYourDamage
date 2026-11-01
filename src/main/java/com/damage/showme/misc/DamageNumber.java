package com.damage.showme.misc;

import com.damage.showme.network.DamageNumberS2CPayload;
import com.damage.showme.network.DamageTextS2CPayload;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;

public class DamageNumber {
    public static void send(ServerLevel level, Vec3 position, float damage, int color) {
        DamageNumberS2CPayload payload = new DamageNumberS2CPayload(position, (int) Math.ceil(damage), color);
        PlayerLookup.around(level, position, 64).forEach(player -> ServerPlayNetworking.send(player, payload));
    }

    public static void send(ServerLevel level, Vec3 position, String damage, int color) {
        DamageTextS2CPayload payload = new DamageTextS2CPayload(position, damage, color);
        PlayerLookup.around(level, position, 64).forEach(player -> ServerPlayNetworking.send(player, payload));
    }
}
