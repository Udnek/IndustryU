package me.udnek.industryu.machine.abstraction;

import me.udnek.industryu.gui.abstraction.GUIHolder;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public interface GUIMachine extends Machine, GUIHolder, RightClickableMachine{

    @Override
    default void onRightClicks(@NotNull PlayerInteractEvent playerInteractEvent){
        if (playerInteractEvent.getPlayer().isSneaking()) return;
        playerInteractEvent.setCancelled(true);
        getGUI().open(playerInteractEvent.getPlayer());
    }
}
