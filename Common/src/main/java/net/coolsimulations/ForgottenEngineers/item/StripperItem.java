package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.FEServices;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public class StripperItem extends FilterDeviceItem {

    public StripperItem(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean checkStackIsValidOrEmpty(Player player, ItemStack stack) {
        if (stack.isEmpty()) return true;
        if (!(stack.getItem() instanceof BlockItem blockItem)) return false;

        return !stack.is(FETags.STRIPPER_IGNORE_ITEMS) && FEServices.REGISTRY.getAxeBlockState(player, blockItem.getBlock().defaultBlockState()).isPresent();
    }
}
