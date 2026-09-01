package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.tooltip.MenderTooltip;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class MenderItem extends StorageDeviceItem {

    public MenderItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return (!contents.isEmpty() && isMendingTool(contents.items().getFirst().create())) ? contents.items().getFirst().create().isDamaged() ? Mth.hsvToRgb(Math.max(0.0F, ((float)contents.items().getFirst().create().getMaxDamage() - contents.items().getFirst().create().getDamageValue()) / contents.items().getFirst().create().getMaxDamage()) / 3.0F, 1.0F, 1.0F) : super.getBarColor(stack) : super.getBarColor(stack);
    }

    @Override
    public int getBarWidth(final ItemStack stack) {
        BundleContents contents = stack.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);
        return (!contents.isEmpty() && isMendingTool(contents.items().getFirst().create())) ? Mth.clamp(Math.round(13.0F - contents.items().getFirst().create().getDamageValue() * 13.0F / contents.items().getFirst().create().getMaxDamage()), 0, 13) : super.getBarWidth(stack);
    }

    @Override
    public @NonNull Optional<TooltipComponent> getTooltipImage(final ItemStack bundle) {
        TooltipDisplay display = bundle.getOrDefault(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT);
        return !display.shows(DataComponents.BUNDLE_CONTENTS) ? Optional.empty() : Optional.ofNullable(bundle.get(DataComponents.BUNDLE_CONTENTS)).map(MenderTooltip::new);
    }

    @Override
    public void inventoryTick(@NonNull ItemStack mender, @NonNull ServerLevel level, @NonNull Entity entity, @Nullable EquipmentSlot selected) {
        if (!(entity instanceof ServerPlayer player)) return;
        if (player.getCooldowns().isOnCooldown(mender)) return;

        BundleContents contents = mender.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY);

        if (contents.isEmpty()) return;

        BundleContents.Mutable mutable = new BundleContents.Mutable(contents);

        for (int i = 0; i < mutable.items.size(); i++) {
            ItemStack item = mutable.items.get(i);

            if (isGlassBottle(item)) {
                int requiredXP = 10;
                if (getTotalExperience(player) >= requiredXP) {
                    item.shrink(1);
                    if (!item.isEmpty())
                        mutable.items.set(i, item);
                    else
                        mutable.items.remove(i);
                    mender.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
                    ItemStack result = new ItemStack(Items.EXPERIENCE_BOTTLE);
                    mutable = new BundleContents.Mutable(mender.getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY));
                    int amountAdded = mutable.tryInsert(result);
                    player.awardStat(Stats.ITEM_CRAFTED.get(result.getItem()));
                    player.giveExperiencePoints(-requiredXP);
                    if (amountAdded == 0) {
                        ForgottenEngineersCommon.EnderStack output = ForgottenEngineersCommon.handleDeviceOutput(player, new ForgottenEngineersCommon.EnderStack(false, null, result), ForgottenEngineersCommon.getInventoryItems(player, stack -> stack.is(FETags.ROUTERS)), ForgottenEngineersCommon.getInventoryItems(player, stack -> stack.is(FEItems.ENDER_ROUTER)));
                        ItemStack remaining = output.stack();
                        if (!remaining.isEmpty()) {
                            if (output.isEnder()) {
                                remaining = player.getEnderChestInventory().addItem(remaining);
                                if (!remaining.isEmpty())
                                    player.getInventory().placeItemBackInInventory(remaining);
                            } else
                                player.getInventory().placeItemBackInInventory(remaining);
                        }
                    }
                }
            }

            if (item.isDamaged() && isMendingTool(item)) {
                int xpUsed = repairTool(player, item);
                if (xpUsed > 0) {
                    mutable.items.set(i, item);
                    if (getTotalExperience(player) == 0 || !item.isDamaged()) {
                        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ANVIL_USE, player.getSoundSource(), 0.8F, 0.8F + player.level().getRandom().nextFloat() * 0.4F);
                        player.getCooldowns().addCooldown(mender, 80);
                    }
                }
            }
        }

        mender.set(DataComponents.BUNDLE_CONTENTS, mutable.toImmutable());
    }

    public int getTotalExperience(Player player) {
        int lvl = player.experienceLevel;
        return (lvl <= 15 ? lvl * lvl + 6 * lvl : lvl <= 30 ? (int) (2.5 * lvl * lvl - 40.5 * lvl + 360) : (int) (4.5 * lvl * lvl - 162.5 * lvl + 2220)) + Math.round(player.experienceProgress * player.getXpNeededForNextLevel());
    }

    private int repairTool(final ServerPlayer player, final ItemStack stack) {
        int damage = stack.getDamageValue();
        if (damage <= 0) return 0;
        int availableXp = getTotalExperience(player);
        if (availableXp <= 0) return 0;
        int repairAmount = EnchantmentHelper.modifyDurabilityToRepairFromXp(player.level(), stack, availableXp);
        repairAmount = Math.min(repairAmount, damage);
        if (repairAmount <= 0) return 0;
        int xpUsed = 0;

        for (int xp = 1; xp <= availableXp; xp++) {
            int possibleRepair = EnchantmentHelper.modifyDurabilityToRepairFromXp(player.level(), stack, xp);

            if (possibleRepair >= repairAmount) {
                xpUsed = xp;
                break;
            }
        }

        if (xpUsed <= 0) return 0;

        stack.setDamageValue(damage - repairAmount);
        player.giveExperiencePoints(-xpUsed);

        return xpUsed;
    }

    @Override
    protected boolean checkStackIsValidOrEmpty(Player player, ItemStack stack) {
        if (stack.isEmpty()) return true;

        return !stack.is(FETags.MENDER_IGNORE_TOOLS) && (isGlassBottle(stack) || isMendingTool(stack) || stack.is(Items.EXPERIENCE_BOTTLE));
    }

    public static boolean isGlassBottle(ItemStack stack) {
        return stack.is(Items.GLASS_BOTTLE);
    }

    public static boolean isMendingTool(ItemStack stack) {
        return stack.isDamageableItem() && (stack.isEnchanted() && stack.getEnchantments().keySet().stream().anyMatch(entry -> entry.value().effects().has(EnchantmentEffectComponents.REPAIR_WITH_XP)));
    }
}
