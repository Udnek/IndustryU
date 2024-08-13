package me.udnek.industryu.machine.abstraction;

import me.udnek.industryu.techincal.MachineManager;
import me.udnek.industryu.transfer.PipeConnector;
import me.udnek.industryu.transfer.Transferable;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.block.Block;

import java.util.List;

public interface OutputtableMachine extends Machine{
    default void output(Transferable transferable){
        List<Block> destinations = PipeConnector.findDestinationsFromExternal(30, getLocation().getBlock());
        MachineManager machineManager = MachineManager.getInstance();
        for (Block destination : destinations) {
            Machine machine = machineManager.getMachine(destination);
            if (machine == null) continue;
            PipeConnector.debug(destination, NamedTextColor.DARK_AQUA.value(), "FOUND MACHINE", 20*2);
            if (!(machine instanceof InputtableMachine inputtableMachine)) continue;
            if (!inputtableMachine.acceptsInput(transferable)) continue;
            PipeConnector.debug(destination, NamedTextColor.BLUE.value(), "SUCCESS", 20*3);
            inputtableMachine.takeInput(transferable);
            if (!transferable.needFurtherTransportation()) return;
        }
    }

}
