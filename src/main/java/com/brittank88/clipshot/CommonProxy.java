package com.brittank88.clipshot;

import java.awt.HeadlessException;
import java.awt.Toolkit;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc., and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {

        if (event.getSide()
            .isServer())
            ClipShot.LOG.warn("ClipShot will do nothing on a dedicated server as it is a client-only mod!");
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        ClipShot.LOG.info("Getting system clipboard via AWT...");

        try {
            Toolkit.getDefaultToolkit()
                .getSystemClipboard();
        } catch (HeadlessException e) {
            throw ClipShot.LOG.throwing(
                new RuntimeException(
                    "Failed to get system clipboard via AWT. The java.awt.headless property was not set properly: ",
                    e));
        }

        ClipShot.LOG.info("ClipShot " + Tags.VERSION + " loaded!");
    }
}
