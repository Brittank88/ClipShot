package com.brittank88.clipshot.client.gui;

import static com.brittank88.clipshot.config.ConfigurationHandler.*;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import com.brittank88.clipshot.ClipShot;

public class GuiConfig extends cpw.mods.fml.client.config.GuiConfig {

    public GuiConfig(GuiScreen guiScreen) {
        super(
            guiScreen,
            new ConfigElement<>(getConfiguration().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
            ClipShot.MOD_ID,
            false,
            false,
            GuiConfig.getAbridgedConfigPath(getConfiguration().toString()));
    }
}
