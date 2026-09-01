package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.FERegistration.FERegistrationProvider;
import net.coolsimulations.ForgottenEngineers.FERegistration.FERegistryObject;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;
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
    public TagKey<Item> getDyeTag(DyeColor color) {
        return switch (color) {
            case BLACK -> ConventionalItemTags.BLACK_DYES;
            case BLUE -> ConventionalItemTags.BLUE_DYES;
            case BROWN -> ConventionalItemTags.BROWN_DYES;
            case CYAN -> ConventionalItemTags.CYAN_DYES;
            case GRAY -> ConventionalItemTags.GRAY_DYES;
            case GREEN -> ConventionalItemTags.GREEN_DYES;
            case LIGHT_BLUE -> ConventionalItemTags.LIGHT_BLUE_DYES;
            case LIGHT_GRAY -> ConventionalItemTags.LIGHT_GRAY_DYES;
            case LIME -> ConventionalItemTags.LIME_DYES;
            case MAGENTA -> ConventionalItemTags.MAGENTA_DYES;
            case ORANGE -> ConventionalItemTags.ORANGE_DYES;
            case PINK -> ConventionalItemTags.PINK_DYES;
            case PURPLE -> ConventionalItemTags.PURPLE_DYES;
            case RED -> ConventionalItemTags.RED_DYES;
            case WHITE -> ConventionalItemTags.WHITE_DYES;
            case YELLOW -> ConventionalItemTags.YELLOW_DYES;
        };
    }

    @Override
    public TagKey<Item> getDyedTag(DyeColor color) {
        return switch (color) {
            case BLACK -> ConventionalItemTags.BLACK_DYED;
            case BLUE -> ConventionalItemTags.BLUE_DYED;
            case BROWN -> ConventionalItemTags.BROWN_DYED;
            case CYAN -> ConventionalItemTags.CYAN_DYED;
            case GRAY -> ConventionalItemTags.GRAY_DYED;
            case GREEN -> ConventionalItemTags.GREEN_DYED;
            case LIGHT_BLUE -> ConventionalItemTags.LIGHT_BLUE_DYED;
            case LIGHT_GRAY -> ConventionalItemTags.LIGHT_GRAY_DYED;
            case LIME -> ConventionalItemTags.LIME_DYED;
            case MAGENTA -> ConventionalItemTags.MAGENTA_DYED;
            case ORANGE -> ConventionalItemTags.ORANGE_DYED;
            case PINK -> ConventionalItemTags.PINK_DYED;
            case PURPLE -> ConventionalItemTags.PURPLE_DYED;
            case RED -> ConventionalItemTags.RED_DYED;
            case WHITE -> ConventionalItemTags.WHITE_DYED;
            case YELLOW -> ConventionalItemTags.YELLOW_DYED;
        };
    }

    @Override
    public TagKey<Item> getGunpowders() {
        return ConventionalItemTags.GUNPOWDERS;
    }

    @Override
    public Optional<AxeResult> getAxeBlockState(Player player, BlockState originalState) {
        BlockState fabricStripState = StrippableBlockRegistry.getStrippedBlockState(originalState);

        if (fabricStripState != null && !fabricStripState.is(originalState.getBlock()))
            return Optional.of(new AxeResult(fabricStripState, AxeType.STRIP));

        Optional<BlockState> vanillaScrapeState = WeatheringCopper.getPrevious(originalState);

        if (vanillaScrapeState.isPresent() && !vanillaScrapeState.get().is(originalState.getBlock()))
            return Optional.of(new AxeResult(vanillaScrapeState.get(), AxeType.SCRAPE));

        Block vanillaWaxState = HoneycombItem.WAX_OFF_BY_BLOCK.get().get(originalState.getBlock());

        if (vanillaWaxState != null && !vanillaWaxState.defaultBlockState().is(originalState.getBlock()))
            return Optional.of(new AxeResult(vanillaWaxState.defaultBlockState(), AxeType.WAX_OFF));

        return Optional.empty();
    }

    @Override
    public int getFuelTime(ItemStack item, Level level, RecipeType<?> recipeType) {
        return level.fuelValues().burnDuration(item);
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
