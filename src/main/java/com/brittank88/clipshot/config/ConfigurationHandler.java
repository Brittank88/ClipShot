package com.brittank88.clipshot.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.brittank88.clipshot.ClipShot;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

    public static Configuration configuration;

    // Initially acts as the default value, but will be updated from the configuration file.
    public static Boolean saveScreenshotsAsFiles = true;

    public static void init(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        saveScreenshotsAsFiles = configuration.getBoolean(
            "Save Screenshots as Files",
            Configuration.CATEGORY_GENERAL,
            saveScreenshotsAsFiles,
            "Determines if screenshots are also saved as files, or ONLY copied to the clipboard.");

        if (configuration.hasChanged()) configuration.save();
    }

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(ClipShot.MOD_ID)) loadConfiguration();
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
}
