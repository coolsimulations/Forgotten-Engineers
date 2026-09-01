package net.coolsimulations.ForgottenEngineers.sounds;

import net.coolsimulations.ForgottenEngineers.FEServices;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FELoot;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class FESounds {

    public static SoundEvent RESTORER_DROP_CONTENTS = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.RESTORER_DROP_CONTENTS_ID);
    public static SoundEvent RESTORER_INSERT = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.RESTORER_INSERT_ID);
    public static SoundEvent RESTORER_REMOVE_ONE = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.RESTORER_REMOVE_ONE_ID);

    public static SoundEvent ROUTER_DROP_CONTENTS = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.ROUTER_DROP_CONTENTS_ID);
    public static SoundEvent ROUTER_INSERT = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.ROUTER_INSERT_ID);
    public static SoundEvent ROUTER_REMOVE_ONE = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.ROUTER_REMOVE_ONE_ID);

    public static SoundEvent COMPRESSOR_DROP_CONTENTS = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.COMPRESSOR_DROP_CONTENTS_ID);
    public static SoundEvent COMPRESSOR_INSERT = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.COMPRESSOR_INSERT_ID);
    public static SoundEvent COMPRESSOR_REMOVE_ONE = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.COMPRESSOR_REMOVE_ONE_ID);

    public static SoundEvent FUEL_CARRIER_DROP_CONTENTS = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.FUEL_CARRIER_DROP_CONTENTS_ID);
    public static SoundEvent FUEL_CARRIER_INSERT = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.FUEL_CARRIER_INSERT_ID);
    public static SoundEvent FUEL_CARRIER_REMOVE_ONE = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.FUEL_CARRIER_REMOVE_ONE_ID);

    public static SoundEvent INDUCTION_FURNACE_DROP_CONTENTS = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.INDUCTION_FURNACE_DROP_CONTENTS_ID);
    public static SoundEvent INDUCTION_FURNACE_INSERT = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.INDUCTION_FURNACE_INSERT_ID);
    public static SoundEvent INDUCTION_FURNACE_REMOVE_ONE = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.INDUCTION_FURNACE_REMOVE_ONE_ID);

    public static SoundEvent MENDER_DROP_CONTENTS = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.MENDER_DROP_CONTENTS_ID);
    public static SoundEvent MENDER_INSERT = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.MENDER_INSERT_ID);
    public static SoundEvent MENDER_REMOVE_ONE = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.MENDER_REMOVE_ONE_ID);

    public static SoundEvent STRIPPER_DROP_CONTENTS = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.STRIPPER_DROP_CONTENTS_ID);
    public static SoundEvent STRIPPER_INSERT = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.STRIPPER_INSERT_ID);
    public static SoundEvent STRIPPER_REMOVE_ONE = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.STRIPPER_REMOVE_ONE_ID);

    public static SoundEvent COMBUSTOR_DROP_CONTENTS = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.COMBUSTOR_DROP_CONTENTS_ID);
    public static SoundEvent COMBUSTOR_REMOVE_ONE = FEServices.REGISTRY.getSoundEvent(ForgottenEngineersSounds.COMBUSTOR_REMOVE_ONE_ID);

    public static void generateSounds(Consumer<SoundData> sounds) {
        sounds.accept(SoundData.RESTORER_DROP_CONTENTS);
        sounds.accept(SoundData.RESTORER_INSERT);
        sounds.accept(SoundData.RESTORER_REMOVE_ONE);

        sounds.accept(SoundData.ROUTER_DROP_CONTENTS);
        sounds.accept(SoundData.ROUTER_INSERT);
        sounds.accept(SoundData.ROUTER_REMOVE_ONE);

        sounds.accept(SoundData.COMPRESSOR_DROP_CONTENTS);
        sounds.accept(SoundData.COMPRESSOR_INSERT);
        sounds.accept(SoundData.COMPRESSOR_REMOVE_ONE);

        sounds.accept(SoundData.FUEL_CARRIER_DROP_CONTENTS);
        sounds.accept(SoundData.FUEL_CARRIER_INSERT);
        sounds.accept(SoundData.FUEL_CARRIER_REMOVE_ONE);

        sounds.accept(SoundData.INDUCTION_FURNACE_DROP_CONTENTS);
        sounds.accept(SoundData.INDUCTION_FURNACE_INSERT);
        sounds.accept(SoundData.INDUCTION_FURNACE_REMOVE_ONE);

        sounds.accept(SoundData.MENDER_DROP_CONTENTS);
        sounds.accept(SoundData.MENDER_INSERT);
        sounds.accept(SoundData.MENDER_REMOVE_ONE);

        sounds.accept(SoundData.STRIPPER_DROP_CONTENTS);
        sounds.accept(SoundData.STRIPPER_INSERT);
        sounds.accept(SoundData.STRIPPER_REMOVE_ONE);

        sounds.accept(SoundData.COMBUSTOR_DROP_CONTENTS);
        sounds.accept(SoundData.COMBUSTOR_REMOVE_ONE);
    }

    public record SoundData(SoundEvent event, String subtitle, Integer variations) {

        public static final SoundData RESTORER_DROP_CONTENTS = new FESounds.SoundData(FESounds.RESTORER_DROP_CONTENTS, "subtitles." + ForgottenEngineersCommon.MOD_ID + ".item.restorer.drop_contents", 3);
        public static final SoundData RESTORER_INSERT = new FESounds.SoundData(FESounds.RESTORER_INSERT, "subtitles.item.bundle.insert", 3);
        public static final SoundData RESTORER_REMOVE_ONE = new FESounds.SoundData(FESounds.RESTORER_REMOVE_ONE, "subtitles.item.bundle.remove_one", 3);
        public static final SoundData ROUTER_DROP_CONTENTS = new FESounds.SoundData(FESounds.ROUTER_DROP_CONTENTS, "subtitles." + ForgottenEngineersCommon.MOD_ID + ".item.router.drop_contents", 3);
        public static final SoundData ROUTER_INSERT = new FESounds.SoundData(FESounds.ROUTER_INSERT, "subtitles.item.bundle.insert", 3);
        public static final SoundData ROUTER_REMOVE_ONE = new FESounds.SoundData(FESounds.ROUTER_REMOVE_ONE, "subtitles.item.bundle.remove_one", 3);
        public static final SoundData COMPRESSOR_DROP_CONTENTS = new FESounds.SoundData(FESounds.COMPRESSOR_DROP_CONTENTS, "subtitles." + ForgottenEngineersCommon.MOD_ID + ".item.compressor.drop_contents", 3);
        public static final SoundData COMPRESSOR_INSERT = new FESounds.SoundData(FESounds.COMPRESSOR_INSERT, "subtitles.item.bundle.insert", 3);
        public static final SoundData COMPRESSOR_REMOVE_ONE = new FESounds.SoundData(FESounds.COMPRESSOR_REMOVE_ONE, "subtitles.item.bundle.remove_one", 3);
        public static final SoundData FUEL_CARRIER_DROP_CONTENTS = new FESounds.SoundData(FESounds.FUEL_CARRIER_DROP_CONTENTS, "subtitles." + ForgottenEngineersCommon.MOD_ID + ".item.fuel_carrier.drop_contents", 3);
        public static final SoundData FUEL_CARRIER_INSERT = new FESounds.SoundData(FESounds.FUEL_CARRIER_INSERT, "subtitles.item.bundle.insert", 3);
        public static final SoundData FUEL_CARRIER_REMOVE_ONE = new FESounds.SoundData(FESounds.FUEL_CARRIER_REMOVE_ONE, "subtitles.item.bundle.remove_one", 3);
        public static final SoundData INDUCTION_FURNACE_DROP_CONTENTS = new FESounds.SoundData(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, "subtitles." + ForgottenEngineersCommon.MOD_ID + ".item.induction_furnace.drop_contents", 3);
        public static final SoundData INDUCTION_FURNACE_INSERT = new FESounds.SoundData(FESounds.INDUCTION_FURNACE_INSERT, "subtitles.item.bundle.insert", 3);
        public static final SoundData INDUCTION_FURNACE_REMOVE_ONE = new FESounds.SoundData(FESounds.INDUCTION_FURNACE_REMOVE_ONE, "subtitles.item.bundle.remove_one", 3);
        public static final SoundData MENDER_DROP_CONTENTS = new FESounds.SoundData(FESounds.MENDER_DROP_CONTENTS, "subtitles." + ForgottenEngineersCommon.MOD_ID + ".item.mender.drop_contents", 3);
        public static final SoundData MENDER_INSERT = new FESounds.SoundData(FESounds.MENDER_INSERT, "subtitles.item.bundle.insert", 3);
        public static final SoundData MENDER_REMOVE_ONE = new FESounds.SoundData(FESounds.MENDER_REMOVE_ONE, "subtitles.item.bundle.remove_one", 3);
        public static final SoundData STRIPPER_DROP_CONTENTS = new FESounds.SoundData(FESounds.STRIPPER_DROP_CONTENTS, "subtitles." + ForgottenEngineersCommon.MOD_ID + ".item.stripper.drop_contents", 3);
        public static final SoundData STRIPPER_INSERT = new FESounds.SoundData(FESounds.STRIPPER_INSERT, "subtitles.item.bundle.insert", 3);
        public static final SoundData STRIPPER_REMOVE_ONE = new FESounds.SoundData(FESounds.STRIPPER_REMOVE_ONE, "subtitles.item.bundle.remove_one", 3);
        public static final SoundData COMBUSTOR_DROP_CONTENTS = new FESounds.SoundData(FESounds.COMBUSTOR_DROP_CONTENTS, "subtitles." + ForgottenEngineersCommon.MOD_ID + ".item.combustor.drop_contents", 3);
        public static final SoundData COMBUSTOR_REMOVE_ONE = new FESounds.SoundData(FESounds.COMBUSTOR_REMOVE_ONE, "subtitles.item.bundle.remove_one", 3);
    }
}
