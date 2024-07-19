package me.udnek.industryu.block;

import me.udnek.industryu.item.Items;
import me.udnek.industryu.techincal.MachineManager;
import me.udnek.itemscoreu.customblock.ConstructableCustomBlock;
import me.udnek.itemscoreu.utils.RightClickable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BoilerBlock extends ConstructableCustomBlock implements RightClickableMachineBlock {
    @Override
    public @NotNull String getRawId() {
        return "boiler";
    }

    @Override
    public void place(Location location) {
        super.place(location);
        onSetup(location.getBlock().getState());
    }

    @Override
    public void onDestroy(Block block) {
        super.onDestroy(block);
        onTerminate(block.getState());
    }

    @Override
    public void onLoad(BlockState blockState) {
        //LogUtils.log("Loaded: " + blockState.getLocation());
        onSetup(blockState);
    }

    @Override
    public void onUnload(BlockState blockState) {
        //LogUtils.log("Unloaded: " + blockState.getLocation());
        onTerminate(blockState);
    }

    @Override
    public ItemStack getVisualItem() {
        return Items.BOILER.getItem();
    }

    protected void onSetup(BlockState blockState){
        MachineManager.getInstance().setup(this, blockState);
    }

    protected void onTerminate(BlockState blockState){
        MachineManager.getInstance().terminate(blockState);
    }

}
