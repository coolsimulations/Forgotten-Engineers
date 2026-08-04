package net.coolsimulations.ForgottenEngineers.mixin;

import net.coolsimulations.ForgottenEngineers.event.FERenderEvents;
import net.minecraft.client.gui.ItemSlotMouseAction;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin extends Screen {

    protected AbstractContainerScreenMixin(Component title) {
        super(title);
    }

    @Shadow
    protected abstract void addItemSlotMouseAction(ItemSlotMouseAction itemSlotMouseAction);

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;addItemSlotMouseAction(Lnet/minecraft/client/gui/ItemSlotMouseAction;)V"))
    private void addMouseAction(CallbackInfo ci) {
        FERenderEvents.MOUSE_ACTION.post().handle(this::addItemSlotMouseAction);
    }
}
