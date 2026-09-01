package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.FERegistration.FERegistrationProvider;
import net.coolsimulations.ForgottenEngineers.FERegistration.FERegistryObject;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.ModList;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.common.DataMapHooks;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.*;
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
    public TagKey<Item> getDyeTag(DyeColor color) {
        return switch (color) {
            case BLACK -> Tags.Items.DYES_BLACK;
            case BLUE -> Tags.Items.DYES_BLUE;
            case BROWN -> Tags.Items.DYES_BROWN;
            case CYAN -> Tags.Items.DYES_CYAN;
            case GRAY -> Tags.Items.DYES_GRAY;
            case GREEN -> Tags.Items.DYES_GREEN;
            case LIGHT_BLUE -> Tags.Items.DYES_LIGHT_BLUE;
            case LIGHT_GRAY -> Tags.Items.DYES_LIGHT_GRAY;
            case LIME -> Tags.Items.DYES_LIME;
            case MAGENTA -> Tags.Items.DYES_MAGENTA;
            case ORANGE -> Tags.Items.DYES_ORANGE;
            case PINK -> Tags.Items.DYES_PINK;
            case PURPLE -> Tags.Items.DYES_PURPLE;
            case RED -> Tags.Items.DYES_RED;
            case WHITE -> Tags.Items.DYES_WHITE;
            case YELLOW -> Tags.Items.DYES_YELLOW;
        };
    }

    @Override
    public TagKey<Item> getDyedTag(DyeColor color) {
        return switch (color) {
            case BLACK -> Tags.Items.DYED_BLACK;
            case BLUE -> Tags.Items.DYED_BLUE;
            case BROWN -> Tags.Items.DYED_BROWN;
            case CYAN -> Tags.Items.DYED_CYAN;
            case GRAY -> Tags.Items.DYED_GRAY;
            case GREEN -> Tags.Items.DYED_GREEN;
            case LIGHT_BLUE -> Tags.Items.DYED_LIGHT_BLUE;
            case LIGHT_GRAY -> Tags.Items.DYED_LIGHT_GRAY;
            case LIME -> Tags.Items.DYED_LIME;
            case MAGENTA -> Tags.Items.DYED_MAGENTA;
            case ORANGE -> Tags.Items.DYED_ORANGE;
            case PINK -> Tags.Items.DYED_PINK;
            case PURPLE -> Tags.Items.DYED_PURPLE;
            case RED -> Tags.Items.DYED_RED;
            case WHITE -> Tags.Items.DYED_WHITE;
            case YELLOW -> Tags.Items.DYED_YELLOW;
        };
    }

    @Override
    public TagKey<Item> getGunpowders() {
        return Tags.Items.GUNPOWDERS;
    }

    @Override
    public Optional<AxeResult> getAxeBlockState(Player player, BlockState originalState) {
        BlockState neoforgeStripState = originalState.getToolModifiedState(new UseOnContext(player, player.getUsedItemHand(), new BlockHitResult(Vec3.ZERO, player.getDirection(), BlockPos.ZERO, false)), ItemAbilities.AXE_STRIP, true);
        BlockState vanillaStripState = AxeItem.getAxeStrippingState(originalState);

        if (neoforgeStripState != null && !neoforgeStripState.is(originalState.getBlock()))
            return Optional.of(new AxeResult(neoforgeStripState, AxeType.STRIP));
        else if (vanillaStripState != null && !vanillaStripState.is(originalState.getBlock()))
            return Optional.of(new AxeResult(vanillaStripState, AxeType.STRIP));

        BlockState neoforgeScrapeState = originalState.getToolModifiedState(new UseOnContext(player, player.getUsedItemHand(), new BlockHitResult(Vec3.ZERO, player.getDirection(), BlockPos.ZERO, false)), ItemAbilities.AXE_SCRAPE, true);
        Optional<BlockState> vanillaScrapeState = WeatheringCopper.getPrevious(originalState);

        if (neoforgeScrapeState != null && !neoforgeScrapeState.is(originalState.getBlock()))
            return Optional.of(new AxeResult(neoforgeScrapeState, AxeType.SCRAPE));
        else if (vanillaScrapeState.isPresent() && !vanillaScrapeState.get().is(originalState.getBlock()))
            return Optional.of(new AxeResult(vanillaScrapeState.get(), AxeType.SCRAPE));

        BlockState neoforgeWaxState = originalState.getToolModifiedState(new UseOnContext(player, player.getUsedItemHand(), new BlockHitResult(Vec3.ZERO, player.getDirection(), BlockPos.ZERO, false)), ItemAbilities.AXE_WAX_OFF, true);
        Block vanillaWaxState = DataMapHooks.getBlockUnwaxed(originalState.getBlock());

        if (neoforgeWaxState != null && !neoforgeWaxState.is(originalState.getBlock()))
            return Optional.of(new AxeResult(neoforgeWaxState, AxeType.WAX_OFF));
        else if (vanillaWaxState != null && !vanillaWaxState.defaultBlockState().is(originalState.getBlock()))
            return Optional.of(new AxeResult(vanillaWaxState.defaultBlockState(), AxeType.WAX_OFF));

        return Optional.empty();
    }

    @Override
    public int getFuelTime(ItemStack item, Level level, RecipeType<?> recipeType) {
        return item.getBurnTime(recipeType, level.fuelValues());
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
