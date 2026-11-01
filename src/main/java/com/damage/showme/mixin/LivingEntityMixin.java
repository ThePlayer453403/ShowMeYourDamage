package com.damage.showme.mixin;

import com.damage.showme.misc.DamageNumber;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "hurtServer", at = @At("RETURN"))
    private void sendDamageNumberPayload(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {return;}
        LivingEntity self = (LivingEntity)(Object) this;

        Vec3 position = self.position();
        Vec3 numberPosition = new Vec3(position.x + Math.random() - 0.5, position.y + Math.random() + 1, position.z + Math.random() - 0.5);
        DamageNumber.send(level, numberPosition, damage, DamageNumber.getColor(level, source));
    }
}
