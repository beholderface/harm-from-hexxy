package net.beholderface.harmfromhexxy.envstuff;

import at.petrak.hexcasting.api.casting.eval.CastResult;
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import at.petrak.hexcasting.api.casting.eval.CastingEnvironmentComponent;
import at.petrak.hexcasting.api.casting.eval.ResolvedPatternType;
import at.petrak.hexcasting.api.casting.iota.PatternIota;
import net.beholderface.harmfromhexxy.HarmFromHexxyItemRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.Items;

import java.util.UUID;

public class HexxySeesYouComponent implements CastingEnvironmentComponent.PostExecution {

    public final Key<PostExecution> key;
    public final CastingEnvironment environment;

    public HexxySeesYouComponent(CastingEnvironment environment) {
        this.key = new HexxySeesYouKey();
        this.environment = environment;
    }

    @Override
    public void onPostExecution(CastResult result) {
        if (result.getNewData() != null){
            var parenthesized = result.getNewData().getParenthesized();
            if (parenthesized.isEmpty()){
                return;
            }
            var latest = parenthesized.get(parenthesized.size() - 1);
            if (latest.getIota() instanceof PatternIota pattern){
                String anglesig = pattern.getPattern().anglesSignature();
                boolean escaped = latest.getEscaped();
                //HarmFromHexxy.LOGGER.info("{} pattern: {} {}", escaped ? "Escaped" : "Unescaped", pattern.getPattern().getStartDir().toString(), anglesig);
                if (escaped && result.getResolutionType() == ResolvedPatternType.ESCAPED && anglesig.equals("qqqaw")){
                    //HarmFromHexxy.LOGGER.info("weewooweewoo");
                    ServerPlayer caster = environment.getCaster();
                    if (caster != null){
                        ServerLevel world = environment.getWorld();
                        RandomSource random = world.random;
                        for (int i = 0; i < random.nextIntBetweenInclusive(3, 8); i++){
                            Mob monster = new Skeleton(EntityType.SKELETON, world);
                            CompoundTag preexistingData = new CompoundTag();
                            monster.addAdditionalSaveData(preexistingData);
                            CompoundTag newData = preexistingData.copy();
                            newData.putString("DeathLootTable", "harmfromhexxy:empty");
                            monster.readAdditionalSaveData(newData);
                            monster.setCanPickUpLoot(false);
                            monster.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, -1, 0, true, false));
                            monster.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, -1, 0, true, false));
                            monster.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, -1, 2, true, false));
                            monster.setInvisible(true);
                            monster.skipDropExperience();
                            monster.setCustomName(Component.literal("Hexxy"));
                            monster.setItemSlot(EquipmentSlot.HEAD, HarmFromHexxyItemRegistry.HEXXY_MASK.get().getDefaultInstance());
                            monster.setDropChance(EquipmentSlot.HEAD, 0.1f);
                            monster.hurt(caster.damageSources().playerAttack(caster), 0.01f); //so that they go after you immediately
                            //I forgot this deals damage, and regen doesn't work on undead (no I'm not making them spawn with inexhaustible phials)
                            /*if (Platform.isModLoaded("oneironaut")){
                                MobEffect shroud = BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.tryParse("oneironaut:detection_resistance"));
                                if (shroud != null){
                                    monster.addEffect(new MobEffectInstance(shroud, -1, 0, true, false));
                                }
                            }*/
                            monster.setPosRaw(caster.getX() + ((random.nextDouble() - 0.5) * 5), caster.getY() + 0.5, caster.getZ()+ ((random.nextDouble() - 0.5) * 5));
                            world.addWithUUID(monster);
                        }
                        //help from hexxy is bugged and can't actually make hexxy say things right now
                        /*caster.sendSystemMessage(Component.translatable(
                                "harm_from_hexxy.hexxy_spawn_message."+(Platform.isModLoaded("helpfromhexxy") ? "helpful" : "default")));*/
                        caster.sendSystemMessage(Component.translatable("harm_from_hexxy.hexxy_spawn_message.default"));
                    }
                }
            }
        }
    }

    @Override
    public Key<PostExecution> getKey() {
        return this.key;
    }

    public static class HexxySeesYouKey implements Key<PostExecution> {
        public static final UUID UUID_STATIC = UUID.randomUUID();
        public final UUID uuid;
        public HexxySeesYouKey(){this.uuid = UUID_STATIC;}
    }
}
