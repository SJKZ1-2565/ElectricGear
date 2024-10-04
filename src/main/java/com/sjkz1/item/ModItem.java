package com.sjkz1.item;

import com.sjkz1.ElectricGear;
import com.sjkz1.item.armor.CopperArmorMaterials;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItem {

    public static final CopperMaterials COPPER = new CopperMaterials();
    public static final CopperArmorMaterials COPPER_ARMOR_MATERIALS = new CopperArmorMaterials();
    public static final SwordItem COPPER_SWORD = new SwordItem(COPPER, 4, -2.4F, new Item.Settings());
    public static final ShovelItem COPPER_SHOVEL = new ShovelItem(COPPER, 1.5F, -3.0F, new Item.Settings());
    public static final PickaxeItem COPPER_PICKAXE = new PickaxeItem(COPPER, 1, -2.8F, new Item.Settings());
    public static final AxeItem COPPER_AXE = new AxeItem(COPPER, 5, -3.1F, new Item.Settings());
    public static final HoeItem COPPER_HOE = new HoeItem(COPPER, 0, -1.0F, new Item.Settings());
    public static final Item COPPER_HELMET = new ArmorItem(COPPER_ARMOR_MATERIALS, ArmorItem.Type.HELMET, new Item.Settings());
    public static final Item COPPER_CHESTPLATE = new ArmorItem(COPPER_ARMOR_MATERIALS, ArmorItem.Type.CHESTPLATE, new Item.Settings());
    public static final Item COPPER_LEGGINGS = new ArmorItem(COPPER_ARMOR_MATERIALS, ArmorItem.Type.LEGGINGS, new Item.Settings());
    public static final Item COPPER_BOOTS = new ArmorItem(COPPER_ARMOR_MATERIALS, ArmorItem.Type.BOOTS, new Item.Settings());

    public static void init() {
        registerItem(COPPER_SWORD, "copper_sword");
        registerItem(COPPER_SHOVEL, "copper_shovel");
        registerItem(COPPER_PICKAXE, "copper_pickaxe");
        registerItem(COPPER_AXE, "copper_axe");
        registerItem(COPPER_HOE, "copper_hoe");
        registerItem(COPPER_HELMET, "copper_helmet");
        registerItem(COPPER_CHESTPLATE, "copper_chestplate");
        registerItem(COPPER_LEGGINGS, "copper_leggings");
        registerItem(COPPER_BOOTS, "copper_boots");
    }

    static void registerItem(Item item, String name) {
        Registry.register(Registries.ITEM, Identifier.of(ElectricGear.MOD_ID, name), item);
    }
}
