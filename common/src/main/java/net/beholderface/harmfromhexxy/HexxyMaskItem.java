package net.beholderface.harmfromhexxy;

import at.petrak.hexcasting.common.lib.HexAttributes;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class HexxyMaskItem extends Item implements Equipable {
    public HexxyMaskItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    private static final Multimap<Attribute, AttributeModifier> modifiers;
    static {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(HexAttributes.GRID_ZOOM, new AttributeModifier(UUID.fromString("9fab7a52-b871-4d53-9ccc-6648b80b3b33"), //randomly generated
                "hexxy mask grid modifier", 6.0, AttributeModifier.Operation.ADDITION)); //TIL there's a limit to how high your grid size value can go before it stops doing anything more
        builder.put(HexAttributes.SCRY_SIGHT, new AttributeModifier(UUID.fromString("9fba7a52-b871-4d53-9ccc-6648b80b3b33"), //slightly less randomly generated
                "hexxy mask sight modifier", 1.0, AttributeModifier.Operation.ADDITION));
        modifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.HEAD){
            return modifiers;
        } else {
            return ImmutableMultimap.of();
        }
    }
}
