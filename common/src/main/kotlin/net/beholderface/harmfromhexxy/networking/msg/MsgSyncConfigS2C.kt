package net.beholderface.harmfromhexxy.networking.msg

import net.beholderface.harmfromhexxy.config.HarmFromHexxyServerConfig
import net.minecraft.network.FriendlyByteBuf

data class MsgSyncConfigS2C(val serverConfig: HarmFromHexxyServerConfig.ServerConfig) : HarmFromHexxyMessageS2C {
    companion object : HarmFromHexxyMessageCompanion<MsgSyncConfigS2C> {
        override val type = MsgSyncConfigS2C::class.java

        override fun decode(buf: FriendlyByteBuf) = MsgSyncConfigS2C(
            serverConfig = HarmFromHexxyServerConfig.ServerConfig().decode(buf),
        )

        override fun MsgSyncConfigS2C.encode(buf: FriendlyByteBuf) {
            serverConfig.encode(buf)
        }
    }
}
