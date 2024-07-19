package me.udnek.industryu.gui;

import me.udnek.industryu.item.Items;
import me.udnek.itemscoreu.custominventory.ConstructableCustomInventory;
import me.udnek.itemscoreu.utils.LogUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public abstract class MachineInventory extends ConstructableCustomInventory {

    protected int[] inputSlots;
    protected int[] outputSlots;

    @Override
    public String getRawDisplayName() {
        return String.valueOf(new Random().nextFloat() * 100);
    }

    @Override
    protected void initialize() {
        super.initialize();
        inputSlots = getInputSlots();
        outputSlots = getOutputSlots();
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, Items.TECHNICAL_FILLER.getItem());
        }
        for (int allowedSlot : inputSlots) {
            inventory.setItem(allowedSlot, null);
        }
        for (int allowedSlot : outputSlots) {
            inventory.setItem(allowedSlot, null);
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(this.getRawDisplayName()).color(NamedTextColor.RED);
    }

    protected abstract int[] getInputSlots();
    protected abstract int[] getOutputSlots();

    protected boolean isAnyInteractionAllowed(int slot){
        for (int allowedSlot : outputSlots) if (allowedSlot == slot) return true;
        for (int allowedSlot : inputSlots) if (allowedSlot == slot) return true;
        return false;
    }

    protected boolean isPutAllowed(int slot){
        for (int allowedSlot : inputSlots) if (allowedSlot == slot) return true;
        return false;
    }

    // TODO: 7/18/2024 REMOVE LOGS
    @Override
    public void onPlayerClicksItem(InventoryClickEvent event) {
        //LogUtils.log("action: " + event.getAction());
        if (event.getClickedInventory() == inventory){
            if (!isAnyInteractionAllowed(event.getSlot())){
                event.setCancelled(true);
                return;
            }
            boolean isPutAction = isPutAction(event.getAction());
            boolean isPutAllowed = isPutAllowed(event.getSlot());
            //LogUtils.log("putAction: " + isPutAction);
            //LogUtils.log("putAllowed: " + isPutAllowed);
            if (isPutAction && !isPutAllowed) {
                event.setCancelled(true);
            }
        }
        else {
            // TODO: 7/18/2024 FIX MOVE TO OTHER INVENTORY
            if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                event.setCancelled(true);
            }
            //LogUtils.log("slot: " + event.getSlot());
            //LogUtils.log("rawSlot: " + event.getRawSlot());
            //LogUtils.log("item: " + event.getCurrentItem());
        }

    }

    public boolean isPutAction(InventoryAction action){
        return switch (action) {
            case PLACE_ALL, PLACE_SOME, PLACE_ONE, SWAP_WITH_CURSOR -> true;
            default -> false;
        };
    }
}
