package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.client.FEClientEvents;

public class ForgottenEngineersClientCommon extends ForgottenEngineersCommon {

    @Override
    public void init() {
        if (FEServices.REGISTRY.getPlatformType() != FERegistration.PlatformType.FABRIC)
            super.init();
        FEClientEvents.init();
    }
}
