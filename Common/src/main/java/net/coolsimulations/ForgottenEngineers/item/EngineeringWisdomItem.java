package net.coolsimulations.ForgottenEngineers.item;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class EngineeringWisdomItem extends Item {

    protected static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    protected static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    protected static final Component ENGINEERING_WISDOM_SUFFIX = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "engineering_wisdom"))).withStyle(TITLE_FORMAT);
    protected static final Component ENGINEERING_WISDOM_DISCOVERS = Component.translatable(Util.makeDescriptionId("item", Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "engineering_wisdom.discovers"))).withStyle(TITLE_FORMAT);
    protected final TagKey<Item> WISDOM;

    public EngineeringWisdomItem(TagKey<Item> wisdom, Properties properties) {
        super(properties);
        this.WISDOM = wisdom;
    }

    @Override
    public void appendHoverText(final @NonNull ItemStack itemStack, final Item.TooltipContext context, final @NonNull TooltipDisplay display, final Consumer<Component> builder, final @NonNull TooltipFlag tooltipFlag) {
        builder.accept(ENGINEERING_WISDOM_SUFFIX);
        HolderLookup.Provider registries = context.registries();
        if (registries != null) {
            registries.lookup(Registries.ITEM).flatMap(item -> item.get(WISDOM)).ifPresent(tag -> {
                builder.accept(CommonComponents.EMPTY);
                builder.accept(ENGINEERING_WISDOM_DISCOVERS);
                for (Holder<Item> item : tag) {
                    builder.accept(CommonComponents.space().append(Component.translatable(item.value().getDescriptionId()).withStyle(DESCRIPTION_FORMAT)));
                }
            });
        }
    }
}
