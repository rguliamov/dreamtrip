package com.github.rgulyamov.dreamtrip.app.rest.service;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


/**
 * REST web service that handles city-related requests
 *
 * @author Guliamov Rustam
 */
@Path("/cities")
@Produces(MediaType.APPLICATION_JSON)
public class CityResource {
    List<City> cityList = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<City> findCity() {
        cityList.add(new City("St. Petersburg"));
        return cityList;
    }
}
