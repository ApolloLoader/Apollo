package xyz.unifycraft.apollo.mixins

import net.minecraft.launchwrapper.ITweaker
import net.minecraft.launchwrapper.LaunchClassLoader
import org.spongepowered.asm.launch.MixinBootstrap
import org.spongepowered.asm.mixin.MixinEnvironment
import org.spongepowered.asm.mixin.Mixins
import java.io.File

class ApolloTweaker : ITweaker {
    private val args = mutableListOf<String>()

    override fun acceptOptions(args: MutableList<String>, gameDir: File?, assetsDir: File?, profile: String) {
        this.args.addAll(args)
        this.args.addArgIfNotPresent("--version", profile)
        if (assetsDir != null) this.args.addArgIfNotPresent("--assetsDir", assetsDir.absolutePath)
        if (gameDir != null) this.args.addArgIfNotPresent("--gameDir", gameDir.absolutePath)
    }

    override fun injectIntoClassLoader(classLoader: LaunchClassLoader) {
        MixinBootstrap.init()

        val environment = MixinEnvironment.getCurrentEnvironment()
        Mixins.addConfiguration("mixins.apollo.json")
        if (environment.obfuscationContext == null) environment.obfuscationContext = "notch"
        environment.side = MixinEnvironment.Side.CLIENT
    }

    override fun getLaunchTarget() = "net.minecraft.client.main.Main"
    override fun getLaunchArguments() = args.toTypedArray()

    private fun MutableList<String>.addArgIfNotPresent(arg: String, value: String) {
        if (!contains(arg)) {
            add(arg)
            add(value)
        }
    }
}