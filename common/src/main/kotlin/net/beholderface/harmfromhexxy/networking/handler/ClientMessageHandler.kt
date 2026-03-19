package net.beholderface.harmfromhexxy.networking.handler

import dev.architectury.networking.NetworkManager.PacketContext
import net.beholderface.harmfromhexxy.config.HarmFromHexxyServerConfig
import net.beholderface.harmfromhexxy.networking.msg.*

fun HarmFromHexxyMessageS2C.applyOnClient(ctx: PacketContext) = ctx.queue {
    when (this) {
        is MsgSyncConfigS2C -> {
            HarmFromHexxyServerConfig.onSyncConfig(serverConfig)
        }

        // add more client-side message handlers here
    }
}
