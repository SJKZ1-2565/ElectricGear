package com.sjkz1.block;

import com.sjkz1.ElectricGear;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlock {
    public static final Block COPPER_PIPELINE = new CopperPipeline(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).requiresTool().strength(3.0F, 4.8F).sounds(BlockSoundGroup.METAL).nonOpaque());
    public static final BlockEntityType<CopperPipelineBlockEntity> COPPER_PIPELINE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(ElectricGear.MOD_ID, "copper_pipeline"), FabricBlockEntityTypeBuilder.create(CopperPipelineBlockEntity::new, ModBlock.COPPER_PIPELINE).build());

    public static void init() {
        registerBlock(COPPER_PIPELINE, "copper_pipeline");
        registerBlockItem(COPPER_PIPELINE, "copper_pipeline");
    }

    static void registerBlock(Block block, String string) {
        Registry.register(Registries.BLOCK, Identifier.of(ElectricGear.MOD_ID, string), block);
    }

    static void registerBlockItem(Block block, String string) {
        Registry.register(Registries.ITEM, Identifier.of(ElectricGear.MOD_ID, string), new BlockItem(block, new Item.Settings()));
    }
}
