package me.udnek.industryu.matter.container;

import com.google.common.base.Preconditions;
import me.udnek.industryu.matter.Matter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnyMatterContainer implements MatterContainer {

    protected int maxCapacity;
    protected int amount;
    protected Matter matter;
    public AnyMatterContainer(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void poutTo(MatterContainer other) {
        if (!other.canTakeAnyFrom(this)) return;
        int actualAmount = other.add(getMatter(), amount);
        amount -= actualAmount;
    }
    @Override
    public boolean canFit(Matter other) {
        if (isEmpty()) return true;
        return other == matter && !isFull();
    }
    @Override
    public boolean canTakeAnyFrom(MatterContainer other){
        return canFit(other.getMatter());
    }
    @Override
    public Matter getMatter() {return matter;}
    @Override
    public int getMaxCapacity() {return maxCapacity;}
    @Override
    public int getAmount() {return amount;}
    @Override
    public int add(Matter otherMatter, int addAmount){
        Preconditions.checkArgument(addAmount >= 0, "Amount can not be negative!");
        if (addAmount == 0) return 0;
        if (!canFit(otherMatter)) return 0;
        if (amount + addAmount > maxCapacity){
            int actualAmount = maxCapacity-amount;
            amount = maxCapacity;
            return actualAmount;
        }
        amount += addAmount;
        return addAmount;
    }

    @Override
    public void add(int add) {
        if (amount + add > maxCapacity){
            amount = maxCapacity;
            return;
        }
        amount += add;
    }

    @Override
    public ItemStack getVisualRepresentation() {
        ItemStack itemStack;
        if (matter == null){
            itemStack = new ItemStack(Material.BUCKET);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.displayName(Component.text(
                    "Empty" +
                            " ("+amount+"/"+maxCapacity+")"
            ));
            itemStack.setItemMeta(itemMeta);
        }
        else {
            itemStack = matter.getVisualRepresentation();
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.displayName(itemMeta.displayName().append(Component.text(" ("+amount+"/"+maxCapacity+")")));
            itemStack.setItemMeta(itemMeta);

        }
        return itemStack;

    }
}
