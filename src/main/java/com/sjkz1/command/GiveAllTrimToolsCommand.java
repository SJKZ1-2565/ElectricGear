package com.sjkz1.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.trim.*;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class GiveAllTrimToolsCommand {
    private static final List<Item> ITEM = List.of(
            Items.WOODEN_AXE,
            Items.STONE_AXE,
            Items.IRON_AXE,
            Items.GOLDEN_AXE,
            Items.DIAMOND_AXE,
            Items.NETHERITE_AXE
    );
    private static final List<RegistryKey<ArmorTrimPattern>> PATTERNS = List.of(
            ArmorTrimPatterns.SENTRY
    );
    private static final List<RegistryKey<ArmorTrimMaterial>> MATERIALS = List.of(
            ArmorTrimMaterials.QUARTZ,
            ArmorTrimMaterials.IRON,
            ArmorTrimMaterials.NETHERITE,
            ArmorTrimMaterials.REDSTONE,
            ArmorTrimMaterials.COPPER,
            ArmorTrimMaterials.GOLD,
            ArmorTrimMaterials.EMERALD,
            ArmorTrimMaterials.DIAMOND,
            ArmorTrimMaterials.LAPIS,
            ArmorTrimMaterials.AMETHYST
    );
    private static final ToIntFunction<RegistryKey<ArmorTrimPattern>> PATTERN_INDEX_GETTER = Util.lastIndexGetter(PATTERNS);
    private static final ToIntFunction<RegistryKey<ArmorTrimMaterial>> MATERIAL_INDEX_GETTER = Util.lastIndexGetter(MATERIALS);

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(
                CommandManager.literal("gal")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(context -> execute(context.getSource(), context.getSource().getPlayerOrThrow()))
        );
    }

    private static int execute(ServerCommandSource source, ServerPlayerEntity playerOrThrow) {
        World world = playerOrThrow.getWorld();
        DefaultedList<ArmorTrim> defaultedList = DefaultedList.of();
        Registry<ArmorTrimPattern> registry = world.getRegistryManager().get(RegistryKeys.TRIM_PATTERN);
        Registry<ArmorTrimMaterial> registry2 = world.getRegistryManager().get(RegistryKeys.TRIM_MATERIAL);
        registry.stream()
                .sorted(Comparator.comparing(pattern -> PATTERN_INDEX_GETTER.applyAsInt((RegistryKey) registry.getKey(pattern).orElse(null))))
                .forEachOrdered(
                        pattern -> registry2.stream()
                                .sorted(Comparator.comparing(material -> MATERIAL_INDEX_GETTER.applyAsInt((RegistryKey) registry2.getKey(material).orElse(null))))
                                .forEachOrdered(material -> defaultedList.add(new ArmorTrim(registry2.getEntry(material), registry.getEntry(pattern))))
                );
        for (ArmorTrim armorTrim : defaultedList) {
            for (Item item : ITEM) {
                if (item != null) {
                    ItemStack itemStack = new ItemStack(item);
                    ArmorTrim.apply(world.getRegistryManager(), itemStack, armorTrim);
                    ItemEntity itemEntity = new ItemEntity(world, playerOrThrow.getX(), playerOrThrow.getY(), playerOrThrow.getZ(), itemStack);
                    itemEntity.setPickupDelay(40);
                    world.spawnEntity(itemEntity);
                }
            }
        }
        return 1;
    }
}
