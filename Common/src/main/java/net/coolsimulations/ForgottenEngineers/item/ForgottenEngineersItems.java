package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.FERegistration;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BundleContents;

import java.util.function.Consumer;

public class ForgottenEngineersItems {

    public static final FERegistration.FERegistrationProvider<Item> ITEMS = FERegistration.FERegistrationProvider.get(BuiltInRegistries.ITEM, ForgottenEngineersCommon.MOD_ID);

    public static final Identifier RESTORATION_WISDOM_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "restoration_wisdom");
    public static final Identifier DISTRIBUTION_WISDOM_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "distribution_wisdom");
    //public static final Identifier COMPRESSION_WISDOM_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "compression_wisdom");
    //public static final Identifier INDUCTION_WISDOM_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "induction_wisdom");

    public static final Identifier RESTORER_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "restorer");
    public static final Identifier ROUTER_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "router");
    //public static final Identifier COMPRESSOR_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "compressor");
    //public static final Identifier INDUCTION_FURNACE_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "induction_furnace");

    public static final FERegistration.FERegistryObject<Item> RESTORATION_WISDOM = ITEMS.register(RESTORATION_WISDOM_ID.getPath(), () -> new EngineeringWisdomItem(FETags.RESTORATION_WISDOM_DISCOVERS, new Item.Properties().rarity(Rarity.UNCOMMON).setId(ResourceKey.create(Registries.ITEM, RESTORATION_WISDOM_ID))));
    public static final FERegistration.FERegistryObject<Item> DISTRIBUTION_WISDOM = ITEMS.register(DISTRIBUTION_WISDOM_ID.getPath(), () -> new EngineeringWisdomItem(FETags.DISTRIBUTION_WISDOM_DISCOVERS, new Item.Properties().rarity(Rarity.UNCOMMON).setId(ResourceKey.create(Registries.ITEM, DISTRIBUTION_WISDOM_ID))));
    //public static final FERegistration.FERegistryObject<Item> COMPRESSION_WISDOM = ITEMS.register(COMPRESSION_WISDOM_ID.getPath(), () -> new EngineeringWisdomItem(new Item.Properties().rarity(Rarity.UNCOMMON).setId(ResourceKey.create(Registries.ITEM, COMPRESSION_WISDOM_ID))));
    //public static final FERegistration.FERegistryObject<Item> INDUCTION_WISDOM = ITEMS.register(INDUCTION_WISDOM_ID.getPath(), () -> new EngineeringWisdomItem(new Item.Properties().rarity(Rarity.UNCOMMON).setId(ResourceKey.create(Registries.ITEM, INDUCTION_WISDOM_ID))));

    public static final FERegistration.FERegistryObject<Item> RESTORER = ITEMS.register(RESTORER_ID.getPath(), () -> new RestorerItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, RESTORER_ID))));
    public static final FERegistration.FERegistryObject<Item> ROUTER = ITEMS.register(ROUTER_ID.getPath(), () -> new RouterItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, ROUTER_ID))));
    //public static final FERegistration.FERegistryObject<Item> COMPRESSOR = ITEMS.register(COMPRESSOR_ID.getPath(), () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, COMPRESSOR_ID))));
    //public static final FERegistration.FERegistryObject<Item> INDUCTION_FURNACE = ITEMS.register(INDUCTION_FURNACE_ID.getPath(), () -> new Item(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, INDUCTION_FURNACE_ID))));

    public static void init() {}

    public static void generateCreativeTabListing(Consumer<CreativeTabListing> loot) {
        loot.accept(CreativeTabListing.RESTORATION_WISDOM);
        loot.accept(CreativeTabListing.DISTRIBUTION_WISDOM);
        loot.accept(CreativeTabListing.RESTORER);
        loot.accept(CreativeTabListing.ROUTER);
    }

    public record CreativeTabListing(ResourceKey<CreativeModeTab> tab, Item beforeItem, Item item) {

        public static final CreativeTabListing RESTORATION_WISDOM = new CreativeTabListing(CreativeModeTabs.INGREDIENTS, Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE, FEItems.RESTORATION_WISDOM);
        public static final CreativeTabListing DISTRIBUTION_WISDOM = new CreativeTabListing(CreativeModeTabs.INGREDIENTS, FEItems.RESTORATION_WISDOM, FEItems.DISTRIBUTION_WISDOM);
        public static final CreativeTabListing RESTORER = new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, Items.DYED_BUNDLE.black(), FEItems.RESTORER);
        public static final CreativeTabListing ROUTER = new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, FEItems.RESTORER, FEItems.ROUTER);
    }
}
