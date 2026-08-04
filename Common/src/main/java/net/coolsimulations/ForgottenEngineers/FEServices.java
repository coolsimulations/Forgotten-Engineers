package net.coolsimulations.ForgottenEngineers;

import java.util.ServiceLoader;

public class FEServices {

    public static final FERegistration.IFERegistry REGISTRY = load(FERegistration.IFERegistry.class);

    public static <T> T load(Class<T> clazz)
    {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        ForgottenEngineersCommon.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
