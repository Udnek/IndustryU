package me.udnek.industryu.matter;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface Matter{
    int getColor();
    @NotNull String getName();
    @NotNull ItemStack getVisualRepresentation();
}
