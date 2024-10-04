package com.sjkz1.datagen;

import com.sjkz1.enchant.ModEnchants;
import com.sjkz1.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ElectricGearLanguageGenerator extends FabricLanguageProvider {
    protected ElectricGearLanguageGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItem.COPPER_SWORD, "Copper Sword");
        translationBuilder.add(ModItem.COPPER_SHOVEL, "Copper Shovel");
        translationBuilder.add(ModItem.COPPER_AXE, "Copper Axe");
        translationBuilder.add(ModItem.COPPER_HOE, "Copper Hoe");
        translationBuilder.add(ModItem.COPPER_PICKAXE, "Copper Pickaxe");
        translationBuilder.add(ModItem.COPPER_HELMET, "Copper Helmet");
        translationBuilder.add(ModItem.COPPER_CHESTPLATE, "Copper Chestplate");
        translationBuilder.add(ModItem.COPPER_LEGGINGS, "Copper Leggings");
        translationBuilder.add(ModItem.COPPER_BOOTS, "Copper Boots");
        translationBuilder.add(ModEnchants.VOLTAGE, "Voltage");
    }

    static class ElectricGearThaiLanguageGenerator extends FabricLanguageProvider {
        protected ElectricGearThaiLanguageGenerator(FabricDataOutput dataOutput) {
            super(dataOutput, "th_th");
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add(ModItem.COPPER_SWORD, "ดาบทองแดง");
            translationBuilder.add(ModItem.COPPER_SHOVEL, "พลั่วทองแดง");
            translationBuilder.add(ModItem.COPPER_AXE, "ขวานทองแดง");
            translationBuilder.add(ModItem.COPPER_HOE, "จอบทองแดง");
            translationBuilder.add(ModItem.COPPER_PICKAXE, "อีเต้อทองแดง");
            translationBuilder.add(ModItem.COPPER_HELMET, "หมวกทองแดง");
            translationBuilder.add(ModItem.COPPER_CHESTPLATE, "เสื้อเกราะทองแดง");
            translationBuilder.add(ModItem.COPPER_LEGGINGS, "กางเกงทองแดง");
            translationBuilder.add(ModItem.COPPER_BOOTS, "รองเท้าทองแดง");
            translationBuilder.add(ModEnchants.VOLTAGE, "แรงดันไฟฟ้า");
        }
    }
}
