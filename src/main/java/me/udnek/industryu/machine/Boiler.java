package me.udnek.industryu.machine;

import com.destroystokyo.paper.ParticleBuilder;
import me.udnek.industryu.gui.BoilerInventory;
import me.udnek.industryu.techincal.MachineManager;
import me.udnek.industryu.transfer.PipeConnector;
import me.udnek.industryu.transfer.TransferBlock;
import me.udnek.itemscoreu.utils.RightClickable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Boiler extends Machine implements InventoryHolder, RightClickable {

    protected Inventory inventory;
    protected ParticleBuilder particle;
    protected int progress = 0;

    @Override
    public void initialize(BlockState blockState) {
        super.initialize(blockState);
        this.inventory = new BoilerInventory().getInventory();
        particle = new ParticleBuilder(Particle.SMOKE);
        particle.count(10);
        particle.extra(0);
        particle.offset(0.3, 0.3, 0.3);
        particle.location(location);
        particle.spawn();
    }

    @Override
    public void tick() {
        if (Bukkit.getCurrentTick() % 5 != 0) return;

        if (progress == 0){
            ItemStack item = inventory.getItem(0);
            if (item == null) return;
            inventory.setItem(0, item.subtract());
            progress = 1;
        }
        else if (progress < 5){
            particle.spawn();
            ItemStack item = inventory.getItem(3);
            item.setAmount(progress);
            inventory.setItem(3, item);
            progress += 1;
        }
        else {
            inventory.setItem(inventory.getSize()-1, new ItemStack(Material.GOLD_INGOT));
            progress = 0;
            new PipeConnector().start(30, BlockFace.DOWN, TransferBlock.getAlong(location, BlockFace.UP));
        }

    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    @Override
    public void onRightClicks(PlayerInteractEvent playerInteractEvent) {
        playerInteractEvent.getPlayer().openInventory(inventory);
    }
}
