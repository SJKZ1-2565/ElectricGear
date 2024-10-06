package com.sjkz1.item.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CopperArmorMaterials implements ArmorMaterial {

    public static final CopperArmorMaterials INSTANCE = new CopperArmorMaterials();

    @Override
    public int getDurability(ArmorItem.Type type) {
        int DURABILITY_MULTIPLIER = 12;
        return switch (type) {
            case BOOTS -> 13 * DURABILITY_MULTIPLIER;
            case LEGGINGS -> 15 * DURABILITY_MULTIPLIER;
            case CHESTPLATE -> 16 * DURABILITY_MULTIPLIER;
            case HELMET -> 11 * DURABILITY_MULTIPLIER;
        };
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS, HELMET -> 3;
            case LEGGINGS -> 6;
            case CHESTPLATE -> 8;
        };
    }

    @Override
    public int getEnchantability() {
        return 12;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.IRON_INGOT);
    }

    @Override
    public String getName() {
        return "copper";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
