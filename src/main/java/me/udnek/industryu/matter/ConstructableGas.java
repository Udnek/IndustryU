package me.udnek.industryu.matter;

import me.udnek.itemscoreu.utils.ItemUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;

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
    public String getName() {
        return name;
    }

    @Override
    public ItemStack getVisualRepresentation() {
        ItemStack itemStack = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        ItemUtils.setFireworkColor((FireworkEffectMeta) itemMeta, getColor());
        itemMeta.displayName(Component.text(name));
        itemMeta.setCustomModelData(1001);
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
