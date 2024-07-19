package me.udnek.industryu.machine;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.BlockState;

public abstract class Machine {

    protected Location location;

    public Machine(){}
    public void initialize(BlockState blockState){
        location = blockState.getLocation().toCenterLocation();
    }
    public abstract void tick();
}
