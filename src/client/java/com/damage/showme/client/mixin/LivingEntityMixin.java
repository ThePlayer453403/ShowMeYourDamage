package com.damage.showme.client.mixin;

import com.damage.showme.client.data.ModDataAttachment;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity.getHealth() < entity.getAttachedOrElse(ModDataAttachment.GRADIENT, 0f)) {
            entity.setAttached(ModDataAttachment.GRADIENT, entity.getAttachedOrElse(ModDataAttachment.GRADIENT, 0f) - entity.getMaxHealth() * 0.03f);
        }
        if (entity.getHealth() > entity.getAttachedOrElse(ModDataAttachment.GRADIENT, 0f)) {
            entity.setAttached(ModDataAttachment.GRADIENT, entity.getHealth());
        }
    }
}
