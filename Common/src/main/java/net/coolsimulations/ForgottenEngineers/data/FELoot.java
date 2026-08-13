package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.List;
import java.util.function.Consumer;

public class FELoot {

    public static void generateWisdomLoot(Consumer<WisdomLoot> loot) {
        loot.accept(WisdomLoot.RESTORATION_WISDOM_VILLAGE);
        loot.accept(WisdomLoot.RESTORATION_WISDOM_MINESHAFT);
        loot.accept(WisdomLoot.DISTRIBUTION_WISDOM_END_CITY);
        loot.accept(WisdomLoot.DISTRIBUTION_WISDOM_STRONGHOLD);
    }

    public record WisdomLoot(String name, List<Identifier> lootTables, Item item, int weight) {

        public static final WisdomLoot RESTORATION_WISDOM_VILLAGE = new WisdomLoot("restoration_wisdom_from_village", List.of(BuiltInLootTables.VILLAGE_TOOLSMITH.identifier(), BuiltInLootTables.VILLAGE_WEAPONSMITH.identifier()), FEItems.RESTORATION_WISDOM, 4);
        public static final WisdomLoot RESTORATION_WISDOM_MINESHAFT = new WisdomLoot("restoration_wisdom_from_mineshaft", List.of(BuiltInLootTables.ABANDONED_MINESHAFT.identifier()), FEItems.RESTORATION_WISDOM, 6);

        public static final WisdomLoot DISTRIBUTION_WISDOM_END_CITY = new WisdomLoot("distribution_wisdom_from_end_city", List.of(BuiltInLootTables.END_CITY_TREASURE.identifier()), FEItems.DISTRIBUTION_WISDOM, 3);
        public static final WisdomLoot DISTRIBUTION_WISDOM_STRONGHOLD = new WisdomLoot("distribution_wisdom_from_stronghold", List.of(BuiltInLootTables.STRONGHOLD_LIBRARY.identifier()), FEItems.DISTRIBUTION_WISDOM, 6);
    }
}
