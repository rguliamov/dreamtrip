package com.github.rgulyamov.dreamtrip.app.rest.service.conf;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * REST web-service configuration for Jersey
 *
 * @author Guliamov Rustam
 */
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {packages("com.github.rgulyamov.dreamtrip.app.rest");}
}
