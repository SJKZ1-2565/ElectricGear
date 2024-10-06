package com.sjkz1.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ElectricGearDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ElectricGearRecipeGenerator::new);
        pack.addProvider(ElectricGearModelGenerator::new);
        pack.addProvider(ElectricGearLanguageGenerator::new);
        pack.addProvider(ElectricGearLanguageGenerator.ElectricGearThaiLanguageGenerator::new);
        pack.addProvider(ElectricGearLooTableGenerator::new);
        pack.addProvider(ElectricGearBlockTagGenerator::new);
    }

}
