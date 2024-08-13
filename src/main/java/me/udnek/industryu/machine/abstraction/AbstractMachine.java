package me.udnek.industryu.machine.abstraction;

import org.bukkit.Location;
import org.bukkit.block.BlockState;

public abstract class AbstractMachine implements Machine{

    protected Location location;
    @Override
    public void initialize(BlockState blockState) {
        location = blockState.getLocation().toCenterLocation();
    }
    @Override
    public Location getLocation() {return location.clone();}
}
