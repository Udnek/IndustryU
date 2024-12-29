package me.udnek.industryu;

import me.udnek.industryu.block.Blocks;
import me.udnek.industryu.block.MachineBlock;
import me.udnek.industryu.entity.EntityTypes;
import me.udnek.industryu.entity.PlaneEntity;
import me.udnek.industryu.item.Items;
import me.udnek.industryu.techincal.MachineManager;
import me.udnek.itemscoreu.customentity.CustomEntityType;
import me.udnek.itemscoreu.customitem.CustomItem;
import me.udnek.itemscoreu.resourcepack.ResourcePackablePlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class IndustryU extends JavaPlugin implements ResourcePackablePlugin {

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
        CustomEntityType<PlaneEntity> plane = EntityTypes.PLANE;
    }

    @Override
    public void onDisable(){
        MachineManager.getInstance().stop();
    }
}
