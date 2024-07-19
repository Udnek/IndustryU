package me.udnek.industryu.item.techincal;

import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

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
    public boolean getHideTooltip() {return true;}
}
