package net.coolsimulations.ForgottenEngineers.sounds;

import net.coolsimulations.ForgottenEngineers.FERegistration;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ForgottenEngineersSounds {

    public static final FERegistration.FERegistrationProvider<SoundEvent> SOUNDS = FERegistration.FERegistrationProvider.get(BuiltInRegistries.SOUND_EVENT, ForgottenEngineersCommon.MOD_ID);

    public static Identifier RESTORER_DROP_CONTENTS_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.restorer.drop_contents");
    public static Identifier RESTORER_INSERT_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.restorer.insert");
    public static Identifier RESTORER_REMOVE_ONE_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.restorer.remove_one");

    public static Identifier ROUTER_DROP_CONTENTS_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.router.drop_contents");
    public static Identifier ROUTER_INSERT_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.router.insert");
    public static Identifier ROUTER_REMOVE_ONE_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.router.remove_one");

    public static final FERegistration.FERegistryObject<SoundEvent> RESTORER_DROP_CONTENTS = SOUNDS.register(RESTORER_DROP_CONTENTS_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(RESTORER_DROP_CONTENTS_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> RESTORER_INSERT = SOUNDS.register(RESTORER_INSERT_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(RESTORER_INSERT_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> RESTORER_REMOVE_ONE = SOUNDS.register(RESTORER_REMOVE_ONE_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(RESTORER_REMOVE_ONE_ID));

    public static final FERegistration.FERegistryObject<SoundEvent> ROUTER_DROP_CONTENTS = SOUNDS.register(ROUTER_DROP_CONTENTS_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(ROUTER_DROP_CONTENTS_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> ROUTER_INSERT = SOUNDS.register(ROUTER_INSERT_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(ROUTER_INSERT_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> ROUTER_REMOVE_ONE = SOUNDS.register(ROUTER_REMOVE_ONE_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(ROUTER_REMOVE_ONE_ID));

    public static void init() {

    }
}
