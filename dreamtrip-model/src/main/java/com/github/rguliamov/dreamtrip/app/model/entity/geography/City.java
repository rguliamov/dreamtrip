package com.github.rguliamov.dreamtrip.app.model.entity.geography;

import com.github.rguliamov.dreamtrip.app.model.entity.base.AbstractEntity;
import com.github.rguliamov.dreamtrip.app.model.entity.transport.TransportType;
import jakarta.persistence.*;

import static com.github.rguliamov.dreamtrip.app.infra.util.CommonUtils.*;

import java.util.*;

/**
 * @author Guliamov Rustam
 *
 * Any location that contains transport stations
 */
@Entity
@Table(name = "CITIES")
public class City extends AbstractEntity {
    @Column(name = "NAME", nullable = false, length = 32)
    private String name;

    /**
     * Name of the district where city is placed
     * (Область или State)
     */
    @Column(name = "DISTRICT", nullable = false, length = 32)
    private String district;

    /**
     * Name of the region where district is located.
     * Region is top level area in the country
     */
    @Column(name = "REGION", nullable = false, length = 32)
    private String region;

    /**
     * Set of transport stations that is linked to this city
     */
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Station> stations;

    @Deprecated
    protected City() {
    }

    public City(String name) {
        this.name = name;
    }

    /**
     * Adds specify station to the city station set
     * lazy initialization used
     *
     * @param transportType
     */
    public Station addStation(TransportType transportType) {
        Objects.requireNonNull(transportType, "stationType not be null");
        if(stations == null) {
            stations = new HashSet<>();
        }
        Station station = new Station(this, transportType);
        stations.add(station);

        return station;
    }

    public Station addStation(Station station) {
        Objects.requireNonNull(station, "Station not be null");
        if(stations == null) {
            stations = new HashSet<>();
        }
        stations.add(station);

        return station;
    }

    /**
     * Remove specify station from the city station set
     *
     * @param station
     */
    public void removeStation(Station station) {
        Objects.requireNonNull(station, "station not be null");
        if(stations == null) {
            return;
        }
        stations.remove(station);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Set<Station> getStations() {
        return getSafeSet(stations);
    }
}
