package me.udnek.industryu.techincal;

import me.udnek.industryu.machine.Boiler;
import me.udnek.industryu.machine.Machine;
import me.udnek.itemscoreu.customblock.CustomBlock;
import me.udnek.itemscoreu.utils.LogUtils;
import me.udnek.itemscoreu.utils.RightClickable;
import me.udnek.itemscoreu.utils.TickingTask;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

public class MachineManager extends TickingTask {

    private final Map<String, Machine> locationToMachine = new HashMap<>();

    private static MachineManager instance;
    private MachineManager(){}
    public static MachineManager getInstance() {
        if (instance == null) instance = new MachineManager();
        return instance;
        
    }
    
    
    public String serialize(Location location){
        return (location.getBlockX()+","+
                location.getBlockY()+","+
                location.getBlockZ()+","+
                location.getWorld().getName());
    }

    public void setup(CustomBlock customBlock, BlockState blockState){
        String serialized = serialize(blockState.getLocation());
        Machine machine = new Boiler();
        machine.initialize(blockState);
        locationToMachine.put(serialized, machine);
        LogUtils.log("Setup: " + serialized);
    }

    public void terminate(BlockState blockState){
        String serialized = serialize(blockState.getLocation());
        locationToMachine.remove(serialized);
        LogUtils.log("Terminated: " + serialized);
    }


    public void onRightClick(PlayerInteractEvent event){
        Machine machine = locationToMachine.get(serialize(event.getClickedBlock().getLocation()));
        if (machine instanceof RightClickable rightClickable) rightClickable.onRightClicks(event);
    }



    @Override
    public int getDelay() {return 1;}

    @Override
    public void run() {
        for (Machine machine : locationToMachine.values()) {
            machine.tick();
        }
    }
}
