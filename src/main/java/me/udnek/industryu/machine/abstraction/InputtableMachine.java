package me.udnek.industryu.machine.abstraction;

import me.udnek.industryu.transfer.Transferable;
import org.jetbrains.annotations.NotNull;

public interface InputtableMachine extends Machine {
    boolean acceptsInput(@NotNull Transferable transferable);
    void takeInput(@NotNull Transferable transferable);
}
