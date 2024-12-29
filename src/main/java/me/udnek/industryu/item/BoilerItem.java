package me.udnek.industryu.item;

import io.papermc.paper.datacomponent.item.CustomModelData;
import me.udnek.industryu.block.Blocks;
import me.udnek.itemscoreu.customblock.CustomBlock;
import me.udnek.itemscoreu.customitem.ConstructableCustomItem;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BoilerItem extends ConstructableCustomItem implements MachineItem {
    @Override
    public @NotNull CustomBlock getBlock() {return Blocks.BOILER;}
    @Override
    public @NotNull String getRawId() {return "boiler";}
    @Override
    public @NotNull Material getMaterial() {return Material.SPAWNER;}

    @Override
    public @NotNull String getModelPath() {
        return "machine/copper_boiler";
    }

    @Override
    public @Nullable DataSupplier<Key> getItemModel() {
        return MachineItem.super.getItemModel();
    }
}
