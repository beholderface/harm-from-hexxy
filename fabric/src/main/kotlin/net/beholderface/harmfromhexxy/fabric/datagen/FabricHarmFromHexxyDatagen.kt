package net.beholderface.harmfromhexxy.fabric.datagen

import net.beholderface.harmfromhexxy.datagen.HarmFromHexxyActionTags
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object FabricHarmFromHexxyDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()

        pack.addProvider(::HarmFromHexxyActionTags)
    }
}
