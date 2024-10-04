package com.sjkz1.item.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class CopperArmorMaterials implements ArmorMaterial {
    @Override
    public int getDurability(ArmorItem.Type type) {
        return 15;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return type.equals(ArmorItem.Type.BOOTS) ? 1 : type.equals(ArmorItem.Type.LEGGINGS) ? 4 : type.equals(ArmorItem.Type.CHESTPLATE) ? 5 : type.equals(ArmorItem.Type.HELMET) ? 2 : 1;
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
