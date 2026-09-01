package net.coolsimulations.ForgottenEngineers.item.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.component.BundleContents;

public record FuelCarrierTooltip(BundleContents contents) implements TooltipComponent {
}
