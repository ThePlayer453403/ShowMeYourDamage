package com.damage.showme.data;

import com.damage.showme.ShowMeYourDamage;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class ModDynamicRegistry {
    public static final ResourceKey<Registry<DamageColorRegistryEntry>> DAMAGE_COLOR = ResourceKey.createRegistryKey(ShowMeYourDamage.id("damage_color"));

    public static void register() {
        DynamicRegistries.register(DAMAGE_COLOR, DamageColorRegistryEntry.CODEC);
    }
}
