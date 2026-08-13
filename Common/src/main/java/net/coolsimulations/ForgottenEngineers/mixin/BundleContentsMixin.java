package net.coolsimulations.ForgottenEngineers.mixin;

import com.mojang.serialization.DataResult;
import net.coolsimulations.ForgottenEngineers.item.RouterItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleContents.class)
public class BundleContentsMixin {

    @Inject(method = "getWeight", at = @At("RETURN"), cancellable = true)
    private static void getShulkerWeight(ItemInstance item, CallbackInfoReturnable<DataResult<Fraction>> cir) {
        if (RouterItem.isShulker(RouterItem.convertFromItemInstance(item)))
            cir.setReturnValue(DataResult.success(Fraction.ZERO));
    }
}
