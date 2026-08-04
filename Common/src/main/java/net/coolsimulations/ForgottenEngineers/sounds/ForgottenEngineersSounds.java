package net.coolsimulations.ForgottenEngineers.sounds;

import net.coolsimulations.ForgottenEngineers.FERegistration;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ForgottenEngineersSounds {

    public static final FERegistration.FERegistrationProvider<SoundEvent> SOUNDS = FERegistration.FERegistrationProvider.get(BuiltInRegistries.SOUND_EVENT, ForgottenEngineersCommon.MOD_ID);

    public static final FERegistration.FERegistryObject<SoundEvent> RESTORER_DROP_CONTENTS = SOUNDS.register("item.restorer.drop_contents", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.restorer.drop_contents")));
    public static final FERegistration.FERegistryObject<SoundEvent> RESTORER_INSERT = SOUNDS.register("item.restorer.insert", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.restorer.insert")));
    public static final FERegistration.FERegistryObject<SoundEvent> RESTORER_REMOVE_ONE = SOUNDS.register("item.restorer.remove_one", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.restorer.remove_one")));

    public static void init() {

    }
}
