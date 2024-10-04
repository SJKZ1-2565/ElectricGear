package com.sjkz1.item;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CopperMaterials implements ToolMaterial {
    @Override
    public int getDurability() {
        return 200;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 7;
    }

    @Override
    public float getAttackDamage() {
        return 1;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.COPPER_INGOT);
    }
}
