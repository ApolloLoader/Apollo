package xyz.unifycraft.apollo.mixins;

import com.google.common.base.Stopwatch;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.unifycraft.apollo.Apollo;
import xyz.unifycraft.apollo.loader.ApolloLoader;

import java.util.concurrent.TimeUnit;

@Mixin({Minecraft.class})
public class MinecraftMixin {

    @Inject(method = "startGame", at = @At("HEAD"))
    private void apollo$load(CallbackInfo ci) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Apollo.debug("Loading Apollo...");
        ApolloLoader.getInstance().load();
        Apollo.debug("Finished loading. (took: " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "ms)");
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setWindowIcon()V", shift = At.Shift.AFTER))
    private void apollo$preInitialize(CallbackInfo ci) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Apollo.debug("Pre-initializing Apollo...");
        ApolloLoader.getInstance().preInitialize();
        Apollo.debug("Finished pre-initialization. (took: " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "ms)");
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/IReloadableResourceManager;registerReloadListener(Lnet/minecraft/client/resources/IResourceManagerReloadListener;)V", ordinal = 0))
    private void apollo$initialize(CallbackInfo ci) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Apollo.debug("Initializing Apollo...");
        ApolloLoader.getInstance().initialize();
        Apollo.debug("Finished initialization. (took: " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "ms)");
    }

    @Inject(method = "startGame", at = @At("TAIL"))
    private void apollo$postInitialize(CallbackInfo ci) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Apollo.debug("Post-initializing Apollo...");
        ApolloLoader.getInstance().postInitialize();
        Apollo.debug("Finished post-initialization. (took: " + stopwatch.stop().elapsed(TimeUnit.MILLISECONDS) + "ms)");
    }

}