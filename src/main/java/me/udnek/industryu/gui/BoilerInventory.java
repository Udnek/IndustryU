package me.udnek.industryu.gui;

import me.udnek.itemscoreu.custominventory.CustomInventory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.Random;

public class BoilerInventory extends MachineInventory {
    @Override
    public int getInventorySize() {return 9;}

    @Override
    protected int[] getInputSlots() {
        return new int[]{0};
    }

    @Override
    protected int[] getOutputSlots() {
        return new int[]{getInventorySize()-1};
    }
}