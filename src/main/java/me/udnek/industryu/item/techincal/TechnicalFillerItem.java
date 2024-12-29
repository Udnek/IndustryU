package me.udnek.industryu.item.techincal;

import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TechnicalFillerItem extends ConstructableCustomItem {
    @Override
    public @NotNull String getRawId() {
        return "technical_filler";
    }

    @Override
    public Material getMaterial() {
        return Material.WHITE_STAINED_GLASS_PANE;
    }

    @Override
    public @Nullable Boolean getHideTooltip() {return true;}
}
