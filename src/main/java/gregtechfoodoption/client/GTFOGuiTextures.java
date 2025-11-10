package gregtechfoodoption.client;

import com.cleanroommc.modularui.drawable.UITexture;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.mui.GTGuiTextures;

public class GTFOGuiTextures {
    public static final UITexture PROGRESS_BAR_SLICER = progressBar("textures/gui/progress_bar/progress_bar_slicer.png");
    public static final UITexture SLICER_INPUT_OVERLAY = fullImage("textures/gui/overlay/chopping_block_overlay.png");
    public static final UITexture SLICER_CUTTER_OVERLAY = fullImage("textures/gui/overlay/slicer_container_overlay.png");
    public static final UITexture SLICER_OUTPUT_OVERLAY = fullImage("textures/gui/overlay/sliced_matter_overlay.png");

    public static final TextureArea PRIMITIVE_FOOD_OVERLAY = TextureArea.fullImage("textures/gui/overlay/food_overlay.png");
    public static final TextureArea CROP_OVERLAY = TextureArea.fullImage("textures/gui/overlay/crop_overlay.png");
    public static final TextureArea SEED_OVERLAY = TextureArea.fullImage("textures/gui/overlay/seed_overlay.png");
    public static final TextureArea BUTTON_MOB_SORTER_MODE = TextureArea.fullImage("textures/gui/widget/button_mob_sorter_mode.png");
    public static final TextureArea BUTTON_ADAPTABILITY = TextureArea.fullImage("textures/gui/widget/button_adaptability.png");
    public static final TextureArea BUTTON_LEFT = TextureArea.fullImage("textures/gui/widget/button_left.png");
    public static final TextureArea BUTTON_RIGHT = TextureArea.fullImage("textures/gui/widget/button_right.png");

    public static final TextureArea PROGRESS_BAR_SINGLE_HEAT = TextureArea.fullImage("textures/gui/progress_bar/progress_bar_single_heat.png");


    public static final TextureArea GTFO_LOGO = TextureArea.fullImage("textures/gui/icon/gtfo_logo.png");
    public static final TextureArea GTFO_LOGO_XMAS = TextureArea.fullImage("textures/gui/icon/gtfo_logo_xmas.png");
    public static final TextureArea GTFO_LOGO_DARK = TextureArea.fullImage("textures/gui/icon/gtfo_logo_dark.png");
    public static final TextureArea GTFO_LOGO_ERROR = TextureArea.fullImage("textures/gui/icon/gtfo_logo_error.png");
    public static final TextureArea GTFO_LOGO_WARNING = TextureArea.fullImage("textures/gui/icon/gtfo_logo_warning.png");
    public static final TextureArea GTFO_LOGO_WORKING = TextureArea.fullImage("textures/gui/icon/gtfo_logo_working.png");
    private static UITexture fullImage(String path) {
        return fullImage(path, false);
    }

    private static UITexture fullImage(String path, boolean canApplyTheme) {
        return UITexture.fullImage("gregtech", path, canApplyTheme);
    }
    private static UITexture progressBar(String path) {
        return progressBar(path, 20, 40, false);
    }

    private static UITexture progressBar(String path, boolean canApplyTheme) {
        return progressBar(path, 20, 40, canApplyTheme);
    }

    private static UITexture progressBar(String path, int width, int height) {
        return progressBar(path, width, height, false);
    }

    private static UITexture progressBar(String path, int width, int height, boolean canApplyTheme) {
        UITexture.Builder builder = (new UITexture.Builder()).location("gregtech", path).imageSize(width, height);
        if (canApplyTheme) {
            builder.canApplyTheme();
        }

        return builder.build();
    }
}
