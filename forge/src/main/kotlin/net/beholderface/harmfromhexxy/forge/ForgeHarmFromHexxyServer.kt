package net.beholderface.harmfromhexxy.forge

import net.beholderface.harmfromhexxy.HarmFromHexxy
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent

object ForgeHarmFromHexxyServer {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLDedicatedServerSetupEvent) {
        HarmFromHexxy.initServer()
    }
}
