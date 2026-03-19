@file:JvmName("HarmFromHexxyAbstractionsImpl")

package net.beholderface.harmfromhexxy.forge

import net.beholderface.harmfromhexxy.registry.HarmFromHexxyRegistrar
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

fun <T : Any> initRegistry(registrar: HarmFromHexxyRegistrar<T>) {
    MOD_BUS.addListener { event: RegisterEvent ->
        event.register(registrar.registryKey) { helper ->
            registrar.init(helper::register)
        }
    }
}
