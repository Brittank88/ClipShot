package com.brittank88.clipshot.mixins.client;

import java.awt.image.BufferedImage;
import java.io.File;

import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ScreenShotHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.brittank88.clipshot.ScreenshotHandler;
import com.brittank88.clipshot.config.ConfigurationHandler;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(ScreenShotHelper.class)
public abstract class ScreenShotHelperMixin {

    @Inject(
        method = "saveScreenshot(Ljava/io/File;Ljava/lang/String;IILnet/minecraft/client/shader/Framebuffer;)Lnet/minecraft/util/IChatComponent;",
        at = @At(
            value = "INVOKE",
            target = "Ljavax/imageio/ImageIO;write(Ljava/awt/image/RenderedImage;Ljava/lang/String;Ljava/io/File;)Z",
            remap = false),
        cancellable = true)
    private static void saveScreenshot(File p_148259_0_, String p_148259_1_, int p_148259_2_, int p_148259_3_,
        Framebuffer p_148259_4_, CallbackInfoReturnable<IChatComponent> cir, @Local BufferedImage bufferedImage) {

        ScreenshotHandler.handleScreenshot(bufferedImage);

        if (!ConfigurationHandler.saveScreenshotsAsFiles) {
            cir.setReturnValue(new ChatComponentTranslation("screenshot.copy_success"));
            cir.cancel();
        }
    }
}
