package com.damage.showme.network;

import com.damage.showme.ShowMeYourDamage;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;

public record DamageTextS2CPayload(Vec3 position, String damage, int color) implements CustomPacketPayload {
    public static final Identifier DAMAGE_NUMBER_PAYLOAD_ID = Identifier.fromNamespaceAndPath("smyd", "dt");

    public static final CustomPacketPayload.Type<DamageTextS2CPayload> TYPE = new CustomPacketPayload.Type<>(DAMAGE_NUMBER_PAYLOAD_ID);

    public static final StreamCodec<RegistryFriendlyByteBuf, DamageTextS2CPayload> CODEC = StreamCodec.composite(
            Vec3.STREAM_CODEC,
            DamageTextS2CPayload::position,
            ByteBufCodecs.STRING_UTF8,
            DamageTextS2CPayload::damage,
            ByteBufCodecs.VAR_INT,
            DamageTextS2CPayload::color,
            DamageTextS2CPayload::new
    );
    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
