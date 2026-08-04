package net.coolsimulations.ForgottenEngineers.event;

public class FERenderEvents {

    public static final FEEvent<IFERenderEvent.TooltipComponent> TOOLTIP_COMPONENT = new FEEvent<>(listeners -> (register) -> {
        for(var listener : listeners)
            listener.handle(register);
    });

    public static final FEEvent<IFERenderEvent.MouseAction> MOUSE_ACTION = new FEEvent<>(listeners -> (register) -> {
        for(var listener : listeners)
            listener.handle(register);
    });
}
