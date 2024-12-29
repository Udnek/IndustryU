package me.udnek.industryu.machine.abstraction;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

public interface Machine {
    void initialize(@NotNull BlockState blockState);
    void tick();
    default boolean shouldSkipTick(int n){
        return Bukkit.getCurrentTick() % n != 0;
    }
    @NotNull Location getLocation();
}
