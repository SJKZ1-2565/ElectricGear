package com.sjkz1.datagen;

import com.sjkz1.block.ModBlock;
import com.sjkz1.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ElectricGearModelGenerator extends FabricModelProvider {
    public ElectricGearModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlock.COPPER_PIPELINE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItem.COPPER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItem.COPPER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItem.COPPER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.COPPER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.COPPER_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItem.COPPER_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItem.COPPER_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItem.COPPER_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItem.COPPER_BOOTS, Models.GENERATED);
    }
}
