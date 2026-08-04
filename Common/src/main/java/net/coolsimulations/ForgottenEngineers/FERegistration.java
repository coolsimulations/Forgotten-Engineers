package net.coolsimulations.ForgottenEngineers;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;

import java.util.Collection;
import java.util.function.Supplier;

public class FERegistration {

    public interface IFERegistry {
        Item getItem(Identifier location);

        SoundEvent getSoundEvent(Identifier location);

        PlatformType getPlatformType();
    }

    public interface FERegistryObject<T> extends Supplier<T> {

        ResourceKey<T> getResourceKey();

        Identifier getId();

        @Override
        T get();

        Holder<T> asHolder();
    }

    public interface FERegistrationProvider<T> {
        static <T> FERegistrationProvider<T> get(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
            return Factory.INSTANCE.create(resourceKey, modId);
        }

        static <T> FERegistrationProvider<T> get(Registry<T> registry, String modId) {
            return Factory.INSTANCE.create(registry, modId);
        }

        <I extends T> FERegistryObject<I> register(String name, Supplier<? extends I> supplier);

        Collection<FERegistryObject<T>> getEntries();

        String getModId();

        interface Factory {
            Factory INSTANCE = FEServices.load(Factory.class);

            <T> FERegistrationProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId);

            default <T> FERegistrationProvider<T> create(Registry<T> registry, String modId) {
                return create(registry.key(), modId);
            }
        }
    }

    public enum PlatformType {
        FORGE("forge"),
        FABRIC("fabric"),
        NEOFORGE("neoforge");

        private final String NAME;

        PlatformType(String name) {
            this.NAME = name;
        }

        @Override
        public String toString() {
            return this.NAME;
        }
    }
}
