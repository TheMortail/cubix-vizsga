package hu.cubix.spring.gyuri.logistics.controller;

import hu.cubix.spring.gyuri.logistics.dto.AddressDto;
import hu.cubix.spring.gyuri.logistics.dto.AddressSearchRequestDto;
import hu.cubix.spring.gyuri.logistics.dto.ResponseDto;
import hu.cubix.spring.gyuri.logistics.model.Address;
import hu.cubix.spring.gyuri.logistics.service.AddressService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;
    @PostMapping
    @PreAuthorize("hasAuthority('AddressManager')")
    public ResponseEntity<ResponseDto> createAddress(@RequestBody @Valid AddressDto dto){
        return ResponseEntity.ok(new ResponseDto(addressService.create(dto).getId()));
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll(){
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(addressService.findById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('AddressManager')")
    public void delete(@PathVariable Long id){
        addressService.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('AddressManager')")
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @RequestBody @Valid AddressDto dto){
        return ResponseEntity.ok(addressService.update(id, dto));
    }

    @PostMapping("/search")
    public ResponseEntity<List<AddressDto>> findBySpecification(@RequestBody AddressSearchRequestDto dto, @SortDefault("id") Pageable pageable, HttpServletResponse response){
        Page<Address> pages = addressService.findBySpecification(dto, pageable);
        response.addHeader("X-Total-Count", String.valueOf(pages.getTotalElements()));
        return ResponseEntity.ok(pages.stream().map(addressService::mapper).collect(Collectors.toList()));
    }

}
