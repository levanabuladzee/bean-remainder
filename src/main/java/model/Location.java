package model;

import annotation.GEOCity;
import annotation.GEOZipCode;

public class Location {
    @GEOCity
    private String city;
    @GEOZipCode
    private String zipCode;

    public Location() {
    }

    public Location(String country, String zip) {
        this.city = country;
        this.zipCode = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "country='" + city + '\'' +
                ", zip='" + zipCode + '\'' +
                '}';
    }
}
