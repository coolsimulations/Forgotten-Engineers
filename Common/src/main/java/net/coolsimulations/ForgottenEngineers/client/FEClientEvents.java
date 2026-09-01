package net.coolsimulations.ForgottenEngineers.client;

import com.mojang.serialization.MapCodec;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.event.FENetworkEvents;
import net.coolsimulations.ForgottenEngineers.event.FERenderEvents;
import net.coolsimulations.ForgottenEngineers.item.CompressorItem;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.item.InductionFurnaceItem;
import net.coolsimulations.ForgottenEngineers.item.tooltip.*;
import net.coolsimulations.ForgottenEngineers.network.InductionRecipeSyncPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ItemSlotMouseAction;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class FEClientEvents {

    public static void init() {
        FERenderEvents.TOOLTIP_COMPONENT.register(FEClientEvents::registerTooltipComponent);
        FERenderEvents.MOUSE_ACTION.register(FEClientEvents::registerAction);
        FERenderEvents.ITEM_PROPERTIES.register(FEClientEvents::registerProperties);
        FENetworkEvents.CLIENT_LOGOUT.register(FEClientEvents::onClientLogout);
    }

    public static void registerTooltipComponent(BiConsumer<Class<? extends TooltipComponent>, Function<TooltipComponent, ClientTooltipComponent>> register) {
        register.accept(RestorerTooltip.class, tooltip -> new ClientRestorerTooltip(((RestorerTooltip) tooltip).contents()));
        register.accept(RouterTooltip.class, tooltip -> new ClientRouterTooltip(((RouterTooltip) tooltip).contents()));
        register.accept(FuelCarrierTooltip.class, tooltip -> new ClientFuelCarrierTooltip(((FuelCarrierTooltip) tooltip).contents()));

        register.accept(MenderTooltip.class, tooltip -> new ClientMenderTooltip(((MenderTooltip) tooltip).contents()));
        register.accept(FilterDeviceTooltip.class, tooltip -> new ClientFilterDeviceTooltip(((FilterDeviceTooltip) tooltip).contents()));
        register.accept(CombustorTooltip.class, tooltip -> new ClientCombustorTooltip(((CombustorTooltip) tooltip).contents()));
    }

    public static void registerAction(Consumer<ItemSlotMouseAction> register) {
        register.accept(new DeviceMouseActions(Minecraft.getInstance(), slot -> slot.getItem().is(FEItems.RESTORER)));
        register.accept(new RouterMouseActions(Minecraft.getInstance()));
        register.accept(new FilterMouseActions(Minecraft.getInstance(), slot -> slot.getItem().is(FEItems.COMPRESSOR)));
        register.accept(new DeviceMouseActions(Minecraft.getInstance(), slot -> slot.getItem().is(FEItems.FUEL_CARRIER)));
        register.accept(new FilterMouseActions(Minecraft.getInstance(), slot -> slot.getItem().is(FEItems.INDUCTION_FURNACE)));

        register.accept(new DeviceMouseActions(Minecraft.getInstance(), slot -> slot.getItem().is(FEItems.MENDER)));
        register.accept(new FilterMouseActions(Minecraft.getInstance(), slot -> slot.getItem().is(FEItems.STRIPPER)));
        register.accept(new DeviceMouseActions(Minecraft.getInstance(), slot -> slot.getItem().is(FEItems.COMBUSTOR)));
        register.accept(new FilterMouseActions(Minecraft.getInstance(), slot -> slot.getItem().is(FEItems.ENDER_ROUTER)));
    }

    public static void registerProperties(BiConsumer<Identifier, MapCodec<? extends ConditionalItemModelProperty>> register) {
        register.accept(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "induction_furnace"), InductionFurnaceHasFuel.MAP_CODEC);
        register.accept(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "combustor"), CombustorHasFuel.MAP_CODEC);
    }

    public static void onClientLogout() {
        InductionFurnaceItem.INDUCTION_RECIPES.clear();
        CompressorItem.COMPRESSOR_RECIPES.clear();
    }
}
