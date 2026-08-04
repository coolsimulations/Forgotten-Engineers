package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ForgottenEngineersSoundProvider extends SoundDefinitionsProvider {

    protected ForgottenEngineersSoundProvider(PackOutput output) {
        super(output, ForgottenEngineersCommon.MOD_ID);
    }

    @Override
    public void registerSounds() {
        this.add(FESounds.RESTORER_DROP_CONTENTS, SoundDefinition.definition().subtitle("subtitles.item.restorer.drop_contents").with(sound(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/restorer/drop_contents1")), sound(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/restorer/drop_contents2")), sound(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/restorer/drop_contents3"))));
        this.add(FESounds.RESTORER_INSERT, SoundDefinition.definition().subtitle("subtitles.item.bundle.insert").with(sound(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/restorer/insert1")), sound(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/restorer/insert2")), sound(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/restorer/insert3"))));
        this.add(FESounds.RESTORER_REMOVE_ONE, SoundDefinition.definition().subtitle("subtitles.item.bundle.remove_one").with(sound(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/restorer/remove_one1")), sound(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/restorer/remove_one2")), sound(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/restorer/remove_one3"))));
    }
}
