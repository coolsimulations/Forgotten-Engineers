package net.coolsimulations.ForgottenEngineers.event;

public interface IFENetworkEvent extends IFEEvent {

    interface ClientLogout extends IFENetworkEvent {
        void handle();
    }
}
