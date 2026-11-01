package com.damage.showme.network;

import com.damage.showme.ShowMeYourDamage;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public record DamageNumberS2CPayload(Vec3 position, int damage, int color) implements CustomPacketPayload {
    public static final Identifier DAMAGE_NUMBER_PAYLOAD_ID = ShowMeYourDamage.id("dm");

    public static final CustomPacketPayload.Type<DamageNumberS2CPayload> TYPE = new CustomPacketPayload.Type<>(DAMAGE_NUMBER_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, DamageNumberS2CPayload> CODEC = StreamCodec.composite(
            Vec3.STREAM_CODEC,
            DamageNumberS2CPayload::position,
            ByteBufCodecs.VAR_INT,
            DamageNumberS2CPayload::damage,
            ByteBufCodecs.VAR_INT,
            DamageNumberS2CPayload::color,
            DamageNumberS2CPayload::new
    );
    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
