package com.github.rguliamov.dreamtrip.app.model.entity.geography;

import com.github.rguliamov.dreamtrip.app.model.entity.base.AbstractEntity;
import com.github.rguliamov.dreamtrip.app.model.entity.transport.TransportType;

import java.util.Objects;

/**
 * @author Guliamov Rustam
 *
 * Station where passengers can get off or take specific kind
 * of transport. Multiple stationts compose route of the trip
 */
public class Station extends AbstractEntity {
    private City city;

    private Address address;

    /**
     * (Optional) Phone of the station
     */
    private String phone;

    private Coordinate coordinate;

    private TransportType stationType;

    /**
     * You shouldn't create {@link Station} object directly.
     * Use {@link City} functionality instead.
     *
     * @param city
     * @param stationType
     */
    public Station(final City city, final TransportType stationType) {
        this.city = Objects.requireNonNull(city);
        this.stationType = Objects.requireNonNull(stationType);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public TransportType getStationType() {
        return stationType;
    }

    public void setStationType(TransportType stationType) {
        this.stationType = stationType;
    }
}
