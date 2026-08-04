package net.coolsimulations.ForgottenEngineers.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public ForgottenEngineersGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, ForgottenEngineersCommon.MOD_ID, registries);
    }

    @Override
    protected void start(HolderLookup.@NonNull Provider registries) {
        FELoot.generateWisdomLoot((wisdomLoot -> {
            LootItemCondition.Builder[] builders = wisdomLoot.lootTables().stream()
                    .map(LootTableIdCondition::builder)
                    .toArray(LootItemCondition.Builder[]::new);

            this.add(wisdomLoot.name(), new AddItemModifier(new LootItemCondition[] {
                    wisdomLoot.lootTables().size() == 1 ? new LootTableIdCondition.Builder(wisdomLoot.lootTables().getFirst()).build() : AnyOfCondition.anyOf(builders).build()
            }, wisdomLoot.item(), wisdomLoot.weight()));
        }));
    }

    public static class AddItemModifier extends LootModifier {
        public static final MapCodec<AddItemModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
                LootModifier.codecStart(inst).and(
                        BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(e -> e.item)
                ).and(Codec.INT.fieldOf("weight").forGetter(e -> e.weight)
                ).apply(inst, AddItemModifier::new));
        private final Item item;
        private final int weight;

        public AddItemModifier(LootItemCondition[] conditionsIn, Item item, int weight) {
            super(conditionsIn);
            this.item = item;
            this.weight = weight;
        }

        @Override
        protected @NonNull ObjectArrayList<ItemStack> doApply(LootTable table, ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
            for (LootItemCondition condition : this.conditions) {
                if(!condition.test(lootContext)) {
                    return generatedLoot;
                }
            }
            if (lootContext.getRandom().nextFloat() <= (1.0F/(1 + weight))) {
                generatedLoot.add(new ItemStack(this.item));
            }
            return generatedLoot;
        }

        @Override
        public MapCodec<? extends IGlobalLootModifier> codec() {
            return CODEC;
        }
    }
}
