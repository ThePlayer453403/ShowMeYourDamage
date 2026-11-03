package com.damage.showme.client.render;

import com.damage.showme.client.data.ModDataAttachment;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class HealthBarRender {
    private static final float HEALTH_BAR_HEIGHT = 1.8f;
    private static final RenderStateDataKey<Float> MAX = RenderStateDataKey.create(() -> "showmeyourdamage_max");
    private static final RenderStateDataKey<Float> HEALTH = RenderStateDataKey.create(() -> "showmeyourdamage_health");
    private static final RenderStateDataKey<Float> GRADIENT = RenderStateDataKey.create(() -> "showmeyourdamage_gradient");

    public static void extractHealthBar(LivingEntity entity, LivingEntityRenderState state) {
        state.setData(MAX, entity.getMaxHealth());
        state.setData(HEALTH, entity.getHealth());
        state.setData(GRADIENT, entity.getAttachedOrElse(ModDataAttachment.GRADIENT, 0f));
    }

    public static void renderHealthBar(EntityRenderState state, PoseStack poseStack, SubmitNodeCollector collector) {
        Minecraft client = Minecraft.getInstance();

        if (client.player != null && client.player.position().distanceTo(new Vec3(state.x, state.y, state.z)) >= 32) {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0, state.boundingBoxHeight + 0.4, 0);
        if (client.player != null) {
            poseStack.rotateDegrees(Axis.YN, client.player.getYRot());
            poseStack.rotateDegrees(Axis.XP, client.player.getXRot());
        }

        poseStack.scale(-0.05f, -0.05f, -0.05f);

        float width = state.boundingBoxWidth * 25;
        float x = width / -2f;
        // 被玄学玩意吓哭了
        collector.submitTextBackground(poseStack, x - 0.1f, -0.1f, 0.1f - x, HEALTH_BAR_HEIGHT + 0.1f, 0xff3a222d, Font.DisplayMode.NORMAL, 0xffffff);
        poseStack.translate(0, 0, 0.001f);
        collector.submitTextBackground(poseStack, x, 0, width * (state.getDataOrDefault(GRADIENT, 0f) / state.getDataOrDefault(MAX, 1f)) + x, HEALTH_BAR_HEIGHT, 0xffffd17e, Font.DisplayMode.NORMAL, 0xf000f0);
        poseStack.translate(0, 0, 0.001f);
        collector.submitTextBackground(poseStack, x, 0, width * (state.getDataOrDefault(HEALTH, 0f) / state.getDataOrDefault(MAX, 1f)) + x, HEALTH_BAR_HEIGHT, 0xffff5a5a, Font.DisplayMode.NORMAL, 0xf000f0);
        poseStack.popPose();
    }
}
