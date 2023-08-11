package hu.cubix.spring.gyuri.logistics.service;

import hu.cubix.spring.gyuri.logistics.dto.AddressDto;
import hu.cubix.spring.gyuri.logistics.dto.AddressSearchRequestDto;
import hu.cubix.spring.gyuri.logistics.mapper.AddressMapper;
import hu.cubix.spring.gyuri.logistics.model.Address;
import hu.cubix.spring.gyuri.logistics.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Transactional
    public Address create(AddressDto dto){
        if (dto.getId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return addressRepository.save(addressMapper.dtoToEntity(dto));
    }

    public List<AddressDto> findAll() {
        return addressRepository.findAll().stream().map(addressMapper::entityToDto).collect(Collectors.toList());
    }

    public AddressDto findById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return addressMapper.entityToDto(addressOptional.get());
    }

    @Transactional
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Transactional
    public AddressDto update(Long id, AddressDto dto) {
        if (dto.getId() != null && !dto.getId().equals(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        dto.setId(id);
        Optional<Address> addressOptional = addressRepository.findById(dto.getId());

        if (addressOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return addressMapper.entityToDto(addressRepository.save(addressMapper.dtoToEntity(dto)));
    }

    public Page<Address> findBySpecification(AddressSearchRequestDto dto, Pageable pageable){
        if (dto == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Specification<Address> specification = Specification.where(null);

        if (!StringUtils.isEmpty(dto.getCountryISO())){
            specification = specification.and(AddressSpecification.byCountry(dto.getCountryISO()));
        }

        if (!StringUtils.isEmpty(dto.getZipCode())){
            specification = specification.and(AddressSpecification.byZipCode(dto.getZipCode()));
        }

        if (!StringUtils.isEmpty(dto.getStreet())){
            specification = specification.and(AddressSpecification.streetPrefix(dto.getStreet()));
        }

        if (!StringUtils.isEmpty(dto.getCity())){
            specification = specification.and(AddressSpecification.cityPrefix(dto.getCity()));
        }

        return addressRepository.findAll(specification, pageable);
    }

    public AddressDto mapper(Address entity){
        return addressMapper.entityToDto(entity);
    }
}
