package net.coolsimulations.ForgottenEngineers.client;

import com.mojang.serialization.DataResult;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientBundleTooltip;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Objects;

public class ClientRestorerTooltip extends ClientBundleTooltip {

    protected static final Identifier PROGRESSBAR_BORDER_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/device_progressbar_border");
    protected static final Identifier PROGRESSBAR_BORDER_FULL_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/device_progressbar_border_full");
    protected static final Identifier PROGRESSBAR_FILL_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/device_progressbar_fill");
    protected static final Identifier PROGRESSBAR_FULL_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/device_progressbar_full");
    protected static final Identifier SLOT_HIGHLIGHT_BACK_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/slot_highlight_back");
    protected static final Identifier SLOT_HIGHLIGHT_FRONT_SPRITE = Identifier.withDefaultNamespace("container/bundle/slot_highlight_front");
    protected static final Identifier SLOT_BACKGROUND_SPRITE = Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "container/device/slot_background");

    protected static final Component BUNDLE_EMPTY_DESCRIPTION = Component.translatable("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description");
    protected final BundleContents contents;

    public ClientRestorerTooltip(final BundleContents contents) {
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
        if (!weight.isError())
            if (this.contents.isEmpty())
                extractEmptyBundleTooltip(font, x, y, w, h, graphics);
            else
                this.extractBundleWithItemsTooltip(font, x, y, w, h, graphics, weight.getOrThrow());
    }

    private static void extractEmptyBundleTooltip(final Font font, final int x, final int y, final int w, final int h, final GuiGraphicsExtractor graphics) {
        int left = x + getContentXOffset(w);
        extractEmptyBundleDescriptionText(left, y, font, graphics);
        extractProgressbar(left, y + getEmptyBundleDescriptionTextHeight(font) + 4, font, graphics, Fraction.ZERO);
    }

    protected void extractBundleWithItemsTooltip(final Font font, final int x, final int y, final int w, final int h, final GuiGraphicsExtractor graphics, final Fraction weight) {
        boolean isOverflowing = this.contents.size() > 12;
        List<ItemStackTemplate> shownItems = this.getShownItems(this.contents.getNumberOfItemsToShow());
        int xStartPos = x + getContentXOffset(w) + 96;
        int yStartPos = y + this.gridSizeY() * 24;
        int slotNumber = 1;

        for(int rowNumber = 1; rowNumber <= this.gridSizeY(); ++rowNumber) {
            for(int columnNumber = 1; columnNumber <= 4; ++columnNumber) {
                int drawX = xStartPos - columnNumber * 24;
                int drawY = yStartPos - rowNumber * 24;
                if (shouldRenderSurplusText(isOverflowing, columnNumber, rowNumber)) {
                    extractCount(drawX, drawY, this.getAmountOfHiddenItems(shownItems), font, graphics);
                } else if (shouldRenderItemSlot(shownItems, slotNumber)) {
                    this.extractSlot(slotNumber, drawX, drawY, shownItems, slotNumber, font, graphics);
                    ++slotNumber;
                }
            }
        }

        this.extractSelectedItemTooltip(font, graphics, x, y, w);
        extractProgressbar(x + getContentXOffset(w), y + this.itemGridHeight() + 4, font, graphics, weight);
    }

    private void extractSlot(final int slotNumber, final int drawX, final int drawY, final List<ItemStackTemplate> shownItems, final int slotIndex, final Font font, final GuiGraphicsExtractor graphics) {
        int itemVisualOrderIndex = shownItems.size() - slotNumber;
        boolean hasHighlight = itemVisualOrderIndex == this.contents.getSelectedItemIndex();
        ItemStack item = (shownItems.get(itemVisualOrderIndex)).create();
        if (hasHighlight)
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_HIGHLIGHT_BACK_SPRITE, drawX, drawY, 24, 24);
        else
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_BACKGROUND_SPRITE, drawX, drawY, 24, 24);

        graphics.item(item, drawX + 4, drawY + 4, slotIndex);
        graphics.itemDecorations(font, item, drawX + 4, drawY + 4);
        if (hasHighlight)
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_HIGHLIGHT_FRONT_SPRITE, drawX, drawY, 24, 24);
    }

    protected static void extractProgressbar(final int x, final int y, final Font font, final GuiGraphicsExtractor graphics, final Fraction weight) {
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, getProgressBarTexture(weight), x + 1, y, getProgressBarFill(weight), 13);
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

    private static Identifier getProgressBarTexture(final Fraction weight) {
        return weight.compareTo(Fraction.ONE) >= 0 ? PROGRESSBAR_FULL_SPRITE : PROGRESSBAR_FILL_SPRITE;
    }

    private static Identifier getProgressBarBorderTexture(final Fraction weight) {
        return weight.compareTo(Fraction.ONE) >= 0 ? PROGRESSBAR_BORDER_FULL_SPRITE : PROGRESSBAR_BORDER_SPRITE;
    }
}
