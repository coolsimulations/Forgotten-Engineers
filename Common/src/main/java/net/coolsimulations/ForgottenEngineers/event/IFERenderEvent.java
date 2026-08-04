package net.coolsimulations.ForgottenEngineers.event;

import net.minecraft.client.gui.ItemSlotMouseAction;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public interface IFERenderEvent extends IFEEvent {

    interface TooltipComponent extends IFEEvent {
        void handle(BiConsumer<Class<? extends net.minecraft.world.inventory.tooltip.TooltipComponent>, Function<net.minecraft.world.inventory.tooltip.TooltipComponent, ClientTooltipComponent>> register);
    }

    interface MouseAction extends IFEEvent {
        void handle(Consumer<ItemSlotMouseAction> action);
    }
}
