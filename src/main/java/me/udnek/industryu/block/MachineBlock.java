package me.udnek.industryu.block;

import me.udnek.industryu.machine.abstraction.Machine;
import me.udnek.industryu.techincal.MachineManager;
import me.udnek.itemscoreu.customblock.CustomBlock;
import me.udnek.itemscoreu.utils.RightClickable;
import org.bukkit.block.BlockState;
import org.bukkit.event.player.PlayerInteractEvent;

public interface MachineBlock extends CustomBlock, RightClickable {
    @Override
    default void onRightClicks(PlayerInteractEvent playerInteractEvent){
        MachineManager.getInstance().onRightClick(playerInteractEvent);
    }
    Machine getNewMachine();

    default void setup(BlockState blockState){
        MachineManager.getInstance().setup(getNewMachine(), blockState);
    }
    default void terminate(BlockState blockState){
        MachineManager.getInstance().terminate(blockState);
    }
}
