package me.udnek.industryu.block;

import me.udnek.industryu.item.Items;
import me.udnek.industryu.machine.Furnace;
import me.udnek.industryu.machine.abstraction.Machine;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class FurnaceBlock extends AbstractMachineBlock {
    @Override
    public ItemStack getVisualItem() {
        return Items.FURNACE.getItem();
    }

    @Override
    public Machine getNewMachine() {
        return new Furnace();
    }

    @Override
    public @NotNull String getRawId() {
        return "furnace";
    }
}
