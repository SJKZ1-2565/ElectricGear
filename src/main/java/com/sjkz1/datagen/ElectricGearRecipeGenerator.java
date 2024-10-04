package com.sjkz1.datagen;

import com.sjkz1.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class ElectricGearRecipeGenerator extends FabricRecipeProvider {
    public ElectricGearRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.COPPER_SWORD).pattern("c").pattern("c").pattern("s")
                .input('c', Items.COPPER_INGOT).input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.COPPER_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.COPPER_HOE).pattern("cc").pattern("s ").pattern("s ")
                .input('c', Items.COPPER_INGOT).input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.COPPER_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.COPPER_SHOVEL).pattern("c").pattern("s").pattern("s")
                .input('c', Items.COPPER_INGOT).input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.COPPER_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.COPPER_PICKAXE).pattern("ccc").pattern(" s ").pattern(" s ")
                .input('c', Items.COPPER_INGOT).input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.COPPER_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItem.COPPER_AXE).pattern("cc").pattern("cs").pattern(" s")
                .input('c', Items.COPPER_INGOT).input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.COPPER_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
    }
}
