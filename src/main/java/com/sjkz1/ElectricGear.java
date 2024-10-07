package com.sjkz1;

import com.sjkz1.block.ModBlock;
import com.sjkz1.command.GiveAllTrimToolsCommand;
import com.sjkz1.effect.ElectricShockEffect;
import com.sjkz1.enchant.ModEnchants;
import com.sjkz1.item.ModItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElectricGear implements ModInitializer {

    public static final String MOD_ID = "electric-gear";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    public static final StatusEffect ELECTRIC_SHOCKING;
    public static final RegistryKey<DamageType> ELECTRIC_SHOCK;


    static {
        ELECTRIC_SHOCK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "electric_shock"));
        ELECTRIC_SHOCKING = Registry.register(Registries.STATUS_EFFECT, Identifier.of(ElectricGear.MOD_ID, "electric_shock"), new ElectricShockEffect());
    }

    @Override
    public void onInitialize() {
        ModItem.init();
        ModBlock.init();
        ModEnchants.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> GiveAllTrimToolsCommand.register(dispatcher));
        LOGGER.info("Hello Fabric world!");
    }
}