package me.udnek.industryu.matter.container;

import me.udnek.industryu.matter.Matter;
import me.udnek.industryu.transfer.Transferable;
import org.bukkit.inventory.ItemStack;

public interface MatterContainer extends Transferable {
    void poutTo(MatterContainer other);
    default void takeFrom(MatterContainer other){
        other.poutTo(this);
    }
    int add(Matter matter, int amount);
    void add(int amount);
    boolean canTakeAnyFrom(MatterContainer other);
    boolean canFit(Matter other);
    Matter getMatter();
    int getMaxCapacity();
    int getAmount();
    default boolean isFull(){return getAmount() == getMaxCapacity();}
    default boolean isEmpty(){return getAmount() == 0;}
    ItemStack getVisualRepresentation();
    @Override
    default boolean needFurtherTransportation(){return isEmpty();}
}
