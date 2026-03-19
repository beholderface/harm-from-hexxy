package net.beholderface.harmfromhexxy

import net.beholderface.harmfromhexxy.config.HarmFromHexxyClientConfig
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen

object HarmFromHexxyClient {
    fun init() {
        HarmFromHexxyClientConfig.init()
    }

    fun getConfigScreen(parent: Screen): Screen {
        return AutoConfig.getConfigScreen(HarmFromHexxyClientConfig.GlobalConfig::class.java, parent).get()
    }
}
