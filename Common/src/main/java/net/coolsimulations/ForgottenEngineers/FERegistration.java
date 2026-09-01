package net.coolsimulations.ForgottenEngineers;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

public class FERegistration {

    public interface IFERegistry {
        Item getItem(Identifier location);

        SoundEvent getSoundEvent(Identifier location);

        TagKey<Item> getDyeTag(DyeColor color);

        TagKey<Item> getDyedTag(DyeColor color);

        TagKey<Item> getGunpowders();

        Optional<AxeResult> getAxeBlockState(Player player, BlockState originalState);

        int getFuelTime(ItemStack item, Level level, RecipeType<?> recipeType);

        PlatformType getPlatformType();

        record AxeResult(BlockState resultState, AxeType type) {}

        enum AxeType {
            STRIP,
            SCRAPE,
            WAX_OFF
        }
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
