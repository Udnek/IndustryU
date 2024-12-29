package me.udnek.industryu.item;

import io.papermc.paper.datacomponent.item.CustomModelData;
import me.udnek.industryu.block.Blocks;
import me.udnek.itemscoreu.customblock.CustomBlock;
import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FurnaceItem extends ConstructableCustomItem implements MachineItem {
    @Override
    public @NotNull CustomBlock getBlock() {return Blocks.FURNACE;}
    @Override
    public @NotNull String getRawId() {return "furnace";}
    @Override
    public @NotNull Material getMaterial() {return Material.SPAWNER;}

    @Override
    public @NotNull String getModelPath() {
        return "machine/copper_furnace";
    }

    @Override
    public @Nullable DataSupplier<Key> getItemModel() {
        return MachineItem.super.getItemModel();
    }
}
