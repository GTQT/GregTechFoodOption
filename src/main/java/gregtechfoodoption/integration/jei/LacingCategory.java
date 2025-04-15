package gregtechfoodoption.integration.jei;

import gregtech.api.gui.GuiTextures;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.Tags;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.Nullable;

public class LacingCategory implements IRecipeCategory<LacingInfo> {
    protected final IDrawable slot;
    private final IDrawable icon;
    private final IGuiHelper guiHelper;
    private String potionName;

    public LacingCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;

        this.icon = guiHelper.createDrawableIngredient(MetaItems.BOTTLE_PURPLE_DRINK.getStackForm());
        this.slot = guiHelper.drawableBuilder(GuiTextures.SLOT.imageLocation, 0, 0, 18, 18).setTextureSize(18, 18).build();
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, LacingInfo recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

        itemStackGroup.init(0, true, 79, 5);
        itemStackGroup.set(0, recipeWrapper.getLacingItem());

        potionName = recipeWrapper.getPotionName();
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        slot.draw(minecraft, 79, 5);
    }

    @Override
    public String getUid() {
        return GTFOValues.MODID + ":lacing_info";
    }

    @Override
    public String getTitle() {
        return I18n.format("lacing.item_list.name");
    }

    @Override
    public String getModName() {
        return Tags.MODNAME;
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createBlankDrawable(176, 50);
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

}
