package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.level.VirtualLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.BlockTransformer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockTransformers;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class StripperItem extends FilterDeviceItem {

    public StripperItem(Properties properties) {
        super(properties);
    }

    public static Optional<BlockState> getAxeBlockState(Player player, BlockState originalState) {
        VirtualLevel virtualLevel = new VirtualLevel(player.level(), new BlockPos(0, 0, 0), originalState);

        if (player.level().registryAccess().get(BlockTransformers.AXE).isPresent()) {
            Holder.Reference<BlockTransformer> transformer = player.level().registryAccess().get(BlockTransformers.AXE).get();
            for (BlockTransformer.BlockTransformData data : transformer.value().transforms()) {
                BlockState test = data.blockStateProvider().value().getOptionalState(virtualLevel, virtualLevel.getRandom(), new BlockPos(0, 0, 0));
                if (test != null)
                    return Optional.of(test);
            }
        }
        return Optional.empty();
    }

    @Override
    protected boolean checkStackIsValidOrEmpty(Player player, ItemStack stack) {
        if (stack.isEmpty()) return true;
        if (!(stack.getItem() instanceof BlockItem blockItem)) return false;

        return !stack.is(FETags.STRIPPER_IGNORE_ITEMS) && getAxeBlockState(player, blockItem.getBlock().defaultBlockState()).isPresent();
    }
}
