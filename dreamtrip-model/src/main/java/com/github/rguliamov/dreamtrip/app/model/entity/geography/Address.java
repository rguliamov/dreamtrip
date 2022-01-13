package com.github.rguliamov.dreamtrip.app.model.entity.geography;

/**
 * @author Guliamov Rustam
 *
 * Address of the specific office or person
 */
public class Address {
    private String zipCode;

    private String street;

    private String house;

    /**
     * (Optional) Apartment number if there's
     */
    private String apartment;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
