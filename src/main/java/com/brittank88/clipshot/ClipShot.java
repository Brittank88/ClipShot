package com.brittank88.clipshot;

import static com.brittank88.clipshot.ClipShot.GUI_FACTORY;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = ClipShot.MOD_ID,
    version = Tags.VERSION,
    name = "ClipShot",
    acceptedMinecraftVersions = "[1.7.10]",
    guiFactory = GUI_FACTORY)
public class ClipShot {

    public static final String GUI_FACTORY = "com.brittank88.clipshot.client.gui.GuiFactory";
    public static final String MOD_ID = "clipshot";
    public static final Logger LOG = LogManager.getLogger(MOD_ID);

    @SidedProxy(clientSide = "com.brittank88.clipshot.ClientProxy", serverSide = "com.brittank88.clipshot.CommonProxy")
    @SuppressWarnings("unused")
    public static CommonProxy proxy;

    /**
     * Run before anything else.
     * <p>
     * Read your config, create blocks, items, etc., and register them with the GameRegistry.
     *
     * @param event The pre-initialisation event.
     */
    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    /**
     * Do your mod setup.
     * <p>
     * Build whatever data structures you care about. Register recipes.
     *
     * @param event The initialisation event.
     */
    @Mod.EventHandler
    @SuppressWarnings("unused")
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
}
