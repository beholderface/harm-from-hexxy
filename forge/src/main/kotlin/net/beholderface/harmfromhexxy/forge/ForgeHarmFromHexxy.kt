package net.beholderface.harmfromhexxy.forge

import dev.architectury.platform.forge.EventBuses
import net.beholderface.harmfromhexxy.HarmFromHexxy
import net.beholderface.harmfromhexxy.forge.datagen.ForgeHarmFromHexxyDatagen
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(HarmFromHexxy.MODID)
class ForgeHarmFromHexxy {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(HarmFromHexxy.MODID, this)
            addListener(ForgeHarmFromHexxyClient::init)
            addListener(ForgeHarmFromHexxyDatagen::init)
            addListener(ForgeHarmFromHexxyServer::init)
        }
        HarmFromHexxy.init()
    }
}
