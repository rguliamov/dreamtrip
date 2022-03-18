package com.github.rgulyamov.dreamtrip.app.dto.transform.impl;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rgulyamov.dreamtrip.app.dto.entity.CityDTO;
import com.github.rgulyamov.dreamtrip.app.dto.transform.Transformer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * verifies functionality of the {@link TransformerImpl} unit
 *
 * @author Guliamov Rustam
 */
class TransformerImplTest {
    private static Transformer transformer;

    @BeforeAll
    public static void setup() {
        transformer = new TransformerImpl();
    }

    @Test
    public void testTransformSuccess() {
        City city = new City("Magnitogorsk");
        city.setDistrict("Chelyabinsk Oblast");
        city.setRegion("none");
        city.setId(1);

        CityDTO dto = transformer.transform(city, CityDTO.class);
        assertNotNull(dto);
        assertEquals(dto.getName(), city.getName());
        assertEquals(dto.getDistrict(), city.getDistrict());
        assertEquals(dto.getRegion(), city.getRegion());
        assertEquals(dto.getId(), city.getId());
    }

    @Test
    public void testUnTransformSuccess() {
        CityDTO dto = new CityDTO();
        dto.setName("Magnitogorsk");
        dto.setId(1);
        dto.setRegion("Chelyabinsk Oblast");
        dto.setDistrict("none");

        City city = transformer.unTransform(dto, City.class);
        assertNotNull(city);
        assertEquals(dto.getName(), city.getName());
        assertEquals(dto.getDistrict(), city.getDistrict());
        assertEquals(dto.getRegion(), city.getRegion());
        assertEquals(dto.getId(), city.getId());
    }
}