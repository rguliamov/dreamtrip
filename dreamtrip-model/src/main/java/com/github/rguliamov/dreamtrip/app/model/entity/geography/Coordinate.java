package com.github.rguliamov.dreamtrip.app.model.entity.geography;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

/**
 * @author Guliamov Rustam
 *
 * Geography coordinate of an object
 */
@Embeddable
public class Coordinate {
    @Column(name = "X")
    private double x;

    @Column(name = "Y")
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Deprecated
    protected Coordinate() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
