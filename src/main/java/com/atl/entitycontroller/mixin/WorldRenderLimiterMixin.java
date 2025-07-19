package com.atl.entitycontroller.mixin;

import com.atl.entitycontroller.util.EntityRenderLimiter;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRenderLimiterMixin {

    @Inject(method = "renderEntity", at = @At("HEAD"), cancellable = true)
    private void limitRender(Entity entity, double x, double y, double z, float yaw, float tickDelta,
                             MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        Identifier id = EntityType.getId(entity.getType());

        if (!EntityRenderLimiter.canRender(entity.getType().getClass(), id)) {
            System.out.println("[Limiter] Skipping render for: " + id);
            ci.cancel();
        }
    }
}