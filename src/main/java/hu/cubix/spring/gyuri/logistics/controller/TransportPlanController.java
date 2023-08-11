package hu.cubix.spring.gyuri.logistics.controller;

import hu.cubix.spring.gyuri.logistics.dto.DelayDto;
import hu.cubix.spring.gyuri.logistics.service.TransportPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {

    @Autowired
    TransportPlansService transportPlansService;
    @PostMapping("/{id}/delay")
    @PreAuthorize("hasAuthority('TransportManager')")
    public ResponseEntity<String> delay(@PathVariable Long id, @RequestBody DelayDto dto){
        transportPlansService.delay(id, dto);
        return ResponseEntity.ok("ok");
    }
}
