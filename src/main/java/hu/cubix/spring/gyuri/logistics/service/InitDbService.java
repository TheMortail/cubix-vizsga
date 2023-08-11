package hu.cubix.spring.gyuri.logistics.service;

import hu.cubix.spring.gyuri.logistics.model.*;
import hu.cubix.spring.gyuri.logistics.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class InitDbService {
    TPUserRepository userRepository;
    PasswordEncoder passwordEncoder;
    TransportPlanRepository transportPlanRepository;
    AddressRepository addressRepository;
    MilestoneRepository milestoneRepository;
    SectionRepository sectionRepository;
    public InitDbService(TPUserRepository userRepository, PasswordEncoder passwordEncoder, TransportPlanRepository transportPlanRepository, AddressRepository addressRepository, MilestoneRepository milestoneRepository, SectionRepository sectionRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.transportPlanRepository = transportPlanRepository;
        this.addressRepository = addressRepository;
        this.milestoneRepository = milestoneRepository;
        this.sectionRepository = sectionRepository;
    }

    @Transactional
    public void createUserIfNeeded(){
        if (!userRepository.existsById("addressManager1")){
            userRepository.save(new TPUser("addressManager1", passwordEncoder.encode("pass"), Set.of("AddressManager")));
        }

        if (!userRepository.existsById("transportManager1")){
            userRepository.save(new TPUser("transportManager1", passwordEncoder.encode("pass"), Set.of("TransportManager")));
        }
    }

    @Transactional
    public void resetDbWithDummyData(){
        transportPlanRepository.deleteAllInBatch();
        sectionRepository.deleteAllInBatch();
        milestoneRepository.deleteAllInBatch();
        addressRepository.deleteAllInBatch();

        Address address1 = addressRepository.save(new Address(1L, "HU", "Budapest", "Nagy", "1000", "19", 5, 6));
        Address address2 = addressRepository.save(new Address(2L, "HU", "Nagykáta", "Viola", "2760", "18", 10, 20));
        Address address3 = addressRepository.save(new Address(3L, "HU", "Jászberény", "Kerek", "5100", "19", 20, 61));
        Address address4 = addressRepository.save(new Address(4L, "HU", "Alsónémedi", "Traktor", "2351", "19", 45, 66));
        Address address5 = addressRepository.save(new Address(5L, "HU", "Veszprém", "Egyetem", "8200", "19", 52, 61));
        Address address6 = addressRepository.save(new Address(6L, "HU", "Budapest", "Zöld", "1000", "30", 13, 47));
        Milestone milestone1 = milestoneRepository.save(new Milestone(1L, address1, LocalDateTime.of(2023, 5, 10, 12, 0, 0)));
        Milestone milestone2 = milestoneRepository.save(new Milestone(2L, address2, LocalDateTime.of(2023, 5, 12, 12, 0, 0)));
        Milestone milestone3 = milestoneRepository.save(new Milestone(3L, address3, LocalDateTime.of(2023, 5, 13, 12, 0, 0)));
        Milestone milestone4 = milestoneRepository.save(new Milestone(4L, address4, LocalDateTime.of(2023, 6, 5, 12, 0, 0)));
        Milestone milestone5 = milestoneRepository.save(new Milestone(5L, address5, LocalDateTime.of(2023, 5, 7, 12, 0, 0)));
        Milestone milestone6 = milestoneRepository.save(new Milestone(6L, address6, LocalDateTime.of(2023, 5, 15, 12, 0, 0)));
        Section section1 = sectionRepository.save(new Section(1L, milestone1, milestone2, 0));
        Section section2 = sectionRepository.save(new Section(2L, milestone3, milestone4, 1));
        Section section3 = sectionRepository.save(new Section(3L, milestone5, milestone6, 2));
        transportPlanRepository.save(new TransportPlan(1L, 100000L, List.of(section1, section2, section3)));
    }
}
