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
import net.neoforged.fml.ModList;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

public class ForgottenEngineersRegistry implements FERegistration.FERegistrationProvider.Factory, FERegistration.IFERegistry {

    @Override
    public <T> FERegistrationProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
        final var containerOpt = ModList.get().getModContainerById(modId);
        if (containerOpt.isEmpty())
            throw new NullPointerException("Cannot find mod container for id " + modId);
        final var cont = containerOpt.get();
        if (cont instanceof FMLModContainer fmlModContainer) {
            final var register = DeferredRegister.create(resourceKey, modId);
            register.register(Objects.requireNonNull(fmlModContainer.getEventBus()));
            return new Provider<>(modId, register);
        } else {
            throw new ClassCastException("The container of the mod " + modId + " is not a FML one!");
        }
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
        return FERegistration.PlatformType.NEOFORGE;
    }

    private static class Provider<T> implements FERegistrationProvider<T> {
        private final String modId;
        private final DeferredRegister<T> registry;

        private final Set<FERegistryObject<T>> entries = new HashSet<>();
        private final Set<FERegistryObject<T>> entriesView = Collections.unmodifiableSet(entries);

        private Provider(String modId, DeferredRegister<T> registry) {
            this.modId = modId;
            this.registry = registry;
        }

        @Override
        public String getModId() {
            return modId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <I extends T> FERegistryObject<I> register(String name, Supplier<? extends I> supplier) {
            final var obj = registry.<I>register(name, supplier);
            final var ro = new FERegistryObject<I>() {

                @Override
                public ResourceKey<I> getResourceKey() {
                    return (ResourceKey<I>) obj.getKey();
                }

                @Override
                public Identifier getId() {
                    return obj.getId();
                }

                @Override
                public I get() {
                    return obj.get();
                }

                @Override
                public Holder<I> asHolder() {
                    return (Holder<I>) obj.asOptional().orElseThrow();
                }
            };
            entries.add((FERegistryObject<T>) ro);
            return ro;
        }

        @Override
        public Set<FERegistryObject<T>> getEntries() {
            return entriesView;
        }
    }

}
