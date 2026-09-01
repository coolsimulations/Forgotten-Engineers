package net.coolsimulations.ForgottenEngineers.event;

public class FENetworkEvents {

    public static final FEEvent<IFENetworkEvent.ClientLogout> CLIENT_LOGOUT = new FEEvent<>(listeners -> () -> {
        for(var listener : listeners)
            listener.handle();
    });
}
