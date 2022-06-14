package com.github.rguliamov.dreamtrip.app.model.search.criteria;

import com.github.rguliamov.dreamtrip.app.model.entity.transport.TransportType;

/**
 * @author Guliamov Rustam
 *
 * selection criteria for the stations search operation
 */
public class StationCriteria {

    private String cityName;

    private TransportType transportType;

    private String address;

    /**
     * returns selection criteria to search stations that
     * contains specified name parameter
     *
     * @param cityName
     */
    public StationCriteria(String cityName) {
        this.cityName = cityName;
    }

    public StationCriteria(TransportType transportType) {
        this.transportType = transportType;
    }

    public StationCriteria() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
