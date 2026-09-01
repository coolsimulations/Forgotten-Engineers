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
import net.minecraft.world.level.block.ColorCollection;

import java.util.List;
import java.util.function.Consumer;

public class ForgottenEngineersItems {

    public static final FERegistration.FERegistrationProvider<Item> ITEMS = FERegistration.FERegistrationProvider.get(BuiltInRegistries.ITEM, ForgottenEngineersCommon.MOD_ID);

    public static final Identifier RESTORATION_WISDOM_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "restoration_wisdom");
    public static final Identifier DISTRIBUTION_WISDOM_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "distribution_wisdom");
    public static final Identifier COMPRESSION_WISDOM_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "compression_wisdom");
    public static final Identifier INDUCTION_WISDOM_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "induction_wisdom");
    public static final Identifier ENGINEERS_SEAL_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "engineers_seal");
    public static final Identifier UNIVERSAL_WISDOM_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "universal_wisdom");

    public static final Identifier RESTORER_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "restorer");
    public static final Identifier ROUTER_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "router");
    public static final ColorCollection<String> DYED_ROUTER_ID = ColorCollection.prefixWithColor(ColorCollection.create(ForgottenEngineersItems.ROUTER_ID.getPath()));
    public static final Identifier COMPRESSOR_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "compressor");
    public static final Identifier FUEL_CARRIER_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "fuel_carrier");
    public static final Identifier INDUCTION_FURNACE_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "induction_furnace");

    public static final Identifier MENDER_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "mender");
    public static final Identifier ENDER_ROUTER_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "ender_router");
    public static final Identifier STRIPPER_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "stripper");
    public static final Identifier COMBUSTOR_ID = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "combustor");

    public static final FERegistration.FERegistryObject<Item> RESTORATION_WISDOM = ITEMS.register(RESTORATION_WISDOM_ID.getPath(), () -> new EngineeringWisdomItem(FETags.RESTORATION_WISDOM_DISCOVERS, new Item.Properties().rarity(Rarity.UNCOMMON).setId(ResourceKey.create(Registries.ITEM, RESTORATION_WISDOM_ID))));
    public static final FERegistration.FERegistryObject<Item> DISTRIBUTION_WISDOM = ITEMS.register(DISTRIBUTION_WISDOM_ID.getPath(), () -> new EngineeringWisdomItem(FETags.DISTRIBUTION_WISDOM_DISCOVERS, new Item.Properties().rarity(Rarity.UNCOMMON).setId(ResourceKey.create(Registries.ITEM, DISTRIBUTION_WISDOM_ID))));
    public static final FERegistration.FERegistryObject<Item> COMPRESSION_WISDOM = ITEMS.register(COMPRESSION_WISDOM_ID.getPath(), () -> new EngineeringWisdomItem(FETags.COMPRESSION_WISDOM_DISCOVERS, new Item.Properties().rarity(Rarity.UNCOMMON).setId(ResourceKey.create(Registries.ITEM, COMPRESSION_WISDOM_ID))));
    public static final FERegistration.FERegistryObject<Item> INDUCTION_WISDOM = ITEMS.register(INDUCTION_WISDOM_ID.getPath(), () -> new EngineeringWisdomItem(FETags.INDUCTION_WISDOM_DISCOVERS, new Item.Properties().rarity(Rarity.UNCOMMON).setId(ResourceKey.create(Registries.ITEM, INDUCTION_WISDOM_ID))));
    public static final FERegistration.FERegistryObject<Item> ENGINEERS_SEAL = ITEMS.register(ENGINEERS_SEAL_ID.getPath(), () -> new EngineeringWisdomItem(FETags.ENGINEERS_SEAL_DISCOVERS, new Item.Properties().rarity(Rarity.RARE).setId(ResourceKey.create(Registries.ITEM, ENGINEERS_SEAL_ID))));
    public static final FERegistration.FERegistryObject<Item> UNIVERSAL_WISDOM = ITEMS.register(UNIVERSAL_WISDOM_ID.getPath(), () -> new EngineeringWisdomItem(FETags.UNIVERSAL_WISDOM_DISCOVERS, new Item.Properties().rarity(Rarity.EPIC).setId(ResourceKey.create(Registries.ITEM, UNIVERSAL_WISDOM_ID))));

    public static final FERegistration.FERegistryObject<Item> RESTORER = ITEMS.register(RESTORER_ID.getPath(), () -> new RestorerItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, RESTORER_ID))));
    public static final FERegistration.FERegistryObject<Item> ROUTER = ITEMS.register(ROUTER_ID.getPath(), () -> new RouterItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, ROUTER_ID))));
    public static final List<FERegistration.FERegistryObject<Item>> DYED_ROUTER = DYED_ROUTER_ID.map(name -> ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, name))).asList().stream().<FERegistration.FERegistryObject<Item>>map(item -> ITEMS.register(item.identifier().getPath(), () -> new RouterItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(item)))).toList();
    public static final FERegistration.FERegistryObject<Item> COMPRESSOR = ITEMS.register(COMPRESSOR_ID.getPath(), () -> new CompressorItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, COMPRESSOR_ID))));
    public static final FERegistration.FERegistryObject<Item> FUEL_CARRIER = ITEMS.register(FUEL_CARRIER_ID.getPath(), () -> new FuelCarrierItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, FUEL_CARRIER_ID))));
    public static final FERegistration.FERegistryObject<Item> INDUCTION_FURNACE = ITEMS.register(INDUCTION_FURNACE_ID.getPath(), () -> new InductionFurnaceItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, INDUCTION_FURNACE_ID))));

    public static final FERegistration.FERegistryObject<Item> MENDER = ITEMS.register(MENDER_ID.getPath(), () -> new MenderItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, MENDER_ID))));
    public static final FERegistration.FERegistryObject<Item> ENDER_ROUTER = ITEMS.register(ENDER_ROUTER_ID.getPath(), () -> new EnderRouterItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, ENDER_ROUTER_ID))));
    public static final FERegistration.FERegistryObject<Item> STRIPPER = ITEMS.register(STRIPPER_ID.getPath(), () -> new StripperItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, STRIPPER_ID))));
    public static final FERegistration.FERegistryObject<Item> COMBUSTOR = ITEMS.register(COMBUSTOR_ID.getPath(), () -> new CombustorItem(new Item.Properties().stacksTo(1).component(DataComponents.BUNDLE_CONTENTS, BundleContents.EMPTY).setId(ResourceKey.create(Registries.ITEM, COMBUSTOR_ID))));

    public static void init() {}

    public static void generateCreativeTabListing(Consumer<CreativeTabListing> loot) {
        loot.accept(new CreativeTabListing(CreativeModeTabs.INGREDIENTS, Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE, FEItems.RESTORATION_WISDOM));
        loot.accept(new CreativeTabListing(CreativeModeTabs.INGREDIENTS, FEItems.RESTORATION_WISDOM, FEItems.DISTRIBUTION_WISDOM));
        loot.accept(new CreativeTabListing(CreativeModeTabs.INGREDIENTS, FEItems.DISTRIBUTION_WISDOM, FEItems.COMPRESSION_WISDOM));
        loot.accept(new CreativeTabListing(CreativeModeTabs.INGREDIENTS, FEItems.COMPRESSION_WISDOM, FEItems.INDUCTION_WISDOM));
        loot.accept(new CreativeTabListing(CreativeModeTabs.INGREDIENTS, FEItems.INDUCTION_WISDOM, FEItems.UNIVERSAL_WISDOM));
        loot.accept(new CreativeTabListing(CreativeModeTabs.INGREDIENTS, FEItems.UNIVERSAL_WISDOM, FEItems.ENGINEERS_SEAL));
        loot.accept(new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, Items.DYED_BUNDLE.black(), FEItems.RESTORER));
        loot.accept(new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, FEItems.RESTORER, FEItems.MENDER));
        loot.accept(new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, FEItems.MENDER, FEItems.ROUTER));
        FEItems.DYED_ROUTER.asList().reversed().forEach((item) -> loot.accept(new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, FEItems.ROUTER, item)));
        loot.accept(new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, FEItems.DYED_ROUTER.black(), FEItems.ENDER_ROUTER));
        loot.accept(new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, FEItems.ENDER_ROUTER, FEItems.COMPRESSOR));
        loot.accept(new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, FEItems.COMPRESSOR, FEItems.STRIPPER));
        loot.accept(new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, FEItems.STRIPPER, FEItems.FUEL_CARRIER));
        loot.accept(new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, FEItems.FUEL_CARRIER, FEItems.INDUCTION_FURNACE));
        loot.accept(new CreativeTabListing(CreativeModeTabs.TOOLS_AND_UTILITIES, FEItems.INDUCTION_FURNACE, FEItems.COMBUSTOR));
    }

    public record CreativeTabListing(ResourceKey<CreativeModeTab> tab, Item beforeItem, Item item) {}
}
