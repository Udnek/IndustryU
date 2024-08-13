package me.udnek.industryu.item;

import me.udnek.industryu.block.Blocks;
import me.udnek.itemscoreu.customblock.CustomBlock;
import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class FurnaceItem extends ConstructableCustomItem implements MachineItem {
    @Override
    public CustomBlock getBlock() {return Blocks.FURNACE;}
    @Override
    public @NotNull String getRawId() {return "furnace";}
    @Override
    public Material getMaterial() {return Material.SPAWNER;}
    @Override
    public String getRawItemName() {return "Furnace";}
    @Override
    public Integer getCustomModelData() {return 1001;}
}
