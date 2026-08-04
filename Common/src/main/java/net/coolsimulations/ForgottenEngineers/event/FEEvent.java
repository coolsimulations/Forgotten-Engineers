package net.coolsimulations.ForgottenEngineers.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FEEvent<T extends IFEEvent> {

    private final List<T> listeners = new ArrayList<>();
    private final Function<List<T>, T> invoker;

    public FEEvent(Function<List<T>, T> invoker) {
        this.invoker = invoker;
    }

    public void register(T listener) {
        this.listeners.add((listener));
    }

    public T post() {
        return this.invoker.apply(this.listeners);
    }
}
