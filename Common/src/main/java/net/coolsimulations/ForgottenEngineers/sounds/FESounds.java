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

    public static void generateSounds(Consumer<SoundData> sounds) {
        sounds.accept(SoundData.RESTORER_DROP_CONTENTS);
        sounds.accept(SoundData.RESTORER_INSERT);
        sounds.accept(SoundData.RESTORER_REMOVE_ONE);

        sounds.accept(SoundData.ROUTER_DROP_CONTENTS);
        sounds.accept(SoundData.ROUTER_INSERT);
        sounds.accept(SoundData.ROUTER_REMOVE_ONE);
    }

    public record SoundData(SoundEvent event, String subtitle, Integer variations) {

        public static final SoundData RESTORER_DROP_CONTENTS = new FESounds.SoundData(FESounds.RESTORER_DROP_CONTENTS, "subtitles." + ForgottenEngineersCommon.MOD_ID + ".item.restorer.drop_contents", 3);
        public static final SoundData RESTORER_INSERT = new FESounds.SoundData(FESounds.RESTORER_INSERT, "subtitles.item.bundle.insert", 3);
        public static final SoundData RESTORER_REMOVE_ONE = new FESounds.SoundData(FESounds.RESTORER_REMOVE_ONE, "subtitles.item.bundle.remove_one", 3);
        public static final SoundData ROUTER_DROP_CONTENTS = new FESounds.SoundData(FESounds.ROUTER_DROP_CONTENTS, "subtitles." + ForgottenEngineersCommon.MOD_ID + ".item.router.drop_contents", 3);
        public static final SoundData ROUTER_INSERT = new FESounds.SoundData(FESounds.ROUTER_INSERT, "subtitles.item.bundle.insert", 3);
        public static final SoundData ROUTER_REMOVE_ONE = new FESounds.SoundData(FESounds.ROUTER_REMOVE_ONE, "subtitles.item.bundle.remove_one", 3);
    }
}
