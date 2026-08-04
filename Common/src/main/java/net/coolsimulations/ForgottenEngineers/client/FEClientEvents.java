package net.coolsimulations.ForgottenEngineers.client;

import net.coolsimulations.ForgottenEngineers.event.FERenderEvents;
import net.coolsimulations.ForgottenEngineers.item.tooltip.RestorerTooltip;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ItemSlotMouseAction;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class FEClientEvents {

    public static void init() {
        FERenderEvents.TOOLTIP_COMPONENT.register(FEClientEvents::registerTooltipComponent);
        FERenderEvents.MOUSE_ACTION.register(FEClientEvents::registerAction);
    }

    public static void registerTooltipComponent(BiConsumer<Class<? extends TooltipComponent>, Function<TooltipComponent, ClientTooltipComponent>> register) {
        register.accept(RestorerTooltip.class, tooltip -> new ClientRestorerTooltip(((RestorerTooltip) tooltip).contents()));
    }

    public static void registerAction(Consumer<ItemSlotMouseAction> register) {
        register.accept(new RestorerMouseActions(Minecraft.getInstance()));
    }
}
