package com.sjkz1.effect;

import com.sjkz1.ElectricGear;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.RegistryKeys;

public class ElectricShockEffect extends StatusEffect {
    public ElectricShockEffect() {
        super(StatusEffectCategory.HARMFUL, 0xffff00);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        DamageSource damageSource = new DamageSource(
                entity.getWorld().getRegistryManager()
                        .get(RegistryKeys.DAMAGE_TYPE)
                        .entryOf(ElectricGear.ELECTRIC_SHOCK));
        if (!entity.getWorld().isClient && entity.age % 10 == 0) {
            entity.damage(damageSource, 0.25F);
        }
        super.applyUpdateEffect(entity, amplifier);
    }
}
