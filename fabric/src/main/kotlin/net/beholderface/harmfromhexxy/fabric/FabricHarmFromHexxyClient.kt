package net.beholderface.harmfromhexxy.fabric

import net.beholderface.harmfromhexxy.HarmFromHexxyClient
import net.fabricmc.api.ClientModInitializer

object FabricHarmFromHexxyClient : ClientModInitializer {
    override fun onInitializeClient() {
        HarmFromHexxyClient.init()
    }
}
