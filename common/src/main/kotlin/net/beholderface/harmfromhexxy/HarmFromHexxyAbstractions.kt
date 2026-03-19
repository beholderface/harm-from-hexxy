@file:JvmName("HarmFromHexxyAbstractions")

package net.beholderface.harmfromhexxy

import dev.architectury.injectables.annotations.ExpectPlatform
import net.beholderface.harmfromhexxy.registry.HarmFromHexxyRegistrar

fun initRegistries(vararg registries: HarmFromHexxyRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HarmFromHexxyRegistrar<T>) {
    throw AssertionError()
}
