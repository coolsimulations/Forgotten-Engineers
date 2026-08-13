package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class FEModels {

    public static void generateFlatItems(Consumer<Item> flat) {
        flat.accept(FEItems.RESTORATION_WISDOM);
        flat.accept(FEItems.DISTRIBUTION_WISDOM);
        flat.accept(FEItems.RESTORER);
        flat.accept(FEItems.ROUTER);
    }
}
