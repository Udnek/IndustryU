package me.udnek.industryu;

import me.udnek.industryu.block.Blocks;
import me.udnek.industryu.block.MachineBlock;
import me.udnek.industryu.item.Items;
import me.udnek.industryu.item.MachineItem;
import me.udnek.industryu.techincal.MachineManager;
import me.udnek.itemscoreu.customitem.CustomItem;
import org.bukkit.plugin.java.JavaPlugin;

public final class IndustryU extends JavaPlugin {

    private static IndustryU instance;
    private MachineManager machineManager;

    public static IndustryU getInstance() {return instance;}

    @Override
    public void onEnable() {
        instance = this;

        MachineManager.getInstance().start(this);

        // BOOT
        CustomItem item = Items.BOILER;
        MachineBlock block = Blocks.BOILER;
    }

    @Override
    public void onDisable(){
        MachineManager.getInstance().stop();
    }
}
