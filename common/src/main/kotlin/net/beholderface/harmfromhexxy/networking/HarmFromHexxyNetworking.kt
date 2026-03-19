package net.beholderface.harmfromhexxy.networking

import dev.architectury.networking.NetworkChannel
import net.beholderface.harmfromhexxy.HarmFromHexxy
import net.beholderface.harmfromhexxy.networking.msg.HarmFromHexxyMessageCompanion

object HarmFromHexxyNetworking {
    val CHANNEL: NetworkChannel = NetworkChannel.create(HarmFromHexxy.id("networking_channel"))

    fun init() {
        for (subclass in HarmFromHexxyMessageCompanion::class.sealedSubclasses) {
            subclass.objectInstance?.register(CHANNEL)
        }
    }
}
