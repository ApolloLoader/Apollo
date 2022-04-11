package xyz.unifycraft.apollo

import me.kbrewster.eventbus.eventbus
import me.kbrewster.eventbus.invokers.LMFInvoker
import org.apache.logging.log4j.LogManager
import xyz.unifycraft.apollo.loader.Mod

class Apollo : Mod {
    override fun preInitialize() {
    }

    companion object {
        const val NAME = "@NAME@"
        const val VERSION = "@VERSION@"
        const val ID = "@ID@"

        @JvmStatic val logger = LogManager.getLogger(NAME)
        private val debugLogger = LogManager.getLogger("$NAME Debug")
        @JvmStatic val eventBus = eventbus {
            invoker { LMFInvoker() }
            exceptionHandler { e ->
                logger.error("Unhandled exception", e)
            }
        }

        @JvmStatic fun debug(message: String) {
            if (System.getProperty("apollo.debug", "false").equals("true", true)) {
                debugLogger.info(message)
            }
        }
    }
}