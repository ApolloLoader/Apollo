package xyz.unifycraft.apollo.loader

import net.minecraft.launchwrapper.LaunchClassLoader
import xyz.unifycraft.apollo.Apollo
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Path

class ModClassLoader(
    val parentClassLoader: LaunchClassLoader
) : URLClassLoader(
    arrayOf<URL>(),
    parentClassLoader
) {
    private val sources = mutableListOf<File>()

    fun addFile(file: File) {
        val url = file.toURI().toURL()
        parentClassLoader.addURL(url)
        sources.add(file)
    }

    fun fetchParentSources(): Array<File> {
        return try {
            val files = mutableListOf<File>()
            parentClassLoader.sources.filter { url ->
                url.toURI().scheme == "file"
            }.forEach { url ->
                files.add(File(url.toURI()))
            }
            files.toTypedArray()
        } catch (e: Exception) {
            Apollo.logger.error("Failed to fetch parent sources.", e)
            arrayOf()
        }
    }

    fun getSources() = sources
    override fun loadClass(name: String?) = parentClassLoader.loadClass(name)
}