package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.List;
import java.util.function.Consumer;

public class FELoot {

    public static void generateChestLoot(Consumer<WisdomLoot> loot) {
        loot.accept(new WisdomLoot("restoration_wisdom_from_village", List.of(BuiltInLootTables.VILLAGE_TOOLSMITH.identifier(), BuiltInLootTables.VILLAGE_WEAPONSMITH.identifier()), FEItems.RESTORATION_WISDOM, 4));
        loot.accept(new WisdomLoot("restoration_wisdom_from_mineshaft", List.of(BuiltInLootTables.ABANDONED_MINESHAFT.identifier()), FEItems.RESTORATION_WISDOM, 6));
        loot.accept(new WisdomLoot("restoration_wisdom_from_dungeon", List.of(BuiltInLootTables.SIMPLE_DUNGEON.identifier()), FEItems.RESTORATION_WISDOM, 2));

        loot.accept(new WisdomLoot("distribution_wisdom_from_end_city", List.of(BuiltInLootTables.END_CITY_TREASURE.identifier()), FEItems.DISTRIBUTION_WISDOM, 3));
        loot.accept(new WisdomLoot("distribution_wisdom_from_stronghold", List.of(BuiltInLootTables.STRONGHOLD_LIBRARY.identifier()), FEItems.DISTRIBUTION_WISDOM, 6));

        loot.accept(new WisdomLoot("induction_wisdom_from_nether", List.of(BuiltInLootTables.NETHER_BRIDGE.identifier(), BuiltInLootTables.BASTION_TREASURE.identifier()), FEItems.INDUCTION_WISDOM, 4));
        loot.accept(new WisdomLoot("induction_wisdom_from_portal", List.of(BuiltInLootTables.RUINED_PORTAL.identifier()), FEItems.INDUCTION_WISDOM, 6));

        loot.accept(new WisdomLoot("engineers_seal_from_ancient_city", List.of(BuiltInLootTables.ANCIENT_CITY.identifier()), FEItems.ENGINEERS_SEAL, 2));
    }

    public static void generateArcheologyLoot(Consumer<WisdomLoot> loot) {
        loot.accept(new WisdomLoot("compression_wisdom_from_trail_ruins", List.of(BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_RARE.identifier()), FEItems.COMPRESSION_WISDOM, 1, 12));
        loot.accept(new WisdomLoot("compression_wisdom_from_ocean_ruin_warm", List.of(BuiltInLootTables.OCEAN_RUIN_WARM_ARCHAEOLOGY.identifier()), FEItems.COMPRESSION_WISDOM, 1, 15));
    }

    public record WisdomLoot(String name, List<Identifier> lootTables, Item item, int weight, int totalWeight) {
        public WisdomLoot(String name, List<Identifier> lootTables, Item item, int weight) {
            this(name, lootTables, item, weight, 0);
        }
    }
}
