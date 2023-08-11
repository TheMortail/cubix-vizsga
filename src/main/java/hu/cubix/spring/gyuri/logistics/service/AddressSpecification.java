package hu.cubix.spring.gyuri.logistics.service;

import hu.cubix.spring.gyuri.logistics.model.Address;
import hu.cubix.spring.gyuri.logistics.model.Address_;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecification {
    public static Specification<Address> cityPrefix (String prefix){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(Address_.CITY)), prefix.toLowerCase() + "%"));
    }

    public static Specification<Address> streetPrefix (String prefix){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get(Address_.STREET)), prefix.toLowerCase() + "%"));
    }

    public static Specification<Address> byCountry (String country) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Address_.COUNTRY_IS_O), country));
    }

    public static Specification<Address> byZipCode (String zipCode) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Address_.ZIP_CODE), zipCode));
    }
}
