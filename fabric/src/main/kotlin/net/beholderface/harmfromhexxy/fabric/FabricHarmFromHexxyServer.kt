package net.beholderface.harmfromhexxy.fabric

import net.beholderface.harmfromhexxy.HarmFromHexxy
import net.fabricmc.api.DedicatedServerModInitializer

object FabricHarmFromHexxyServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        HarmFromHexxy.initServer()
    }
}
