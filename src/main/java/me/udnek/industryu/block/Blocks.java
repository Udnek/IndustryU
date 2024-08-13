package me.udnek.industryu.block;

import me.udnek.industryu.IndustryU;
import me.udnek.itemscoreu.customblock.CustomBlock;
import me.udnek.itemscoreu.customblock.CustomBlockRegistry;

public class Blocks {

    public static final MachineBlock BOILER = register(new BoilerBlock());
    public static final MachineBlock FURNACE = register(new FurnaceBlock());

    private static MachineBlock register(CustomBlock customBlock){
        return (MachineBlock) CustomBlockRegistry.getInstance().register(IndustryU.getInstance(), customBlock);
    }
}
