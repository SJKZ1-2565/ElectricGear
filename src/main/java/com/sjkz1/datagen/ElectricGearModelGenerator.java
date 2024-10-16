package com.sjkz1.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sjkz1.ElectricGear;
import com.sjkz1.block.ModBlock;
import com.sjkz1.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public class ElectricGearModelGenerator extends FabricModelProvider {

    public ElectricGearModelGenerator(FabricDataOutput output) {
        super(output);
    }

    public static final Model HANDHELD_TWO_LAYERS = item("handheld", TextureKey.LAYER0, TextureKey.LAYER1);

    private static Model item(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier("minecraft", "item/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerCopperPipeline(blockStateModelGenerator);
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
        for (Item item : ElectricGear.ITEM) {
            registerCustomToolTrim(itemModelGenerator, item);
        }
    }

    public void registerCustomToolTrim(ItemModelGenerator itemModelGenerator, Item item) {
        Identifier identifier = ModelIds.getItemModelId(item);
        Identifier identifier2 = TextureMap.getId(item);
        Models.HANDHELD.upload(identifier, TextureMap.layer0(identifier2), itemModelGenerator.writer, (id, textures) -> this.createItemJson(id, textures, item));
        for (ItemModelGenerator.TrimMaterial material : ItemModelGenerator.TRIM_MATERIALS) {
            String itemString = getString(item);
            Identifier identifier4 = this.suffixTrim(Identifier.of(ElectricGear.MOD_ID, identifier.getPath()), material.name());
            String darkerString = "";
            if (item.toString().contains(material.name())) {
                darkerString = "_darker";
            }
            String string2 = itemString + "_trim_" + material.name() + darkerString;
            Identifier identifier5 = Identifier.of(ElectricGear.MOD_ID, string2).withPrefixedPath("trims/items/");
            HANDHELD_TWO_LAYERS.upload(identifier4, TextureMap.layered(identifier2, identifier5), itemModelGenerator.writer);
        }
    }

    private static @NotNull String getString(Item item) {
        String itemString = "";
        if (item instanceof SwordItem) {
            itemString = "sword";
        }
        if (item instanceof AxeItem) {
            itemString = "axe";
        }
        if (item instanceof PickaxeItem) {
            itemString = "pickaxe";
        }
        if (item instanceof HoeItem) {
            itemString = "hoe";
        }
        if (item instanceof ShovelItem) {
            itemString = "shovel";
        }
        return itemString;
    }

    public final Identifier suffixTrim(Identifier id, String trimMaterialName) {
        return id.withSuffixedPath("_" + trimMaterialName + "_trim");
    }

    public final JsonObject createItemJson(Identifier id, Map<TextureKey, Identifier> textures, Item item) {
        JsonObject jsonObject = HANDHELD_TWO_LAYERS.createJson(id, textures);
        JsonArray jsonArray = new JsonArray();

        for (ItemModelGenerator.TrimMaterial trimMaterial : ItemModelGenerator.TRIM_MATERIALS) {
            JsonObject jsonObject2 = new JsonObject();
            JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty(ItemModelGenerator.TRIM_TYPE.getPath(), trimMaterial.itemModelIndex());
            jsonObject2.add("predicate", jsonObject3);
            jsonObject2.addProperty("model", this.suffixTrim(Identifier.of(ElectricGear.MOD_ID, "item/" + item.toString()), trimMaterial.name()).toString());
            jsonArray.add(jsonObject2);
        }

        jsonObject.add("overrides", jsonArray);
        return jsonObject;
    }

    private void registerCopperPipeline(BlockStateModelGenerator blockStateModelGenerator) {
        Block block = ModBlock.COPPER_PIPELINE;
        var modelId = ModelIds.getBlockModelId(block);
        var flatModelId = ModelIds.getBlockSubModelId(block, "_flat");
        var model = MultipartBlockStateSupplier.create(block)
                .with(When.create().set(Properties.FACING, Direction.UP).set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, modelId))
                .with(When.create().set(Properties.FACING, Direction.UP).set(Properties.UP, false), BlockStateVariant.create().put(VariantSettings.MODEL, flatModelId))
                .with(When.create().set(Properties.FACING, Direction.DOWN).set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.X, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.FACING, Direction.DOWN).set(Properties.UP, false), BlockStateVariant.create().put(VariantSettings.MODEL, flatModelId).put(VariantSettings.X, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.FACING, Direction.NORTH).set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.X, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.FACING, Direction.NORTH).set(Properties.UP, false), BlockStateVariant.create().put(VariantSettings.MODEL, flatModelId).put(VariantSettings.X, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.FACING, Direction.SOUTH).set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.FACING, Direction.SOUTH).set(Properties.UP, false), BlockStateVariant.create().put(VariantSettings.MODEL, flatModelId).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                .with(When.create().set(Properties.FACING, Direction.WEST).set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.FACING, Direction.WEST).set(Properties.UP, false), BlockStateVariant.create().put(VariantSettings.MODEL, flatModelId).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                .with(When.create().set(Properties.FACING, Direction.EAST).set(Properties.UP, true), BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with(When.create().set(Properties.FACING, Direction.EAST).set(Properties.UP, false), BlockStateVariant.create().put(VariantSettings.MODEL, flatModelId).put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90));
        blockStateModelGenerator.blockStateCollector.accept(model);
    }
}
