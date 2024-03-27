package com.brittank88.clipshot;

import static com.brittank88.clipshot.ClipShot.MODID;
import static net.minecraft.client.Minecraft.isRunningOnMac;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;

import javax.annotation.Nonnull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ScreenshotHandler {

    public static final Logger LOG = LogManager.getLogger(MODID + ".DefaultHandler");

    public static void handleScreenshot(BufferedImage bufferedImage) {
        if (isRunningOnMac) {
            LOG.warn("Method handleScreenshotAWT called on a MacOS system! Skipping execution...");
            return;
        }

        LOG.debug("Copying screenshot to clipboard using AWT...");

        new Thread(
            () -> {
                Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new TransferableImage(bufferedImage), null);
            },
            "Screenshot to Clipboard Copy").start();
    }

    private static class TransferableImage implements Transferable {

        private final BufferedImage bufferedImage;

        public TransferableImage(BufferedImage bufferedImage) {
            this.bufferedImage = bufferedImage;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[] { DataFlavor.imageFlavor };
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return DataFlavor.imageFlavor.equals(flavor);
        }

        @Override
        @Nonnull
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (DataFlavor.imageFlavor.equals(flavor)) return bufferedImage;
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
