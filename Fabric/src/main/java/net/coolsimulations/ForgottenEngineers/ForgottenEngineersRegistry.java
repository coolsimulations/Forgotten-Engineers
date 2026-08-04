package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.FERegistration.FERegistrationProvider;
import net.coolsimulations.ForgottenEngineers.FERegistration.FERegistryObject;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class ForgottenEngineersRegistry implements FERegistrationProvider.Factory, FERegistration.IFERegistry {

    @Override
    public <T> FERegistrationProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
        return new Provider<>(modId, resourceKey);
    }

    @Override
    public <T> FERegistrationProvider<T> create(Registry<T> registry, String modId) {
        return new Provider<>(modId, registry);
    }

    @Override
    public Item getItem(Identifier location) {
        return BuiltInRegistries.ITEM.get(location).map(Holder.Reference::value).orElse(Items.AIR);
    }

    @Override
    public SoundEvent getSoundEvent(Identifier location) {
        return BuiltInRegistries.SOUND_EVENT.get(location).map(Holder.Reference::value).orElse(SoundEvents.EXPERIENCE_ORB_PICKUP);
    }

    @Override
    public FERegistration.PlatformType getPlatformType() {
        return FERegistration.PlatformType.FABRIC;
    }

    private static class Provider<T> implements FERegistrationProvider<T> {
        private final String modId;
        private final Registry<T> registry;

        private final Set<FERegistryObject<T>> entries = new HashSet<>();
        private final Set<FERegistryObject<T>> entriesView = Collections.unmodifiableSet(entries);

        @SuppressWarnings({"unchecked"})
        private Provider(String modId, ResourceKey<? extends Registry<T>> key) {
            this.modId = modId;

            final var reg = BuiltInRegistries.REGISTRY.get(key.identifier());
            if (reg.isEmpty()) {
                throw new RuntimeException("Registry with name " + key.identifier() + " was not found!");
            }
            registry = (Registry<T>) reg.get();
        }

        private Provider(String modId, Registry<T> registry) {
            this.modId = modId;
            this.registry = registry;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <I extends T> FERegistryObject<I> register(String name, Supplier<? extends I> supplier) {
            final var rl = Identifier.fromNamespaceAndPath(modId, name);
            final var obj = Registry.register(registry, rl, supplier.get());
            final var ro = new FERegistryObject<I>() {
                final ResourceKey<I> key = ResourceKey.create((ResourceKey<? extends Registry<I>>) registry.key(), rl);

                @Override
                public ResourceKey<I> getResourceKey() {
                    return key;
                }

                @Override
                public Identifier getId() {
                    return rl;
                }

                @Override
                public I get() {
                    return obj;
                }

                @Override
                public Holder<I> asHolder() {
                    if (registry.get(getId()).isEmpty()) {
                        throw new RuntimeException("Registry with name " + key.identifier() + " was not found!");
                    }
                    return (Holder<I>) registry.get(getId()).get();
                }
            };
            entries.add((FERegistryObject<T>) ro);
            return ro;
        }

        @Override
        public Collection<FERegistryObject<T>> getEntries() {
            return entriesView;
        }

        @Override
        public String getModId() {
            return modId;
        }
    }
}
