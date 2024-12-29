package me.udnek.industryu.entity;

import me.udnek.industryu.IndustryU;
import me.udnek.itemscoreu.customentity.CustomEntityType;
import me.udnek.itemscoreu.customregistry.CustomRegistries;
import org.jetbrains.annotations.NotNull;

public class EntityTypes {

    public static final CustomEntityType<PlaneEntity> PLANE = register(new CustomEntityType<PlaneEntity>("plane") {
        @Override
        protected @NotNull PlaneEntity getNewCustomEntityClass() {
            return new PlaneEntity();
        }
    });

    private static <T extends CustomEntityType<?>> T register(T customEntityType){
        return (T) CustomRegistries.ENTITY_TYPE.register(IndustryU.getInstance(), customEntityType);
    }
}
