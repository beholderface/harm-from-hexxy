package net.beholderface.harmfromhexxy

import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import net.beholderface.harmfromhexxy.config.HarmFromHexxyServerConfig
import net.beholderface.harmfromhexxy.envstuff.CastEnvComponents
import net.beholderface.harmfromhexxy.networking.HarmFromHexxyNetworking
import net.beholderface.harmfromhexxy.registry.HarmFromHexxyActions

object HarmFromHexxy {
    const val MODID = "harm_from_hexxy"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        HarmFromHexxyServerConfig.init()
        initRegistries(
            HarmFromHexxyActions,
        )
        HarmFromHexxyItemRegistry.init()
        HarmFromHexxyNetworking.init()
        CastEnvComponents.init()
        LOGGER.info("Hexxy watches.")
    }

    fun initServer() {
        HarmFromHexxyServerConfig.initServer()
    }
}
