package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.event.FEEntityEvents;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.coolsimulations.ForgottenEngineers.item.RouterItem;
import net.coolsimulations.ForgottenEngineers.sounds.ForgottenEngineersSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BundleContents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class ForgottenEngineersCommon {

    public static final String MOD_ID = "forgottenengineers";
    public static final String MOD_NAME = "Forgotten Engineers";
    public static final String MOD_VERSION = "1.1.0";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    //public static final FEConfigCommon CONFIG = load(FEConfigCommon.class);

    public void init() {
        ForgottenEngineersItems.init();
        ForgottenEngineersSounds.init();

        FEEntityEvents.PLAYER_ITEM_ENTITY_PICKUP.register(ForgottenEngineersCommon::onPlayerItemEntityPickup);
    }

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }

    private static boolean onPlayerItemEntityPickup(Player player, ItemEntity item) {
        Map<Integer, ItemStack> INVENTORY_ROUTER = new HashMap<>();
        for (int i = 0; i < player.getInventory().getNonEquipmentItems().size(); i++) {
            ItemStack stack = player.getInventory().getNonEquipmentItems().get(i);
            if (stack.getItem() instanceof RouterItem)
                INVENTORY_ROUTER.put(i, stack);
        }
        for (Map.Entry<Integer, ItemStack> entry : INVENTORY_ROUTER.entrySet()) {
            if (entry.getValue().isEmpty() || item.getItem().is(FETags.ROUTER_IGNORE_ITEMS))
                continue;
            boolean matchesFilter = false;
            BundleContents.Mutable contents = new BundleContents.Mutable(entry.getValue().getOrDefault(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY));
            for (ItemInstance filter : RouterItem.getFilterItems(contents.items)) {
                if (!matchesFilter)
                    matchesFilter = RouterItem.matchesFilter((ItemStack)filter, item.getItem());
            }
            if (matchesFilter) {
                if (!item.level().isClientSide()) {
                    if (!item.hasPickUpDelay() && (item.getOwner() == null || item.getOwner().getUUID().equals(player.getUUID()))) {
                        RouterItem.Result result = RouterItem.addItemToShulker(entry.getValue(), item.getItem());
                        player.take(item, item.getItem().getCount() - result.stack().getCount());
                        player.awardStat(Stats.ITEM_PICKED_UP.get(item.getItem().getItem()), item.getItem().getCount() - result.stack().getCount());
                        player.onItemPickup(item);
                        if (result.stack().isEmpty()) {
                            player.getInventory().getNonEquipmentItems().set(entry.getKey(), result.router());
                            item.discard();
                            return false;
                        } else {
                            item.getItem().setCount(result.stack().getCount());
                        }
                    }
                }
            }
        }
        return true;
    }
}
