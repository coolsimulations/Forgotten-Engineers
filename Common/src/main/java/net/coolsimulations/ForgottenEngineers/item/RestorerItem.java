package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.tooltip.RestorerTooltip;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.*;

public class RestorerItem extends BundleItem {

    public RestorerItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean overrideStackedOnOther(@NotNull ItemStack restorer, Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        return checkStackIsRepairMaterialOrEmpty(slot.getItem()) && super.overrideStackedOnOther(restorer, slot, action, player);
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack restorer, @NotNull ItemStack stack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        return checkStackIsRepairMaterialOrEmpty(stack) && super.overrideOtherStackedOnMe(restorer, stack, slot, action, player, access);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return ARGB.color(255, 109, 176, 166);
    }

    @Override
    public @NonNull Optional<TooltipComponent> getTooltipImage(final ItemStack bundle) {
        TooltipDisplay display = (TooltipDisplay)bundle.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
        return !display.shows(DataComponents.BUNDLE_CONTENTS) ? Optional.empty() : Optional.ofNullable((BundleContents)bundle.get(DataComponents.BUNDLE_CONTENTS)).map(RestorerTooltip::new);
    }

    protected boolean checkStackIsRepairMaterialOrEmpty(ItemStack stack) {
        for (Map.Entry<ResourceKey<Item>, Item> item : BuiltInRegistries.ITEM.entrySet())
            if (!stack.is(FETags.RESTORER_IGNORE_MATERIALS) && (stack.isEmpty() || new ItemStack(item.getValue()).isValidRepairItem(stack)))
                return true;
        return false;
    }

    @Override
    public void inventoryTick(@NonNull ItemStack stack, @NonNull ServerLevel level, @NonNull Entity entity, @Nullable EquipmentSlot selected) {
        if (entity instanceof LivingEntity)
            for (EquipmentSlot slot : EquipmentSlotGroup.ANY)
                restoreDamagedItems(entity, stack, ((LivingEntity) entity).getItemBySlot(slot));
        if (entity instanceof Player)
            for (ItemStack inventoryStack : ((Player) entity).getInventory().getNonEquipmentItems())
                restoreDamagedItems(entity, stack, inventoryStack);
    }

    protected void restoreDamagedItems(Entity entity, ItemStack restorer, ItemStack inventoryStack) {
        if (!inventoryStack.is(FETags.RESTORER_IGNORE_TOOLS) && inventoryStack.isDamageableItem() && inventoryStack.isDamaged()) {
            for (ItemStackTemplate stack : Objects.requireNonNull(restorer.get(DataComponents.BUNDLE_CONTENTS)).items()) {
                ItemStack material = stack.create();
                if (material.isEmpty())
                    continue;
                if (inventoryStack.getItem().getDefaultInstance().isValidRepairItem(material)) {
                    boolean flag = false;
                    if (entity instanceof Player)
                        flag = ((Player) entity).getCooldowns().isOnCooldown(restorer);
                    if (!flag) {
                        int repairAmount = inventoryStack.getMaxDamage() / 4;
                        if (inventoryStack.getDamageValue() >= repairAmount) {
                            inventoryStack.setDamageValue(inventoryStack.getDamageValue() - repairAmount);
                            entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ANVIL_USE, entity.getSoundSource(), 0.8F, 0.8F + entity.level().getRandom().nextFloat() * 0.4F);
                            removeMaterialFromRestorer(restorer, material);
                            if (entity instanceof Player)
                                ((Player) entity).getCooldowns().addCooldown(restorer, 80);
                        }
                    }
                }
            }
        }
    }

    protected void removeMaterialFromRestorer(ItemStack restorer, ItemStack material) {
        BundleContents contents = restorer.get(DataComponents.BUNDLE_CONTENTS);
        if (contents != null && !contents.isEmpty()) {
            List<ItemStackTemplate> newList = new ArrayList<>(contents.itemCopyStream().map(ItemStackTemplate::fromStack).toList());
            for (int i = 0; i < contents.size(); i++) {
                ItemStack restorerItem = newList.get(i).create();
                if (restorerItem.is(material.getItem())) {
                    restorerItem.shrink(1);
                    if (restorerItem.isEmpty())
                        newList.remove(i);
                    else
                        newList.set(i, ItemStackTemplate.fromNonEmptyStack(restorerItem));
                    restorer.set(DataComponents.BUNDLE_CONTENTS, new BundleContents(newList));
                    break;
                }
            }
        }
    }
}
