package com.sjkz1.mixin.client;

import com.sjkz1.ElectricGear;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {


    @Unique
    @Final
    private static final SpriteIdentifier ELECTRIC_0 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.of(ElectricGear.MOD_ID, "block/electric_0"));


    @Unique
    @Final
    private static final SpriteIdentifier ELECTRIC_1 = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier.of(ElectricGear.MOD_ID, "block/electric_1"));

    @Shadow
    public Camera camera;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V", ordinal = 1))
    public <E extends Entity> void render(E entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.hasStatusEffect(ElectricGear.ELECTRIC_SHOCKING)) {
                renderFire(matrices, vertexConsumers, livingEntity);
            }
        }
    }

    @Unique
    private void renderFire(MatrixStack matrices, VertexConsumerProvider vertexConsumers, Entity entity) {
        Sprite sprite = ELECTRIC_0.getSprite();
        Sprite sprite2 = ELECTRIC_1.getSprite();
        matrices.push();
        float f = entity.getWidth() * 1.4F;
        matrices.scale(f, f, f);
        float g = 0.5F;
        float h = 0.0F;
        float i = entity.getHeight() / f;
        float j = 0.0F;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-this.camera.getYaw()));
        matrices.translate(0.0F, 0.0F, -0.3F + (float) ((int) i) * 0.02F);
        float k = 0.0F;
        int l = 0;
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(TexturedRenderLayers.getEntityCutout());

        for (MatrixStack.Entry entry = matrices.peek(); i > 0.0F; l++) {
            Sprite sprite3 = l % 2 == 0 ? sprite : sprite2;
            float m = sprite3.getMinU();
            float n = sprite3.getMinV();
            float o = sprite3.getMaxU();
            float p = sprite3.getMaxV();
            if (l / 2 % 2 == 0) {
                float q = o;
                o = m;
                m = q;
            }

            drawFireVertex(entry, vertexConsumer, g - 0.0F, 0.0F - j, k, o, p, entity.age);
            drawFireVertex(entry, vertexConsumer, -g - 0.0F, 0.0F - j, k, m, p, entity.age);
            drawFireVertex(entry, vertexConsumer, -g - 0.0F, 1.4F - j, k, m, n, entity.age);
            drawFireVertex(entry, vertexConsumer, g - 0.0F, 1.4F - j, k, o, n, entity.age);
            i -= 0.45F;
            j -= 0.45F;
            g *= 0.9F;
            k += 0.03F;
        }

        matrices.pop();
    }

    private static void drawFireVertex(MatrixStack.Entry entry, VertexConsumer vertices, float x, float y, float z, float u, float v, float alpha) {
        vertices.vertex(entry.getPositionMatrix(), x, y, z)
                .color(255, 255, 255, 255)
                        .texture(u, v)
                        .overlay(0, 10)
                        .light(LightmapTextureManager.MAX_BLOCK_LIGHT_COORDINATE)
                        .normal(entry.getNormalMatrix(), 0.0F, 1.0F, 0.0F)
                        .next();
    }
}
