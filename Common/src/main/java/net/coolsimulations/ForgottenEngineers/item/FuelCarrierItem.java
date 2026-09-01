package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.FEServices;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.tooltip.FuelCarrierTooltip;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.*;

public class FuelCarrierItem extends StorageDeviceItem {

    public FuelCarrierItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NonNull Optional<TooltipComponent> getTooltipImage(final ItemStack bundle) {
        TooltipDisplay display = bundle.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
        return !display.shows(DataComponents.BUNDLE_CONTENTS) ? Optional.empty() : Optional.ofNullable(bundle.get(DataComponents.BUNDLE_CONTENTS)).map(FuelCarrierTooltip::new);
    }

    public static boolean hasFuel(ItemStack fuelCarrier) {
        return !fuelCarrier.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).isEmpty();
    }

    public record FuelOption(int fuelCarrierIndex, int fuelIndex) {}

    public static List<FuelOption> getAllFuels(Level level, Map<Integer, ItemStack> fuelCarriers) {
        List<FuelOption> options = new ArrayList<>();
        for (Map.Entry<Integer, ItemStack> entry : fuelCarriers.entrySet()) {
            if (entry.getValue().isEmpty()) continue;
            BundleContents contents = entry.getValue().getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            if (contents.isEmpty()) continue;
            for (int i = 0; i < contents.items().size(); i++)
                if (!contents.items().get(i).create().isEmpty())
                    options.add(new FuelOption(entry.getKey(), i));
        }
        return sortByLowestFuelType(options, level, fuelCarriers);
    }

    public static void consumeFuel(Item type, Level level, int toConsume, Map<Integer, ItemStack> fuelCarriers) {
        int remaining = toConsume;
        for (FuelOption option : getOptionsForType(type, level, fuelCarriers)) {
            if (remaining <= 0) break;

            ItemStack carrier = fuelCarriers.get(option.fuelCarrierIndex());
            if (carrier.isEmpty()) continue;

            BundleContents contents = carrier.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            BundleContents.Mutable mutable = new BundleContents.Mutable(contents);

            if (option.fuelIndex() < 0 || option.fuelIndex() >= mutable.items.size()) continue;

            ItemStack fuel = mutable.items.get(option.fuelIndex());
            int available = fuel.getCount();
            int take = Math.min(remaining, available);
            fuel.shrink(take);
            remaining -= take;

            if (fuel.isEmpty())
                mutable.items.remove(option.fuelIndex());
            else
                mutable.items.set(option.fuelIndex(), fuel);
            carrier.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
        }
    }

    public static int getFuelDuration(Item type, Level level, int count) {
        return FEServices.REGISTRY.getFuelTime(new ItemStack(type), level, null) * count;
    }

    public static int getCountAcrossOptions(List<FuelOption> options, Map<Integer, ItemStack> fuelCarriers) {
        return options.stream().mapToInt(option -> fuelCarriers.get(option.fuelCarrierIndex()).getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).items().get(option.fuelIndex()).create().getCount()).sum();
    }

    public static List<FuelOption> getOptionsForType(Item type, Level level, Map<Integer, ItemStack> fuelCarriers) {
        List<FuelOption> options = new ArrayList<>();
        for (FuelOption option : getAllFuels(level, fuelCarriers)) {
            ItemStack fuelCarrier = fuelCarriers.get(option.fuelCarrierIndex());
            if (fuelCarrier.isEmpty()) continue;
            BundleContents contents = fuelCarriers.get(option.fuelCarrierIndex()).getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
            if (contents.isEmpty()) continue;
            if (getFuelDuration(contents.items().get(option.fuelIndex()).item().value(), level, 1) == getFuelDuration(type, level, 1))
                options.add(option);

        }
        return sortByLowestFuelType(options, level, fuelCarriers);
    }

    public static List<FuelOption> sortByLowestFuelType(List<FuelOption> options, Level level, Map<Integer, ItemStack> fuelCarriers) {
        if (!options.isEmpty()) {
            options.sort(Comparator.<FuelOption>comparingInt(option -> {
                BundleContents contents = fuelCarriers.get(option.fuelCarrierIndex()).getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
                ItemStackTemplate fuel = contents.items().get(option.fuelIndex());
                return getFuelDuration(fuel.item().value(), level, 1);
            }).thenComparingInt(option -> {
                BundleContents contents = fuelCarriers.get(option.fuelCarrierIndex()).getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
                ItemStackTemplate fuel = contents.items().get(option.fuelIndex());
                return getFuelDuration(fuel.item().value(), level, fuel.count());
            }));
        }
        return options;
    }

    @Override
    protected boolean checkStackIsValidOrEmpty(Player player, ItemStack stack) {
        if (stack.isEmpty()) return true;

        return !stack.is(FETags.FUEL_CARRIER_IGNORE_ITEMS) && FEServices.REGISTRY.getFuelTime(stack, player.level(), null) > 0;
    }
}
