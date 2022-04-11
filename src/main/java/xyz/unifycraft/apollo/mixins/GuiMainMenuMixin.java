package xyz.unifycraft.apollo.mixins;

import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.unifycraft.apollo.gui.hooks.MainMenuScreenHook;

@Mixin({GuiMainMenu.class})
public class GuiMainMenuMixin {
    @Unique private final MainMenuScreenHook apollo$hook = new MainMenuScreenHook();

    @Inject(method = "initGui", at = @At("HEAD"), cancellable = true)
    private void apollo$onGuiInitialized(CallbackInfo ci) {
        apollo$hook.onInitialize();
        ci.cancel();
    }

    @Inject(method = "drawScreen", at = @At("HEAD"), cancellable = true)
    private void apollo$onScreenDrawn(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        apollo$hook.onScreenDrawn();
        ci.cancel();
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void apollo$onMouseClicked(int mouseX, int mouseY, int mouseButton, CallbackInfo ci) {
        apollo$hook.onMouseClicked(mouseX, mouseY, mouseButton);
        ci.cancel();
    }

    @Inject(method = "keyTyped", at = @At("HEAD"), cancellable = true)
    private void apollo$onKeyTyped(char typedChar, int keyCode, CallbackInfo ci) {
        apollo$hook.onKeyTyped(typedChar, keyCode);
        ci.cancel();
    }
}