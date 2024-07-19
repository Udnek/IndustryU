package me.udnek.industryu.block;

import me.udnek.industryu.IndustryU;
import me.udnek.itemscoreu.customblock.CustomBlock;
import me.udnek.itemscoreu.customblock.CustomBlockManager;

public class Blocks {

    public static final MachineBlock BOILER = register(new BoilerBlock());

    private static MachineBlock register(CustomBlock customBlock){
        return (MachineBlock) CustomBlockManager.getInstance().register(IndustryU.getInstance(), customBlock);
    }
}
