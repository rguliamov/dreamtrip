package com.github.rgulyamov.dreamtrip.app.diconfig;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * Register DI bindings
 *
 * @author Guliamov Rustam
 */
public class ComponentFeature implements Feature {
    @Override
    public boolean configure(FeatureContext context) {
        context.register(new ComponentBinder());
        return true;
    }
}
