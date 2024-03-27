package com.brittank88.clipshot;

import java.awt.HeadlessException;
import java.awt.Toolkit;

import com.brittank88.clipshot.config.ConfigurationHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Override CommonProxy methods here, if you want a different behaviour on the client (e.g. registering renders).
 * <p>
 * Don't forget to call the super methods as well.
 *
 * @see CommonProxy
 */
@SuppressWarnings("unused")
public class ClientProxy extends CommonProxy {

    /**
     * {@inheritDoc}
     *
     * @param event The pre-initialisation event.
     */
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        // Initialise the configuration file.
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance()
            .bus()
            .register(new ConfigurationHandler());
    }

    /**
     * {@inheritDoc}
     *
     * @param event The initialisation event.
     */
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        ClipShot.LOG.info("Getting system clipboard via AWT...");

        try {
            Toolkit.getDefaultToolkit()
                .getSystemClipboard();
        } catch (HeadlessException e) {
            throw ClipShot.LOG.throwing(
                new RuntimeException(
                    "Failed to get system clipboard; java.awt.headless property was not set properly: ",
                    e));
        }

        ClipShot.LOG.info("ClipShot " + Tags.VERSION + " loaded!");
    }
}
