package com.github.rguliamov.dreamtrip.app.model.entity.geography;

import com.github.rguliamov.dreamtrip.app.model.entity.base.AbstractEntity;
import com.github.rguliamov.dreamtrip.app.model.entity.transport.TransportType;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.StationCriteria;
import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;
    
import java.util.Objects;

/**
 * @author Guliamov Rustam
 *
 * Station where passengers can get off or take specific kind
 * of transport. Multiple stationts compose route of the trip
 */
@Entity
@Table(name = "STATIONS")
public class Station extends AbstractEntity {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {})
    @JoinColumn(name = "CITY_ID")
    private City city;

    @Embedded
    private Address address;

    /**
     * (Optional) Phone of the station
     */
    @Column(name = "PHONE", length = 16)
    private String phone;

    @Embedded
    private Coordinate coordinate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "TRANSPORT_TYPE", nullable = false)
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

    @Deprecated
    protected Station() {
    }

    /**
     * Verifies if current station matches specified criteria
     *
     * @param criteria
     * @return
     */
    public boolean match(StationCriteria criteria) {
        Objects.requireNonNull(criteria, "criteria isn't initialization");

        if(!StringUtils.isEmpty(criteria.getCityName())) {
            if (!criteria.getCityName().equals(this.getCity().getName()))
                return false;
        }

        if(criteria.getTransportType() != null) {
            if(!criteria.getTransportType().equals(this.getStationType()))
                return false;
        }

        if(!StringUtils.isEmpty(criteria.getAddress())) {
            if(!criteria.getAddress().equals(this.getAddress()))
                return false;
        }

        return true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Station station = (Station) o;
        return Objects.equals(city, station.city) && Objects.equals(address, station.address) && stationType == station.stationType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), city, address, stationType);
    }

    @Override
    public String toString() {
        return "Station{" +
                "city=" + city.getName() +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", stationType=" + stationType +
                '}';
    }
}
