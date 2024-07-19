package me.udnek.industryu.item;

import me.udnek.industryu.block.Blocks;
import me.udnek.itemscoreu.customblock.CustomBlock;
import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import me.udnek.itemscoreu.customitem.CustomBlockItem;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class BoilerItem extends ConstructableCustomItem implements MachineItem {
    @Override
    public CustomBlock getBlock() {return Blocks.BOILER;}
    @Override
    public @NotNull String getRawId() {return "boiler";}
    @Override
    public Material getMaterial() {return Material.SPAWNER;}
    @Override
    public String getRawItemName() {return "Boiler";}
    @Override
    public Integer getCustomModelData() {return 1000;}
}
