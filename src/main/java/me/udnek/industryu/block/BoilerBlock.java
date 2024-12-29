package me.udnek.industryu.block;

import me.udnek.industryu.item.Items;
import me.udnek.industryu.machine.Boiler;
import me.udnek.industryu.machine.abstraction.Machine;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BoilerBlock extends AbstractMachineBlock{
    @Override
    public @NotNull String getRawId() {
        return "boiler";
    }

    @Override
    public @NotNull Machine getNewMachine() {
        return new Boiler();
    }

    @Override
    public ItemStack getVisualItem() {
        return Items.BOILER.getItem();
    }

}
