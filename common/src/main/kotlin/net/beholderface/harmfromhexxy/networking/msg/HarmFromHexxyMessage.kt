package net.beholderface.harmfromhexxy.networking.msg

import dev.architectury.networking.NetworkChannel
import dev.architectury.networking.NetworkManager.PacketContext
import net.beholderface.harmfromhexxy.HarmFromHexxy
import net.beholderface.harmfromhexxy.networking.HarmFromHexxyNetworking
import net.beholderface.harmfromhexxy.networking.handler.applyOnClient
import net.beholderface.harmfromhexxy.networking.handler.applyOnServer
import net.fabricmc.api.EnvType
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import java.util.function.Supplier

sealed interface HarmFromHexxyMessage

sealed interface HarmFromHexxyMessageC2S : HarmFromHexxyMessage {
    fun sendToServer() {
        HarmFromHexxyNetworking.CHANNEL.sendToServer(this)
    }
}

sealed interface HarmFromHexxyMessageS2C : HarmFromHexxyMessage {
    fun sendToPlayer(player: ServerPlayer) {
        HarmFromHexxyNetworking.CHANNEL.sendToPlayer(player, this)
    }

    fun sendToPlayers(players: Iterable<ServerPlayer>) {
        HarmFromHexxyNetworking.CHANNEL.sendToPlayers(players, this)
    }
}

sealed interface HarmFromHexxyMessageCompanion<T : HarmFromHexxyMessage> {
    val type: Class<T>

    fun decode(buf: FriendlyByteBuf): T

    fun T.encode(buf: FriendlyByteBuf)

    fun apply(msg: T, supplier: Supplier<PacketContext>) {
        val ctx = supplier.get()
        when (ctx.env) {
            EnvType.SERVER, null -> {
                HarmFromHexxy.LOGGER.debug("Server received packet from {}: {}", ctx.player.name.string, this)
                when (msg) {
                    is HarmFromHexxyMessageC2S -> msg.applyOnServer(ctx)
                    else -> HarmFromHexxy.LOGGER.warn("Message not handled on server: {}", msg::class)
                }
            }
            EnvType.CLIENT -> {
                HarmFromHexxy.LOGGER.debug("Client received packet: {}", this)
                when (msg) {
                    is HarmFromHexxyMessageS2C -> msg.applyOnClient(ctx)
                    else -> HarmFromHexxy.LOGGER.warn("Message not handled on client: {}", msg::class)
                }
            }
        }
    }

    fun register(channel: NetworkChannel) {
        channel.register(type, { msg, buf -> msg.encode(buf) }, ::decode, ::apply)
    }
}
