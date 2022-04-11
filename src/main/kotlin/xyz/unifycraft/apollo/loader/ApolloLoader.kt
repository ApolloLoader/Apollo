package xyz.unifycraft.apollo.loader

import com.google.gson.GsonBuilder
import net.minecraft.launchwrapper.Launch
import java.io.File
import java.io.FilenameFilter

class ApolloLoader internal constructor(
) {
    private val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()
    private val classLoader = ModClassLoader(Launch.classLoader)

    private val mods = mutableListOf<ModConfiguration>()

    fun load() {
        modsFolder.listFiles(fileFilter)?.forEach(classLoader::addFile)
        val resources = classLoader.getResources("mod.apollo.json")
        while (resources.hasMoreElements()) {
            val next = resources.nextElement()
            val text = next.openStream().bufferedReader().readText()
            mods.add(gson.fromJson(text, ModConfiguration::class.java))
        }
    }

    fun preInitialize() {
        mods.forEach {
            it.entrypointInstance.preInitialize()
        }
    }

    fun initialize() {
        mods.forEach {
            it.entrypointInstance.initialize()
        }
    }

    fun postInitialize() {
        mods.forEach {
            it.entrypointInstance.postInitialize()
        }
    }

    companion object {
        @JvmStatic val instance = ApolloLoader()
        @JvmStatic val fileFilter = FilenameFilter { _, name -> name.endsWith(".jar") }
        @JvmStatic val modsFolder by lazy {
            val file = File(Launch.minecraftHome, "mods")
            if (!file.exists())
                file.mkdirs()
            file
        }

        @JvmStatic val mods: List<ModConfiguration>
            get() = instance.mods.toList()
    }
}