package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.coolsimulations.ForgottenEngineers.sounds.ForgottenEngineersSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

public class ForgottenEngineersCommon {

    //public static final FEConfigCommon CONFIG = load(FVConfigCommon.class)

    public static final String MOD_ID = "forgottenengineers";
    public static final String MOD_NAME = "Forgotten Engineers";
    public static final String MOD_VERSION = "1.0.0";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    public void init() {
        ForgottenEngineersItems.init();
        ForgottenEngineersSounds.init();
    }

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
