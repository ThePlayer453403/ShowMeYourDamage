package com.damage.showme.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record DamageColorRegistryEntry(int color) {
    public static final Codec<DamageColorRegistryEntry> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.INT.fieldOf("color").forGetter(DamageColorRegistryEntry::color)).apply(instance, DamageColorRegistryEntry::new)
    );
}
