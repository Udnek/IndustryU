package me.udnek.industryu.machine;

import me.udnek.industryu.gui.FurnaceGUI;
import me.udnek.industryu.gui.abstraction.MachineGUI;
import me.udnek.industryu.machine.abstraction.AbstractMachine;
import me.udnek.industryu.machine.abstraction.GUIMachine;
import me.udnek.industryu.machine.abstraction.InputtableMachine;
import me.udnek.industryu.matter.Matters;
import me.udnek.industryu.matter.container.MatterContainer;
import me.udnek.industryu.matter.container.SpecificMatterContainer;
import me.udnek.industryu.transfer.Transferable;
import org.bukkit.block.BlockState;

public class Furnace extends AbstractMachine implements GUIMachine, InputtableMachine {
    protected FurnaceGUI gui;
    protected SpecificMatterContainer steamContainer = new SpecificMatterContainer(Matters.STEAM, 1000);
    @Override
    public void initialize(BlockState blockState) {
        super.initialize(blockState);
        gui = new FurnaceGUI(this);
    }

    @Override
    public void tick() {
        if (shouldSkipTick(20)) return;
        gui.synchronizeWithMachine();
    }

    public SpecificMatterContainer getSteamContainer() {return steamContainer;}
    @Override
    public MachineGUI getGUI() {
        return gui;
    }

    @Override
    public boolean acceptsInput(Transferable transferable) {
        if (transferable instanceof MatterContainer otherContainer){
            return steamContainer.canTakeAnyFrom(otherContainer);
        }
        return false;
    }
    @Override
    public void takeInput(Transferable transferable) {
        steamContainer.takeFrom((MatterContainer) transferable);
    }
}
