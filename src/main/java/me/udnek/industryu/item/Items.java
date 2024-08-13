package me.udnek.industryu.item;

import me.udnek.industryu.IndustryU;
import me.udnek.industryu.item.techincal.TechnicalFillerItem;
import me.udnek.itemscoreu.customitem.CustomItem;
import me.udnek.itemscoreu.customitem.CustomItemRegistry;

public class Items {

    public static final CustomItem BOILER = register(new BoilerItem());
    public static final CustomItem FURNACE = register(new FurnaceItem());

    public static final CustomItem TECHNICAL_FILLER = register(new TechnicalFillerItem());

    private static CustomItem register(CustomItem customItem){
        return CustomItemRegistry.getInstance().register(IndustryU.getInstance(), customItem);
    }

}
