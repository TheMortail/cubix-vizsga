package hu.cubix.spring.gyuri.logistics.mapper;

import hu.cubix.spring.gyuri.logistics.dto.AddressDto;
import hu.cubix.spring.gyuri.logistics.model.Address;
import org.mapstruct.Mapper;


@Mapper(componentModel = "Spring")
public interface AddressMapper {
    AddressDto entityToDto(Address entity);
    Address dtoToEntity(AddressDto dto);
}
