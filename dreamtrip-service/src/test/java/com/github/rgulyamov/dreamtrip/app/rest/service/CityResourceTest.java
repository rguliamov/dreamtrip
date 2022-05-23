package com.github.rgulyamov.dreamtrip.app.rest.service;

import com.github.rgulyamov.dreamtrip.app.dto.entity.CityDTO;
import com.github.rgulyamov.dreamtrip.app.rest.service.conf.JerseyConfig;
import com.github.rgulyamov.dreamtrip.app.service.GeographicService;
import com.github.rgulyamov.dreamtrip.app.service.impl.GeographicServiceImpl;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

/**
 * {@link CityResourceTest} is integration test that verifies {@link CityResource}
 *
 * @author Guliamov Rustam
 */
public class CityResourceTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new JerseyConfig();
    }

    @Test
    public void testFindCitySuccess() {
        List<Map<String, String>> cityList = target("cities").request().get(List.class);
        assertNotNull(cityList);
        assertEquals(cityList.size(), 1);

        Map<String, String> city = cityList.get(0);
        assertEquals(city.get("name"), "Magnitogorsk");
    }

    @Test
    public void testFindCityByIdSuccess() {
        CityDTO dto = target("cities/1").request().get(CityDTO.class);

        assertNotNull(dto);
        assertEquals(dto.getName(), "Magnitogorsk");
        assertEquals(dto.getId(), 1);
    }

    @Test
    public void testFindCityByIdNotFound() {
        Response response = target("/cities/2").request().get();

        assertNotNull(response);
        assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void testFindCityByIdInvalidParameter() {
        Response response = target("/cities/hi").request().get();

        assertNotNull(response);
        assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    public void testSaveCitySuccess() {
        CityDTO dto = new CityDTO();
        dto.setName("Kiev");

        Response response = target("cities/add").request().post(Entity.entity(dto, MediaType.APPLICATION_JSON_TYPE));
        assertEquals(response.getStatus(), Response.Status.NO_CONTENT.getStatusCode());
    }
}
