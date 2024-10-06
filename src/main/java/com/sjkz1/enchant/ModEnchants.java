package com.sjkz1.enchant;

import com.sjkz1.ElectricGear;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchants {

    public static Enchantment VOLTAGE = new VoltageEnchantment();

    public static void init() {
        registerEnchant(VOLTAGE, "voltage");
    }

    static void registerEnchant(Enchantment enchantment, String name) {
        Registry.register(Registries.ENCHANTMENT, Identifier.of(ElectricGear.MOD_ID, name), enchantment);
    }

    public static int getVoltage(LivingEntity entity) {
        return EnchantmentHelper.getEquipmentLevel(VOLTAGE, entity);
    }
}
