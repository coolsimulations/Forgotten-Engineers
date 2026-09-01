package net.coolsimulations.ForgottenEngineers.client;

import com.mojang.serialization.DataResult;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientFilterDeviceTooltip extends ClientRestorerTooltip {

    private static final Component BUNDLE_EMPTY_DESCRIPTION = Component.translatable("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description");
    private final BundleContents contents;

    public ClientFilterDeviceTooltip(final BundleContents contents) {
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

    @Override
    protected void extractBundleWithItemsTooltip(final Font font, final int x, final int y, final int w, final int h, final GuiGraphicsExtractor graphics, final Fraction weight) {
        List<ItemStackTemplate> items = this.contents.items();
        int hiddenItems = Math.max(0, items.size() - 12);

        List<ItemStackTemplate> shownItems = new ArrayList<>(items.subList(hiddenItems, items.size()));

        int xStartPos = x + getContentXOffset(w);
        int yStartPos = y;

        int visibleItemCount = shownItems.size();

        if (hiddenItems > 0)
            visibleItemCount--;

        for (int visualIndex = 0; visualIndex < 12; visualIndex++) {
            int column = visualIndex % 4;
            int row = visualIndex / 4;
            int drawX = xStartPos + column * 24;
            int drawY = yStartPos + row * 24;

            if (hiddenItems > 0 && visualIndex == 11) {
                extractCount(drawX, drawY, hiddenItems + 1, font, graphics);
                continue;
            }

            if (visualIndex >= visibleItemCount) continue;
            int originalIndex = items.size() - 1 - visualIndex;
            this.extractSlot(drawX, drawY, shownItems.get(visualIndex), originalIndex, visualIndex, font, graphics);
        }

        this.extractSelectedItemTooltip(font, graphics, x, y, w);
        extractProgressbar(x + getContentXOffset(w), y + this.itemGridHeight() + 4, font, graphics, weight);
    }

    private void extractSlot(final int drawX, final int drawY, final ItemStackTemplate itemTemplate, final int originalIndex, final int visualIndex, final Font font, final GuiGraphicsExtractor graphics) {
        boolean hasHighlight = originalIndex == this.contents.getSelectedItemIndex();
        ItemStack item = itemTemplate.create();

        if (hasHighlight)
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_HIGHLIGHT_BACK_SPRITE, drawX, drawY, 24, 24);
        else
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_BACKGROUND_SPRITE, drawX, drawY, 24, 24);

        graphics.item(item, drawX + 4, drawY + 4, visualIndex);
        graphics.itemDecorations(font, item, drawX + 4, drawY + 4);

        if (hasHighlight)
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_HIGHLIGHT_FRONT_SPRITE, drawX, drawY, 24, 24);
    }

    public static void extractEmptyBundleDescriptionText(final int x, final int y, final Font font, final GuiGraphicsExtractor graphics) {
        graphics.textWithWordWrap(font, BUNDLE_EMPTY_DESCRIPTION, x, y, 96, -5592406);
    }

    public static int getEmptyBundleDescriptionTextHeight(final Font font) {
        int var10000 = font.split(BUNDLE_EMPTY_DESCRIPTION, 96).size();
        Objects.requireNonNull(font);
        return var10000 * 9;
    }
}
