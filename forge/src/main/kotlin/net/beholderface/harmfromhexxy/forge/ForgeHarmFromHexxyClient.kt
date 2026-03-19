package net.beholderface.harmfromhexxy.forge

import net.beholderface.harmfromhexxy.HarmFromHexxyClient
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.LOADING_CONTEXT

object ForgeHarmFromHexxyClient {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLClientSetupEvent) {
        HarmFromHexxyClient.init()
        LOADING_CONTEXT.registerExtensionPoint(ConfigScreenFactory::class.java) {
            ConfigScreenFactory { _, parent -> HarmFromHexxyClient.getConfigScreen(parent) }
        }
    }
}
