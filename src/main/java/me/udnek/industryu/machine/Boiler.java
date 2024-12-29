package me.udnek.industryu.machine;

import com.destroystokyo.paper.ParticleBuilder;
import me.udnek.industryu.gui.BoilerGUI;
import me.udnek.industryu.gui.abstraction.MachineGUI;
import me.udnek.industryu.machine.abstraction.AbstractMachine;
import me.udnek.industryu.machine.abstraction.GUIMachine;
import me.udnek.industryu.machine.abstraction.OutputtableMachine;
import me.udnek.industryu.matter.Matters;
import me.udnek.industryu.matter.container.SpecificMatterContainer;
import org.bukkit.Particle;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Boiler extends AbstractMachine implements GUIMachine, OutputtableMachine {

    public static final int TIME = 10;

    protected SpecificMatterContainer steamContainer = new SpecificMatterContainer(Matters.STEAM, 100);
    protected BoilerGUI gui;
    protected ParticleBuilder particle;
    protected int progress = 0;

    @Override
    public void initialize(@NotNull BlockState blockState) {
        super.initialize(blockState);
        this.gui = new BoilerGUI(this);
        particle = new ParticleBuilder(Particle.SMOKE);
        particle.count(15);
        particle.extra(0);
        particle.offset(0.25, 0.4, 0.25);
        particle.location(location);
    }

    @Override
    public void tick() {
        if (shouldSkipTick(5)) return;

        gui.synchronizeWithMachine();

        if (progress == 0){
            if (steamContainer.isFull()){
                output(steamContainer);
                return;
            }
            ItemStack fuel = gui.takeFuel();
            if (fuel == null) return;
            progress = 1;
        } else if (progress < TIME){
            progress++;
            gui.synchronizeWithMachine();
            particle.spawn();
        } else {
            progress = 0;
            steamContainer.add(10);
            output(steamContainer);
        }

    }


    public SpecificMatterContainer getSteamContainer() {
        return steamContainer;
    }

    public int getProgress() {return progress;}

    @Override
    public @NotNull MachineGUI getGUI() {return gui;}
}
