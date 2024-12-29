package me.udnek.industryu.block;

import me.udnek.industryu.techincal.MachineManager;
import me.udnek.itemscoreu.customblock.ConstructableCustomBlock;
import me.udnek.itemscoreu.customcomponent.instance.RightClickableBlock;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

public abstract class AbstractMachineBlock extends ConstructableCustomBlock implements MachineBlock {

    @Override
    public void initializeComponents() {
        super.initializeComponents();
        getComponents().set((RightClickableBlock) (customBlock, playerInteractEvent) -> MachineManager.getInstance().onRightClick(playerInteractEvent));
    }

    @Override
    public void place(Location location) {
        super.place(location);
        setup(location.getBlock().getState());
    }

    @Override
    public void onDestroy(Block block) {
        super.onDestroy(block);
        terminate(block.getState());
    }

    @Override
    public void onLoad(BlockState blockState) {
        super.onLoad(blockState);
        setup(blockState);
    }

    @Override
    public void onUnload(BlockState blockState) {
        super.onUnload(blockState);
        terminate(blockState);
    }
}
