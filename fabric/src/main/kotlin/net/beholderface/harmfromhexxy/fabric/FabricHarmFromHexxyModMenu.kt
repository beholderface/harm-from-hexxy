package net.beholderface.harmfromhexxy.fabric

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import net.beholderface.harmfromhexxy.HarmFromHexxyClient

object FabricHarmFromHexxyModMenu : ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory(HarmFromHexxyClient::getConfigScreen)
}
