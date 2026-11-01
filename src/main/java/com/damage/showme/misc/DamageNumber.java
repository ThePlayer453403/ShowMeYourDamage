package com.damage.showme.misc;

import com.damage.showme.ShowMeYourDamage;
import com.damage.showme.data.DamageColorRegistryEntry;
import com.damage.showme.data.ModDynamicRegistry;
import com.damage.showme.network.DamageNumberS2CPayload;
import com.damage.showme.network.DamageTextS2CPayload;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DamageNumber {
    private static final Map<String, Integer> damageColorCache = new HashMap<>();
    public static void send(ServerLevel level, Vec3 position, float damage, int color) {
        DamageNumberS2CPayload payload = new DamageNumberS2CPayload(position, (int) Math.ceil(damage), color);
        PlayerLookup.around(level, position, 64).forEach(player -> ServerPlayNetworking.send(player, payload));
    }

    public static void send(ServerLevel level, Vec3 position, String damage, int color) {
        DamageTextS2CPayload payload = new DamageTextS2CPayload(position, damage, color);
        PlayerLookup.around(level, position, 64).forEach(player -> ServerPlayNetworking.send(player, payload));
    }

    public static int getColor(ServerLevel level, DamageSource damageSource) {
        Identifier identifier = damageSource.typeHolder().unwrapKey().map(ResourceKey::identifier).orElse(null);
        if (identifier == null) {
            ShowMeYourDamage.LOGGER.warn("Can't get register key for damage type: {}, using white as default color", damageSource);
            return 0xffffffff;
        }
        if (damageColorCache.containsKey(identifier.toString())) {
            return damageColorCache.get(identifier.toString());
        } else {
            ResourceKey<DamageColorRegistryEntry> COLOR = ResourceKey.create(ModDynamicRegistry.DAMAGE_COLOR, identifier);
            Optional<Holder.Reference<DamageColorRegistryEntry>> entry = level.registryAccess().get(COLOR);
            if (entry.isPresent()) {
                damageColorCache.put(identifier.toString(), entry.get().value().color());
                return damageColorCache.get(identifier.toString());
            } else {
                damageColorCache.put(identifier.toString(), 0xffffffff);
                ShowMeYourDamage.LOGGER.warn("Can't get color for damage type: {}, using white as default color", damageSource);
                return 0xffffffff;
            }
        }
    }

    public static void reload() {
        damageColorCache.clear();
    }
}
