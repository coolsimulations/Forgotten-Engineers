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
        FESounds.generateSounds(this::generateSound);
    }

    public void generateSound(FESounds.SoundData sound) {
        SoundDefinition definition = SoundDefinition.definition().subtitle(sound.subtitle());
        for (int i = 1; i <= sound.variations(); i++)
            definition.with(sound(Identifier.fromNamespaceAndPath(sound.event().location().getNamespace(), sound.event().location().getPath().replace('.', '/') + Integer.toString(i))));
        this.add(sound.event(), definition);
    }
}
