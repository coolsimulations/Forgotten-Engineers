package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.FERegistration.FERegistrationProvider;
import net.coolsimulations.ForgottenEngineers.FERegistration.FERegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
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
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
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
        BlockState forgeStripState = originalState.getToolModifiedState(new UseOnContext(player, player.getUsedItemHand(), new BlockHitResult(Vec3.ZERO, player.getDirection(), BlockPos.ZERO, false)), ToolActions.AXE_STRIP, true);
        BlockState vanillaStripState = AxeItem.getAxeStrippingState(originalState);

        if (forgeStripState != null && !forgeStripState.is(originalState.getBlock()))
            return Optional.of(new AxeResult(forgeStripState, AxeType.STRIP));
        else if (vanillaStripState != null && !vanillaStripState.is(originalState.getBlock()))
            return Optional.of(new AxeResult(vanillaStripState, AxeType.STRIP));

        BlockState forgeScrapeState = originalState.getToolModifiedState(new UseOnContext(player, player.getUsedItemHand(), new BlockHitResult(Vec3.ZERO, player.getDirection(), BlockPos.ZERO, false)), ToolActions.AXE_SCRAPE, true);
        Optional<BlockState> vanillaScrapeState = WeatheringCopper.getPrevious(originalState);

        if (forgeScrapeState != null && !forgeScrapeState.is(originalState.getBlock()))
            return Optional.of(new AxeResult(forgeScrapeState, AxeType.SCRAPE));
        else if (vanillaScrapeState.isPresent() && !vanillaScrapeState.get().is(originalState.getBlock()))
            return Optional.of(new AxeResult(vanillaScrapeState.get(), AxeType.SCRAPE));

        BlockState forgeWaxState = originalState.getToolModifiedState(new UseOnContext(player, player.getUsedItemHand(), new BlockHitResult(Vec3.ZERO, player.getDirection(), BlockPos.ZERO, false)), ToolActions.AXE_WAX_OFF, true);
        Block vanillaWaxState = HoneycombItem.WAX_OFF_BY_BLOCK.get().get(originalState.getBlock());

        if (forgeWaxState != null && !forgeWaxState.is(originalState.getBlock()))
            return Optional.of(new AxeResult(forgeWaxState, AxeType.WAX_OFF));
        else if (vanillaWaxState != null && !vanillaWaxState.defaultBlockState().is(originalState.getBlock()))
            return Optional.of(new AxeResult(vanillaWaxState.defaultBlockState(), AxeType.WAX_OFF));

        return Optional.empty();
    }

    @Override
    public int getFuelTime(ItemStack item, Level level, RecipeType<?> recipeType) {
        return level.fuelValues().burnDuration(item, recipeType);
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
