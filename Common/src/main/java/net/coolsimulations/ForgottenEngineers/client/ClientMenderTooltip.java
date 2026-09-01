package net.coolsimulations.ForgottenEngineers.client;

import com.mojang.serialization.DataResult;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.BundleContents;
import org.apache.commons.lang3.math.Fraction;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public class ClientMenderTooltip extends ClientRestorerTooltip {

    private static final Component BUNDLE_EMPTY_DESCRIPTION = Component.translatable("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description");
    private final BundleContents contents;

    public ClientMenderTooltip(final BundleContents contents) {
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

    public static void extractEmptyBundleDescriptionText(final int x, final int y, final Font font, final GuiGraphicsExtractor graphics) {
        graphics.textWithWordWrap(font, BUNDLE_EMPTY_DESCRIPTION, x, y, 96, -5592406);
    }

    public static int getEmptyBundleDescriptionTextHeight(final Font font) {
        int var10000 = font.split(BUNDLE_EMPTY_DESCRIPTION, 96).size();
        Objects.requireNonNull(font);
        return var10000 * 9;
    }
}
