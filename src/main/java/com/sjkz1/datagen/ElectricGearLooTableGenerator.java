package com.sjkz1.datagen;

import com.sjkz1.block.ModBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ElectricGearLooTableGenerator extends FabricBlockLootTableProvider {
    protected ElectricGearLooTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        this.addDrop(ModBlock.COPPER_PIPELINE, this::nameableContainerDrops);
    }
}
