package net.coolsimulations.ForgottenEngineers.client;

import com.mojang.serialization.MapCodec;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.item.FuelCarrierItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Map;

public class InductionFurnaceHasFuel implements ConditionalItemModelProperty {

    public static final MapCodec<InductionFurnaceHasFuel> MAP_CODEC = MapCodec.unit(new InductionFurnaceHasFuel());

    @Override
    public boolean get(final ItemStack furnace, final @Nullable ClientLevel level, final @Nullable LivingEntity owner, final int seed, final @NonNull ItemDisplayContext displayContext) {

        if (!furnace.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).isEmpty())
            if (owner instanceof Player player) {
                Map<Integer, ItemStack> carriers = ForgottenEngineersCommon.getInventoryItems(player, stack -> stack.is(FEItems.FUEL_CARRIER));
                if (!carriers.isEmpty())
                    for (Map.Entry<Integer, ItemStack> entry : carriers.entrySet())
                        if (FuelCarrierItem.hasFuel(entry.getValue()))
                            return true;
            }

        return false;
    }

    @Override
    public @NonNull MapCodec<InductionFurnaceHasFuel> type() {
        return MAP_CODEC;
    }
}
