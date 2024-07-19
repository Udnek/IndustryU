package me.udnek.industryu.block;

import me.udnek.industryu.techincal.MachineManager;
import me.udnek.itemscoreu.utils.RightClickable;
import org.bukkit.event.player.PlayerInteractEvent;

public interface RightClickableMachineBlock extends MachineBlock, RightClickable {
    @Override
    default void onRightClicks(PlayerInteractEvent playerInteractEvent){
        if (playerInteractEvent.getPlayer().isSneaking()) return;
        playerInteractEvent.setCancelled(true);
        MachineManager.getInstance().onRightClick(playerInteractEvent);
    }
}
