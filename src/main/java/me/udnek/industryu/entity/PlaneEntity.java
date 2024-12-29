package me.udnek.industryu.entity;

import me.udnek.itemscoreu.customentity.ConstructableCustomEntity;
import me.udnek.itemscoreu.customentity.CustomEntityType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlaneEntity extends ConstructableCustomEntity<Horse> {
    @Override
    public @NotNull EntityType getVanillaEntityType() {return EntityType.HORSE;}

    @Override
    public void onSpawn() {
        entity.setAware(false);
        entity.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        //entity.addPassenger(entity.getWorld().spawnEntity(entity.getLocation(), EntityType.RABBIT));
    }

    @Override
    public void unload() {
    }

    @Override
    public void tick() {
        if (entity.getPassengers().isEmpty()) return;
        entity.setVelocity(entity.getPassengers().getFirst().getLocation().getDirection().normalize().multiply(0.5));
    }

    @Override
    public @NotNull CustomEntityType<?> getType() {
        return EntityTypes.PLANE;
    }
}
