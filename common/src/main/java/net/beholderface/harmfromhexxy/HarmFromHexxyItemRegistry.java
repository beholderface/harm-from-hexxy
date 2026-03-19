package net.beholderface.harmfromhexxy;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class HarmFromHexxyItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(HarmFromHexxy.MODID, Registries.ITEM);
    public static void init(){
        ITEMS.register();
    }
    public static final RegistrySupplier<Item> HEXXY_MASK = ITEMS.register("hexxy_mask", ()->
            new HexxyMaskItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
}
