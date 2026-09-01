package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.FEServices;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ColorCollection;

public class FEItems {

    public static final Item RESTORATION_WISDOM = FEServices.REGISTRY.getItem(ForgottenEngineersItems.RESTORATION_WISDOM_ID);
    public static final Item DISTRIBUTION_WISDOM = FEServices.REGISTRY.getItem(ForgottenEngineersItems.DISTRIBUTION_WISDOM_ID);
    public static final Item COMPRESSION_WISDOM = FEServices.REGISTRY.getItem(ForgottenEngineersItems.COMPRESSION_WISDOM_ID);
    public static final Item INDUCTION_WISDOM = FEServices.REGISTRY.getItem(ForgottenEngineersItems.INDUCTION_WISDOM_ID);
    public static final Item ENGINEERS_SEAL = FEServices.REGISTRY.getItem(ForgottenEngineersItems.ENGINEERS_SEAL_ID);
    public static final Item UNIVERSAL_WISDOM = FEServices.REGISTRY.getItem(ForgottenEngineersItems.UNIVERSAL_WISDOM_ID);

    public static final Item RESTORER = FEServices.REGISTRY.getItem(ForgottenEngineersItems.RESTORER_ID);
    public static final Item ROUTER = FEServices.REGISTRY.getItem(ForgottenEngineersItems.ROUTER_ID);
    public static final ColorCollection<Item> DYED_ROUTER = ForgottenEngineersItems.DYED_ROUTER_ID.map((name) -> FEServices.REGISTRY.getItem(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, name)));
    public static final Item COMPRESSOR = FEServices.REGISTRY.getItem(ForgottenEngineersItems.COMPRESSOR_ID);
    public static final Item FUEL_CARRIER = FEServices.REGISTRY.getItem(ForgottenEngineersItems.FUEL_CARRIER_ID);
    public static final Item INDUCTION_FURNACE = FEServices.REGISTRY.getItem(ForgottenEngineersItems.INDUCTION_FURNACE_ID);

    public static final Item MENDER = FEServices.REGISTRY.getItem(ForgottenEngineersItems.MENDER_ID);
    public static final Item ENDER_ROUTER = FEServices.REGISTRY.getItem(ForgottenEngineersItems.ENDER_ROUTER_ID);
    public static final Item STRIPPER = FEServices.REGISTRY.getItem(ForgottenEngineersItems.STRIPPER_ID);
    public static final Item COMBUSTOR = FEServices.REGISTRY.getItem(ForgottenEngineersItems.COMBUSTOR_ID);
}
