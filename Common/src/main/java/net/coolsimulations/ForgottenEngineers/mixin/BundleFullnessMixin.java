package net.coolsimulations.ForgottenEngineers.mixin;

import net.coolsimulations.ForgottenEngineers.item.RouterItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.BundleFullness;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BundleFullness.class)
public class BundleFullnessMixin {

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void getRouter(ItemStack itemStack, ClientLevel level, ItemOwner owner, int seed, CallbackInfoReturnable<Float> cir) {
        if (itemStack.getItem() instanceof RouterItem)
            cir.setReturnValue(RouterItem.getFullnessDisplay(itemStack));
    }
}
