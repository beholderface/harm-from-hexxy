package net.beholderface.harmfromhexxy.fabric

import net.beholderface.harmfromhexxy.HarmFromHexxy
import net.fabricmc.api.ModInitializer

object FabricHarmFromHexxy : ModInitializer {
    override fun onInitialize() {
        HarmFromHexxy.init()
    }
}
