package com.sjkz1.enchant;

import com.sjkz1.ElectricGear;
import com.sjkz1.item.ModItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;

public class VoltageEnchantment extends Enchantment {
    protected VoltageEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isOf(ModItem.COPPER_SWORD);
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(ElectricGear.ELECTRIC_SHOCKING, 20 * 2 * level, level - 1, false, false));
        }
        super.onTargetDamaged(user, target, level);
    }
}
