package com.brittank88.clipshot;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    /**
     * Run before anything else.
     * <p>
     * Read your config, create blocks, items, etc., and register them with the GameRegistry.
     *
     * @param event The pre-initialisation event.
     */
    public void preInit(FMLPreInitializationEvent event) {

        // Warn about being useless on a dedicated server.
        if (event.getSide()
            .isServer())
            ClipShot.LOG.warn("ClipShot will do nothing on a dedicated server as it is a client-only mod!");
    }

    /**
     * Do your mod setup.
     * <p>
     * Build whatever data structures you care about. Register recipes.
     *
     * @param event The initialisation event.
     */
    public void init(@SuppressWarnings("unused") FMLInitializationEvent event) {}
}
