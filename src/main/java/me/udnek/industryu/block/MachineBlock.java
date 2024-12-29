package me.udnek.industryu.block;

import me.udnek.industryu.machine.abstraction.Machine;
import me.udnek.industryu.techincal.MachineManager;
import me.udnek.itemscoreu.customblock.CustomBlock;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

public interface MachineBlock extends CustomBlock {

    @NotNull Machine getNewMachine();

    default void setup(BlockState blockState){
        MachineManager.getInstance().setup(getNewMachine(), blockState);
    }
    default void terminate(BlockState blockState){
        MachineManager.getInstance().terminate(blockState);
    }
}
