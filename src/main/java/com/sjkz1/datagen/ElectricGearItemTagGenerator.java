package com.sjkz1.datagen;

import com.sjkz1.ElectricGear;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ElectricGearItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public ElectricGearItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        for (Item item : ElectricGear.ITEM) {
            this.getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(item);
        }
    }
}
