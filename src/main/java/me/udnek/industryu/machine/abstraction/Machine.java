package me.udnek.industryu.machine.abstraction;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockState;

public interface Machine {
    void initialize(BlockState blockState);
    void tick();
    default boolean shouldSkipTick(int n){
        return Bukkit.getCurrentTick() % n != 0;
    }
    Location getLocation();
}
