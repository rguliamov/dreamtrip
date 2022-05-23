package com.github.rgulyamov.dreamtrip.app.rest.service;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.transport.TransportType;
import com.github.rgulyamov.dreamtrip.app.dto.entity.CityDTO;
import com.github.rgulyamov.dreamtrip.app.dto.transform.Transformer;
import com.github.rgulyamov.dreamtrip.app.dto.transform.impl.TransformerImpl;
import com.github.rgulyamov.dreamtrip.app.rest.service.base.BaseResource;
import com.github.rgulyamov.dreamtrip.app.service.GeographicService;
import com.github.rgulyamov.dreamtrip.app.service.impl.GeographicServiceImpl;
import org.apache.commons.lang3.math.NumberUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST web service that handles city-related requests
 *
 * @author Guliamov Rustam
 */
@Path("/cities")
@Produces(MediaType.APPLICATION_JSON)
public class CityResource extends BaseResource {
    /**
     * Source of data
     */
    private final GeographicService service = new GeographicServiceImpl();

    /**
     * DTO <-> Entity Transformer
     */
    private final Transformer transformer;

    public CityResource() {
        //service = new GeographicServiceImpl();
        transformer = new TransformerImpl();

        City city = new City("Magnitogorsk");
        city.addStation(TransportType.AVIA);
        service.saveCity(city);
    }

    /**
     * returns all the existing cities
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CityDTO> findCity() {
        return service.findCities()
                .stream()
                .map(city -> transformer.transform(city, CityDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * save new instance city
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public void saveCity(CityDTO cityDTO) {
        service.saveCity(transformer.unTransform(cityDTO, City.class));
    }

    /**
     * return city with specified identifier
     *
     * @return
     */
    @Path("/{cityId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCityByID(@PathParam("cityId") final String cityId) {
        if(!NumberUtils.isCreatable(cityId)) {
            return BAD_REQUEST;
        }

        Optional<City> city= service.findCityById(NumberUtils.toInt(cityId));
        if(!city.isPresent()) {
            return NOT_FOUND;
        }

        return ok(transformer.transform(city.get(), CityDTO.class));
    }
}
