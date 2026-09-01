package net.coolsimulations.ForgottenEngineers.client;

import com.mojang.serialization.DataResult;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.coolsimulations.ForgottenEngineers.item.RouterItem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientBundleTooltip;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientRouterTooltip extends ClientBundleTooltip {

    private static final Identifier PROGRESSBAR_BORDER_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/device_progressbar_border");
    private static final Identifier PROGRESSBAR_BORDER_FULL_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/device_progressbar_border_full");
    private static final Identifier PROGRESSBAR_FILL_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/device_progressbar_fill");
    private static final Identifier PROGRESSBAR_SHULKER_FILL_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/router/router_progressbar_fill");
    private static final Identifier PROGRESSBAR_FULL_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/device_progressbar_full");
    private static final Identifier SLOT_HIGHLIGHT_BACK_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/slot_highlight_back");
    private static final Identifier SLOT_HIGHLIGHT_FRONT_SPRITE = Identifier.withDefaultNamespace("container/bundle/slot_highlight_front");
    private static final Identifier SLOT_BACKGROUND_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/slot_background");

    private static final Identifier SHULKER_SLOT_BACKGROUND_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/router/shulker_slot_background");
    private static final Identifier SHULKER_SLOT_HIGHLIGHT_BACKGROUND_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/router/shulker_slot_highlight_background");

    private static final Component BUNDLE_EMPTY_DESCRIPTION = Component.translatable("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description");
    private final BundleContents contents;

    public ClientRouterTooltip(final BundleContents contents) {
        super(contents);
        this.contents = contents;
    }

    @Override
    public int getHeight(final @NonNull Font font) {
        return this.contents.isEmpty() ? getEmptyBundleBackgroundHeight(font) : this.backgroundHeight();
    }

    private static int getEmptyBundleBackgroundHeight(final Font font) {
        return getEmptyBundleDescriptionTextHeight(font) + 13 + 8;
    }

    public void extractImage(final @NonNull Font font, final int x, final int y, final int w, final int h, final @NonNull GuiGraphicsExtractor graphics) {
        DataResult<Fraction> weight = this.contents.weight();
        if (RouterItem.hasShulkerBox(this.contents.items()))
            weight = DataResult.success(RouterItem.getShulkerContentWeight(RouterItem.getShulker(this.contents.items())));
        if (!weight.isError())
            if (this.contents.isEmpty())
                extractEmptyBundleTooltip(font, x, y, w, h, graphics);
            else
                this.extractBundleWithItemsTooltip(font, x, y, w, h, graphics, weight.getOrThrow());
    }

    private static void extractEmptyBundleTooltip(final Font font, final int x, final int y, final int w, final int h, final GuiGraphicsExtractor graphics) {
        int left = x + getContentXOffset(w);
        extractEmptyBundleDescriptionText(left, y, font, graphics);
        extractProgressbar(left, y + getEmptyBundleDescriptionTextHeight(font) + 4, font, graphics, Fraction.ZERO, false);
    }

    private void extractBundleWithItemsTooltip(final Font font, final int x, final int y, final int w, final int h, final GuiGraphicsExtractor graphics, final Fraction weight) {
        boolean isOverflowing = this.contents.size() + (!RouterItem.hasShulkerBox(this.contents.items()) ? 1 : 0) > 12;
        List<DisplayItem> shownItems = this.getShownItems();
        int xStartPos = x + getContentXOffset(w);
        int slotNumber = 0;

        for (int rowNumber = 1; rowNumber <= this.gridSizeY(); ++rowNumber) {
            for (int columnNumber = 1; columnNumber <= 4; ++columnNumber) {
                int drawX = xStartPos + (columnNumber - 1) * 24;
                int drawY = y + (rowNumber - 1) * 24;
                if (shouldRenderSurplusText(isOverflowing, columnNumber, rowNumber)) {
                    extractCount(drawX, drawY, getAmountOfHiddenDisplayItems(shownItems), font, graphics);
                    continue;
                }
                if (shouldRenderDisplayItemSlot(shownItems, slotNumber)) {
                    this.extractSlot(slotNumber, drawX, drawY, shownItems, font, graphics);
                    ++slotNumber;
                }
            }
        }

        this.extractSelectedItemTooltip(font, graphics, x, y, w);
        extractProgressbar(x + getContentXOffset(w), y + this.itemGridHeight() + 4, font, graphics, weight, RouterItem.hasShulkerBox(this.contents.items()));
    }

    private List<DisplayItem> getShownItems() {
        List<DisplayItem> result = new ArrayList<>();
        List<? extends ItemInstance> items = this.contents.items();
        int shulkerIndex = RouterItem.getShulkerBoxIndex(items);
        boolean isOverflowing = items.size() + (!RouterItem.hasShulkerBox(items) ? 1 : 0) > 12;

        if (!RouterItem.hasShulkerBox(this.contents.items()))
            result.add(new DisplayItem(ItemStack.EMPTY, -2, true));
        else if (shulkerIndex >= 0)
            result.add(new DisplayItem(((ItemStackTemplate) items.get(shulkerIndex)).create(), shulkerIndex, true));

        for (int index = 0; index < items.size(); ++index) {
            if (RouterItem.hasShulkerBox(contents.items()) && index == shulkerIndex)
                continue;

            result.add(new DisplayItem(((ItemStackTemplate) items.get(index)).create(), index, false));

            if (result.size() == 11 && isOverflowing)
                break;
            if (result.size() == 12)
                break;
        }

        return result;
    }

    public static boolean shouldRenderSurplusText(final boolean isOverflowing, final int column, final int row) {
        return isOverflowing && column == 4 && row == 3;
    }

    private static boolean shouldRenderDisplayItemSlot(final List<DisplayItem> shownItems, final int displayIndex) {
        return displayIndex < shownItems.size();
    }

    private int getAmountOfHiddenDisplayItems(final List<DisplayItem> shownItems) {
        int visibleCount = shownItems.stream().mapToInt(displayItem -> displayItem.item().count()).sum();
        return this.contents.items().stream().mapToInt(ItemInstance::count).sum() - visibleCount;
    }

    private void extractSlot(final int displayIndex, final int drawX, final int drawY, final List<DisplayItem> shownItems, final Font font, final GuiGraphicsExtractor graphics) {
        DisplayItem displayItem = shownItems.get(displayIndex);
        ItemStack item = displayItem.item();
        int contentsIndex = displayItem.contentsIndex();
        boolean hasHighlight = contentsIndex == this.contents.getSelectedItemIndex();

        if (hasHighlight)
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, displayItem.isShulker() ? SHULKER_SLOT_HIGHLIGHT_BACKGROUND_SPRITE : SLOT_HIGHLIGHT_BACK_SPRITE, drawX, drawY, 24, 24);
        else
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, displayItem.isShulker() ? SHULKER_SLOT_BACKGROUND_SPRITE : SLOT_BACKGROUND_SPRITE, drawX, drawY, 24, 24);

        graphics.item(item, drawX + 4, drawY + 4, displayIndex);
        graphics.itemDecorations(font, item, drawX + 4, drawY + 4);

        if (hasHighlight)
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_HIGHLIGHT_FRONT_SPRITE, drawX, drawY, 24, 24);
    }

    private static void extractProgressbar(final int x, final int y, final Font font, final GuiGraphicsExtractor graphics, final Fraction weight, final boolean hasShulker) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, getProgressBarTexture(weight, hasShulker), x + 1, y, getProgressBarFill(weight), 13);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, getProgressBarBorderTexture(weight), x, y, 96, 13);
        Component progressBarFillText = getProgressBarFillText(weight);
        if (progressBarFillText != null)
            graphics.centeredText(font, progressBarFillText, x + 48, y + 3, -1);
    }

    public static void extractEmptyBundleDescriptionText(final int x, final int y, final Font font, final GuiGraphicsExtractor graphics) {
        graphics.textWithWordWrap(font, BUNDLE_EMPTY_DESCRIPTION, x, y, 96, -5592406);
    }

    public static int getEmptyBundleDescriptionTextHeight(final Font font) {
        int var10000 = font.split(BUNDLE_EMPTY_DESCRIPTION, 96).size();
        Objects.requireNonNull(font);
        return var10000 * 9;
    }

    private static Identifier getProgressBarTexture(final Fraction weight, final boolean hasShulker) {
        return weight.compareTo(Fraction.ONE) >= 0 ? PROGRESSBAR_FULL_SPRITE : hasShulker ? PROGRESSBAR_SHULKER_FILL_SPRITE : PROGRESSBAR_FILL_SPRITE;
    }

    private static Identifier getProgressBarBorderTexture(final Fraction weight) {
        return weight.compareTo(Fraction.ONE) >= 0 ? PROGRESSBAR_BORDER_FULL_SPRITE : PROGRESSBAR_BORDER_SPRITE;
    }

    private record DisplayItem(ItemStack item, int contentsIndex, boolean isShulker) {}
}
