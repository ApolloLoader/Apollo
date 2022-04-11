package xyz.unifycraft.apollo.loader

import net.minecraft.launchwrapper.Launch

class ModConfiguration {
    lateinit var name: String
    lateinit var version: String
    lateinit var id: String
    lateinit var description: String
    lateinit var author: String
    lateinit var url: String
    lateinit var entrypoint: String

    val entrypointInstance by lazy {
        val clazz = Class.forName(entrypoint, true, Launch.classLoader)
        if (clazz.interfaces.contains(Mod::class.java)) {
            clazz.getDeclaredConstructor().apply {
                isAccessible = true
            }.newInstance() as Mod
        } else {
            throw IllegalArgumentException("Entrypoint class must be a subclass of Mod")
        }
    }
}