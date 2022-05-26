package com.github.rgulyamov.dreamtrip.app.rest.service.conf;

import com.github.rgulyamov.dreamtrip.app.diconfig.ComponentFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * REST web-service configuration for Jersey
 *
 * @author Guliamov Rustam
 */
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        super(ComponentFeature.class);
        packages("com.github.rgulyamov.dreamtrip.app.rest");
    }
}
