package me.udnek.industryu.machine.abstraction;

import me.udnek.industryu.gui.abstraction.GUIHolder;
import me.udnek.itemscoreu.utils.RightClickable;
import org.bukkit.event.player.PlayerInteractEvent;

public interface GUIMachine extends Machine, GUIHolder, RightClickable {

    @Override
    default void onRightClicks(PlayerInteractEvent playerInteractEvent){
        if (playerInteractEvent.getPlayer().isSneaking()) return;
        playerInteractEvent.setCancelled(true);
        getGUI().open(playerInteractEvent.getPlayer());
    }
}
