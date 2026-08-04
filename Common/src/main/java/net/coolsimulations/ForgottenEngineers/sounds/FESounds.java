package net.coolsimulations.ForgottenEngineers.sounds;

import net.coolsimulations.ForgottenEngineers.FEServices;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class FESounds {

    public static SoundEvent RESTORER_DROP_CONTENTS = FEServices.REGISTRY.getSoundEvent(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.restorer.drop_contents"));
    public static SoundEvent RESTORER_INSERT = FEServices.REGISTRY.getSoundEvent(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.restorer.insert"));
    public static SoundEvent RESTORER_REMOVE_ONE = FEServices.REGISTRY.getSoundEvent(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.restorer.remove_one"));
}
