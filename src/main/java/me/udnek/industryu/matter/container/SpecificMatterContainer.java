package me.udnek.industryu.matter.container;

import me.udnek.industryu.matter.Matter;

public class SpecificMatterContainer extends AnyMatterContainer {
    public SpecificMatterContainer(Matter matter, int maxCapacity){
        super(maxCapacity);
        this.matter = matter;
    }
    @Override
    public boolean canFit(Matter other){
        return matter == other && !isFull();
    }
}
