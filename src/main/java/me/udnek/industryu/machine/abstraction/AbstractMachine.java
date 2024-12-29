package me.udnek.industryu.machine.abstraction;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractMachine implements Machine{

    protected Location location;
    @Override
    public void initialize(@NotNull BlockState blockState) {
        location = blockState.getLocation().toCenterLocation();
    }
    @Override
    public @NotNull Location getLocation() {return location.clone();}
}
