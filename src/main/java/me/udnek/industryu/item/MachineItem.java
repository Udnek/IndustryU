package me.udnek.industryu.item;

import me.udnek.industryu.IndustryU;
import me.udnek.itemscoreu.customitem.CustomBlockItem;
import me.udnek.itemscoreu.customitem.CustomItemProperties;
import net.kyori.adventure.key.Key;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MachineItem extends CustomBlockItem, CustomItemProperties {


    @NotNull String getModelPath();

    @Override
    @Nullable
    default DataSupplier<Key> getItemModel() {
        return DataSupplier.of(new NamespacedKey(IndustryU.getInstance(), getModelPath()));
    }

}
