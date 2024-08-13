package me.udnek.industryu.machine.abstraction;

import me.udnek.industryu.transfer.Transferable;

public interface InputtableMachine extends Machine {
    boolean acceptsInput(Transferable transferable);
    void takeInput(Transferable transferable);
}
