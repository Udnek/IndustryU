package me.udnek.industryu.gui;

import me.udnek.industryu.gui.abstraction.MachineGUI;
import me.udnek.industryu.machine.Furnace;

public class FurnaceGUI extends MachineGUI {
    protected Furnace machine;

    public FurnaceGUI(Furnace furnace){
        machine = furnace;
    }
    @Override
    public void synchronizeWithMachine() {
        inventory.setItem(1, machine.getSteamContainer().getVisualRepresentation());
    }

    @Override
    protected int[] getInputSlots() {
        return new int[0];
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[8];
    }

    @Override
    public int getInventorySize() {
        return 9;
    }
}
