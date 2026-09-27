package net.coolsimulations.ForgottenEngineers;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;

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

        default int getFuelTime(ItemStack item, Level level, RecipeType<?> recipeType) {
            if (!item.has(DataComponents.COOKING_FUEL))
                return 0;

            if (level instanceof ServerLevel serverLevel)
                return ResolvableInt.getFromItem(item, DataComponents.COOKING_FUEL, CookingFuel::burnTime, new LootContext.Builder(new LootParams.Builder(serverLevel).create(LootContextParamSets.EMPTY)).create(Optional.empty()), 0);

            return 0;
        }

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
