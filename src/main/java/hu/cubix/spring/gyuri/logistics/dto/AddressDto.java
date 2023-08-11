package hu.cubix.spring.gyuri.logistics.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddressDto {
    private Long id;

    @NotNull
    @NotEmpty
    private String countryISO;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String street;
    @NotNull
    @NotEmpty
    private String zipCode;
    @NotNull
    @NotEmpty
    private String houseNumber;
    private int latitude;
    private int longitude;

    public AddressDto() {
    }

    public AddressDto(Long id, String countryISO, String city, String street, String zipCode, String houseNumber, int latitude, int longitude) {
        this.id = id;
        this.countryISO = countryISO;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryISO() {
        return countryISO;
    }

    public void setCountryISO(String countryISO) {
        this.countryISO = countryISO;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
