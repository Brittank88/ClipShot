package com.brittank88.clipshot;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static Boolean saveFile = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        saveFile = configuration.getBoolean(
            "saveFile",
            Configuration.CATEGORY_GENERAL,
            saveFile,
            "Should the screenshot still be saved as a file?");

        if (configuration.hasChanged()) configuration.save();
    }
}
