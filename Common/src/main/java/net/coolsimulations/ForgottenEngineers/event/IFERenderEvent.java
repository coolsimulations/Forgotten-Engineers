package net.coolsimulations.ForgottenEngineers.event;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.gui.ItemSlotMouseAction;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.client.renderer.item.properties.conditional.ItemModelPropertyTest;
import net.minecraft.resources.Identifier;

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

    interface ItemProperties extends IFEEvent {
        void handle(BiConsumer<Identifier, MapCodec<? extends ConditionalItemModelProperty>> property);
    }
}
