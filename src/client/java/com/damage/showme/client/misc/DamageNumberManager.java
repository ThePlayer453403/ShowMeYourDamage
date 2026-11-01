package com.damage.showme.client.misc;

import com.damage.showme.client.ShowMeYourDamageClient;
import com.damage.showme.network.DamageNumberS2CPayload;
import com.damage.showme.network.DamageTextS2CPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class DamageNumberManager {
    public static List<DamageNumberInstance> damageNumberInstances = new ArrayList<>();

    public static class DamageNumberInstance {
        public float x;
        public float y;
        public float z;
        public Component damage;
        public int lifetime;
        public float width;

        public DamageNumberInstance(Vec3 position, Component damage) {
            this.x = (float) position.x;
            this.y = (float) position.y;
            this.z = (float) position.z;
            this.damage = damage;
            this.lifetime = 200;
            this.width = Minecraft.getInstance().font.width(damage);
        }
    }

    public static void addDamageNumberInstance(DamageNumberS2CPayload payload) {
        damageNumberInstances.add(new DamageNumberInstance(payload.position(), Component.literal(String.valueOf(payload.damage())).setStyle(Style.EMPTY.withBold(!ShowMeYourDamageClient.installedModernUI)).withColor(payload.color())));
    }

    public static void addDamageNumberInstance(DamageTextS2CPayload payload) {
        damageNumberInstances.add(new DamageNumberInstance(payload.position(), Component.literal(payload.damage()).setStyle(Style.EMPTY.withBold(!ShowMeYourDamageClient.installedModernUI)).withColor(payload.color())));
    }
}
