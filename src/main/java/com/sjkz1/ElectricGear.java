package com.sjkz1;

import com.sjkz1.block.ModBlock;
import com.sjkz1.command.GiveAllTrimToolsCommand;
import com.sjkz1.effect.ElectricShockEffect;
import com.sjkz1.enchant.ModEnchants;
import com.sjkz1.item.ModItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ElectricGear implements ModInitializer {

    public static final String MOD_ID = "electric-gear";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final StatusEffect ELECTRIC_SHOCKING;

    public static final RegistryKey<DamageType> ELECTRIC_SHOCK;

    static {
        ELECTRIC_SHOCK = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "electric_shock"));
        ELECTRIC_SHOCKING = Registry.register(Registries.STATUS_EFFECT, Identifier.of(ElectricGear.MOD_ID, "electric_shock"), new ElectricShockEffect());
    }

    public static final List<Item> ITEM = List.of(
            Items.WOODEN_AXE,
            Items.STONE_AXE,
            Items.IRON_AXE,
            Items.GOLDEN_AXE,
            Items.DIAMOND_AXE,
            Items.NETHERITE_AXE,
            Items.NETHERITE_SWORD,
            Items.STONE_SWORD
    );

    @Override
    public void onInitialize() {
        ModItem.init();
        ModBlock.init();
        ModEnchants.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> GiveAllTrimToolsCommand.register(dispatcher));
        LOGGER.info("Hello Fabric world!");
    }
}