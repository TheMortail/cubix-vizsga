package hu.cubix.spring.gyuri.logistics.dto;

public class AddressSearchRequestDto {
    private String countryISO;
    private String city;
    private String street;
    private String zipCode;

    public AddressSearchRequestDto() {
    }

    public AddressSearchRequestDto(String countryISO, String city, String street, String zipCode) {
        this.countryISO = countryISO;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
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
}
