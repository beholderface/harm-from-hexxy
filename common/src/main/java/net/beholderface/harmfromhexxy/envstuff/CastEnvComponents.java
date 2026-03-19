package net.beholderface.harmfromhexxy.envstuff;

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import at.petrak.hexcasting.api.casting.eval.env.StaffCastEnv;

public class CastEnvComponents {
    public static void init(){
        CastingEnvironment.addCreateEventListener((env, nbt)->{
            if (env instanceof StaffCastEnv){
                env.addExtension(new HexxySeesYouComponent(env));
            }
        });
    }
}
