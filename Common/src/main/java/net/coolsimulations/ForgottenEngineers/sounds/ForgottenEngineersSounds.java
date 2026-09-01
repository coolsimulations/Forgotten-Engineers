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

    public static Identifier COMPRESSOR_INSERT_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.compressor.insert");
    public static Identifier COMPRESSOR_DROP_CONTENTS_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.compressor.drop_contents");
    public static Identifier COMPRESSOR_REMOVE_ONE_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.compressor.remove_one");

    public static Identifier FUEL_CARRIER_DROP_CONTENTS_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.fuel_carrier.drop_contents");
    public static Identifier FUEL_CARRIER_INSERT_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.fuel_carrier.insert");
    public static Identifier FUEL_CARRIER_REMOVE_ONE_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.fuel_carrier.remove_one");

    public static Identifier INDUCTION_FURNACE_DROP_CONTENTS_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.induction_furnace.drop_contents");
    public static Identifier INDUCTION_FURNACE_INSERT_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.induction_furnace.insert");
    public static Identifier INDUCTION_FURNACE_REMOVE_ONE_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.induction_furnace.remove_one");

    public static Identifier MENDER_DROP_CONTENTS_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.mender.drop_contents");
    public static Identifier MENDER_INSERT_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.mender.insert");
    public static Identifier MENDER_REMOVE_ONE_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.mender.remove_one");

    public static Identifier STRIPPER_DROP_CONTENTS_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.stripper.drop_contents");
    public static Identifier STRIPPER_INSERT_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.stripper.insert");
    public static Identifier STRIPPER_REMOVE_ONE_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.stripper.remove_one");

    public static Identifier COMBUSTOR_DROP_CONTENTS_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.combustor.drop_contents");
    public static Identifier COMBUSTOR_REMOVE_ONE_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item.combustor.remove_one");

    public static final FERegistration.FERegistryObject<SoundEvent> RESTORER_DROP_CONTENTS = SOUNDS.register(RESTORER_DROP_CONTENTS_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(RESTORER_DROP_CONTENTS_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> RESTORER_INSERT = SOUNDS.register(RESTORER_INSERT_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(RESTORER_INSERT_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> RESTORER_REMOVE_ONE = SOUNDS.register(RESTORER_REMOVE_ONE_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(RESTORER_REMOVE_ONE_ID));

    public static final FERegistration.FERegistryObject<SoundEvent> ROUTER_DROP_CONTENTS = SOUNDS.register(ROUTER_DROP_CONTENTS_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(ROUTER_DROP_CONTENTS_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> ROUTER_INSERT = SOUNDS.register(ROUTER_INSERT_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(ROUTER_INSERT_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> ROUTER_REMOVE_ONE = SOUNDS.register(ROUTER_REMOVE_ONE_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(ROUTER_REMOVE_ONE_ID));

    public static final FERegistration.FERegistryObject<SoundEvent> COMPRESSOR_DROP_CONTENTS = SOUNDS.register(COMPRESSOR_DROP_CONTENTS_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(COMPRESSOR_DROP_CONTENTS_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> COMPRESSOR_INSERT = SOUNDS.register(COMPRESSOR_INSERT_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(COMPRESSOR_INSERT_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> COMPRESSOR_REMOVE_ONE = SOUNDS.register(COMPRESSOR_REMOVE_ONE_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(COMPRESSOR_REMOVE_ONE_ID));

    public static final FERegistration.FERegistryObject<SoundEvent> FUEL_CARRIER_DROP_CONTENTS = SOUNDS.register(FUEL_CARRIER_DROP_CONTENTS_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(FUEL_CARRIER_DROP_CONTENTS_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> FUEL_CARRIER_INSERT = SOUNDS.register(FUEL_CARRIER_INSERT_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(FUEL_CARRIER_INSERT_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> FUEL_CARRIER_REMOVE_ONE = SOUNDS.register(FUEL_CARRIER_REMOVE_ONE_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(FUEL_CARRIER_REMOVE_ONE_ID));

    public static final FERegistration.FERegistryObject<SoundEvent> INDUCTION_FURNACE_DROP_CONTENTS = SOUNDS.register(INDUCTION_FURNACE_DROP_CONTENTS_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(INDUCTION_FURNACE_DROP_CONTENTS_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> INDUCTION_FURNACE_INSERT = SOUNDS.register(INDUCTION_FURNACE_INSERT_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(INDUCTION_FURNACE_INSERT_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> INDUCTION_FURNACE_REMOVE_ONE = SOUNDS.register(INDUCTION_FURNACE_REMOVE_ONE_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(INDUCTION_FURNACE_REMOVE_ONE_ID));

    public static final FERegistration.FERegistryObject<SoundEvent> MENDER_DROP_CONTENTS = SOUNDS.register(MENDER_DROP_CONTENTS_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(MENDER_DROP_CONTENTS_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> MENDER_INSERT = SOUNDS.register(MENDER_INSERT_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(MENDER_INSERT_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> MENDER_REMOVE_ONE = SOUNDS.register(MENDER_REMOVE_ONE_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(MENDER_REMOVE_ONE_ID));

    public static final FERegistration.FERegistryObject<SoundEvent> STRIPPER_DROP_CONTENTS = SOUNDS.register(STRIPPER_DROP_CONTENTS_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(STRIPPER_DROP_CONTENTS_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> STRIPPER_INSERT = SOUNDS.register(STRIPPER_INSERT_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(STRIPPER_INSERT_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> STRIPPER_REMOVE_ONE = SOUNDS.register(STRIPPER_REMOVE_ONE_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(STRIPPER_REMOVE_ONE_ID));

    public static final FERegistration.FERegistryObject<SoundEvent> COMBUSTOR_DROP_CONTENTS = SOUNDS.register(COMBUSTOR_DROP_CONTENTS_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(COMBUSTOR_DROP_CONTENTS_ID));
    public static final FERegistration.FERegistryObject<SoundEvent> COMBUSTOR_REMOVE_ONE = SOUNDS.register(COMBUSTOR_REMOVE_ONE_ID.getPath(), () -> SoundEvent.createVariableRangeEvent(COMBUSTOR_REMOVE_ONE_ID));


    public static void init() {

    }
}
