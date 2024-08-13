package me.udnek.industryu.techincal;

import me.udnek.industryu.machine.abstraction.Machine;
import me.udnek.itemscoreu.utils.LogUtils;
import me.udnek.itemscoreu.utils.RightClickable;
import me.udnek.itemscoreu.utils.TickingTask;
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
    public String serialize(Block block){
        return (block.getX()+","+
                block.getY()+","+
                block.getZ()+","+
                block.getWorld().getName()
        );
    }

    public void setup(Machine machine, BlockState blockState){
        String serialized = serialize(blockState.getBlock());
        machine.initialize(blockState);
        locationToMachine.put(serialized, machine);
        LogUtils.log("Setup: " + serialized);
    }

    public void terminate(BlockState blockState){
        String serialized = serialize(blockState.getBlock());
        locationToMachine.remove(serialized);
        LogUtils.log("Terminated: " + serialized);
    }

    public Machine getMachine(Block block){
        return locationToMachine.get(serialize(block));
    }

    public void onRightClick(PlayerInteractEvent event){
        Machine machine = locationToMachine.get(serialize(event.getClickedBlock()));
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
