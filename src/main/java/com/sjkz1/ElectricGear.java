package com.sjkz1;

import com.sjkz1.enchant.ModEnchants;
import com.sjkz1.item.ModItem;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElectricGear implements ModInitializer {
    public static final String MOD_ID = "electric-gear";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItem.init();
        ModEnchants.init();
        LOGGER.info("Hello Fabric world!");
    }
}