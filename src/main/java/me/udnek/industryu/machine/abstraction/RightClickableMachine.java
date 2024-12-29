package me.udnek.industryu.machine.abstraction;

import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public interface RightClickableMachine extends Machine{
    void onRightClicks(@NotNull PlayerInteractEvent playerInteractEvent);
}
