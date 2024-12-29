package me.udnek.industryu.matter;

import me.udnek.industryu.IndustryU;
import me.udnek.itemscoreu.util.ItemUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class ConstructableGas implements Gas{

    private final String name;
    private final int color;

    protected ConstructableGas(String name, int color){
        this.name = name;
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull ItemStack getVisualRepresentation() {
        ItemStack itemStack = new ItemStack(Material.FIREWORK_STAR);
        ItemUtils.setFireworkColor(itemStack, getColor());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.text(name));
        itemMeta.setItemModel(new NamespacedKey(IndustryU.getInstance(), "gas"));
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
