package net.coolsimulations.ForgottenEngineers.client;

import com.mojang.serialization.MapCodec;
import net.coolsimulations.ForgottenEngineers.item.CombustorItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class CombustorHasFuel implements ConditionalItemModelProperty {

    public static final MapCodec<CombustorHasFuel> MAP_CODEC = MapCodec.unit(new CombustorHasFuel());

    @Override
    public boolean get(final @NonNull ItemStack combustor, final @Nullable ClientLevel level, final @Nullable LivingEntity owner, final int seed, final @NonNull ItemDisplayContext displayContext) {

        if (owner instanceof Player player)
            if (player.getCooldowns().isOnCooldown(combustor))
                return false;

        return CombustorItem.hasFuelForShot(combustor);
    }

    @Override
    public @NonNull MapCodec<CombustorHasFuel> type() {
        return MAP_CODEC;
    }
}
