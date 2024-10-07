package com.sjkz1.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.decoration.ArmorStandEntity;
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
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class GiveAllTrimToolsCommand {
    private static final List<Item> ITEM = List.of(
            Items.WOODEN_AXE,
            Items.STONE_AXE,
            Items.IRON_AXE,
            Items.GOLDEN_AXE,
            Items.DIAMOND_AXE,
            Items.NETHERITE_AXE,
            Items.NETHERITE_SWORD,
            Items.STONE_SWORD
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

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("gal")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(context -> execute(context.getSource(), context.getSource().getPlayerOrThrow()))
        );
    }

    private static int execute(ServerCommandSource source, ServerPlayerEntity playerOrThrow) {
        World world = playerOrThrow.getWorld();
        Registry<ArmorTrimPattern> registry = world.getRegistryManager().get(RegistryKeys.TRIM_PATTERN);
        Registry<ArmorTrimMaterial> registry2 = world.getRegistryManager().get(RegistryKeys.TRIM_MATERIAL);
        int j = 0;
        int k = 0;
        for (Item item : ITEM) {
            for (var material : MATERIALS) {
                ArmorTrim armorTrim1 = new ArmorTrim(registry2.getEntry(material).get(), registry.getEntry(ArmorTrimPatterns.SENTRY).get());
                if (item != null) {
                    ItemStack itemStack = new ItemStack(item);
                    ArmorTrim.apply(world.getRegistryManager(), itemStack, armorTrim1);
                    BlockPos blockPos = playerOrThrow.getBlockPos().offset(playerOrThrow.getHorizontalFacing(), 5);
                    double e = (double) blockPos.getX() + 0.5 - (double) (j % registry2.size()) * 3.0;
                    double f = (double) blockPos.getY() + 0.5 + (double) (k % MATERIALS.size()) * 3.0;
                    double g = (double) blockPos.getZ() + 0.5 + (double) (j / registry2.size() * 10);
                    ArmorStandEntity armorStandEntity = new ArmorStandEntity(world, e, f, g);
                    armorStandEntity.setStackInHand(Hand.MAIN_HAND, itemStack);
                    armorStandEntity.setNoGravity(true);
                    armorStandEntity.setShowArms(true);
                    world.spawnEntity(armorStandEntity);
                    k++;
                }
            }
            j++;
        }
        source.sendFeedback(() -> Text.literal("Tools with trimmed spawned around you"), true);
        return 1;
    }
}
