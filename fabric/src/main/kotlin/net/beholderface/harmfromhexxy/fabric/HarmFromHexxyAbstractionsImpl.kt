@file:JvmName("HarmFromHexxyAbstractionsImpl")

package net.beholderface.harmfromhexxy.fabric

import net.beholderface.harmfromhexxy.registry.HarmFromHexxyRegistrar
import net.minecraft.core.Registry

fun <T : Any> initRegistry(registrar: HarmFromHexxyRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
