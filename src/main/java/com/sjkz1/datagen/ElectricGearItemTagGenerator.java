package com.sjkz1.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ElectricGearItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public ElectricGearItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        this.getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Items.IRON_AXE);
        this.getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Items.DIAMOND_AXE);
        this.getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Items.WOODEN_AXE);
        this.getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Items.STONE_AXE);
        this.getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Items.GOLDEN_AXE);
        this.getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Items.NETHERITE_AXE);
        this.getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Items.NETHERITE_SWORD);
        this.getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Items.STONE_SWORD);
        this.getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR).add(Items.GOLDEN_SWORD);
    }
}
