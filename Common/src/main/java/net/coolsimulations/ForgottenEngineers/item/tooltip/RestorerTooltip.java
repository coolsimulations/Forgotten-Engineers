package net.coolsimulations.ForgottenEngineers.item.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.component.BundleContents;

public record RestorerTooltip(BundleContents contents) implements TooltipComponent {
}
