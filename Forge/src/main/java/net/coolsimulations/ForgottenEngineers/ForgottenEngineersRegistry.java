package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.FERegistration.FERegistrationProvider;
import net.coolsimulations.ForgottenEngineers.FERegistration.FERegistryObject;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class ForgottenEngineersRegistry implements FERegistrationProvider.Factory, FERegistration.IFERegistry {

    @Override
    public <T> FERegistrationProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
        final var containerOpt = ModList.getModContainerById(modId);
        if (containerOpt.isEmpty())
            throw new NullPointerException("Cannot find mod container for id " + modId);
        final var cont = containerOpt.get();
        if (cont instanceof FMLModContainer fmlModContainer) {
            final var register = DeferredRegister.create(resourceKey, modId);
            register.register(fmlModContainer.getModBusGroup());
            return new Provider<>(modId, register);
        } else {
            throw new ClassCastException("The container of the mod " + modId + " is not a FML one!");
        }
    }

    @Override
    public Item getItem(Identifier location) {
        RegistryObject<Item> item = RegistryObject.create(location, ForgeRegistries.ITEMS);
        if (item.isPresent())
            return item.get();
        return Items.AIR;
    }

    @Override
    public SoundEvent getSoundEvent(Identifier location) {
        RegistryObject<SoundEvent> sound = RegistryObject.create(location, ForgeRegistries.SOUND_EVENTS);
        if (sound.isPresent())
            return sound.get();
        return SoundEvents.EXPERIENCE_ORB_PICKUP;
    }

    @Override
    public FERegistration.PlatformType getPlatformType() {
        return FERegistration.PlatformType.FORGE;
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
                    return obj.getKey();
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
                    return obj.getHolder().orElseThrow();
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
