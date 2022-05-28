package com.github.rguliamov.dreamtrip.app.model.entity.geography;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * @author Guliamov Rustam
 *
 * Address of the specific office or person
 */
@Embeddable
public class Address {
    @Column(name = "ZIP_CODE", length = 10)
    private String zipCode;

    @Column(name = "STREET", length = 32)
    private String street;

    @Column(name = "HOUSE_NO", length = 16)
    private String house;

    /**
     * (Optional) Apartment number if there's
     */
    @Column(name = "APARTMENT", length = 16)
    private String apartment;

    public Address() {
    }

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

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
