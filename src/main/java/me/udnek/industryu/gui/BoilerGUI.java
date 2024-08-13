package me.udnek.industryu.gui;

import me.udnek.industryu.gui.abstraction.MachineGUI;
import me.udnek.industryu.machine.Boiler;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BoilerGUI extends MachineGUI {

    public static final int FUEL_SLOT = 0;
    protected Boiler machine;
    public BoilerGUI(Boiler boiler){
        this.machine = boiler;
    }

    public ItemStack takeFuel(){
        ItemStack item = inventory.getItem(FUEL_SLOT);
        if (item == null) return null;
        if (item.getType() != Material.COAL) return null;
        return takeItem(FUEL_SLOT, 1);
    }

    @Override
    public void synchronizeWithMachine() {
        setAmount(1, machine.getProgress()+1);
        inventory.setItem(1, machine.getSteamContainer().getVisualRepresentation());
    }

    @Override
    public int getInventorySize() {return 9;}
    @Override
    protected int[] getInputSlots() {
        return new int[]{FUEL_SLOT};
    }
    @Override
    protected int[] getOutputSlots() {
        return new int[]{};
    }
}