package hu.cubix.spring.gyuri.logistics.controller;

import hu.cubix.spring.gyuri.logistics.dto.DelayDto;
import hu.cubix.spring.gyuri.logistics.dto.LoginDto;
import hu.cubix.spring.gyuri.logistics.model.Address;
import hu.cubix.spring.gyuri.logistics.model.Milestone;
import hu.cubix.spring.gyuri.logistics.model.Section;
import hu.cubix.spring.gyuri.logistics.model.TransportPlan;
import hu.cubix.spring.gyuri.logistics.repository.AddressRepository;
import hu.cubix.spring.gyuri.logistics.repository.MilestoneRepository;
import hu.cubix.spring.gyuri.logistics.repository.SectionRepository;
import hu.cubix.spring.gyuri.logistics.repository.TransportPlanRepository;
import hu.cubix.spring.gyuri.logistics.service.InitDbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "100000")
public class TransportPlanControllerIT {
    private static final String TEST_USERNAME = "transportManager1";
    private static final String TEST_PASS = "pass";

    //Ez nem akart a világért sem akart működni, ezért manuálisan adtam meg egy konstansban a jwt-t a tesztekhez
    private static final String JWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0cmFuc3BvcnRNYW5hZ2VyMSIsImF1dGgiOlsiVHJhbnNwb3J0TWFuYWdlciJdLCJleHAiOjE2OTE3NzIwMjMsImlzcyI6IlRyYW5zcG9ydFBsYW5zQXBwIn0.KAMizEC2wj6vbSrMa0y8TiUpuE3PDAvmX-IkLyuVLRY";

    //Ennél ugyan az a gond, egy másik user jwt-je kell, akinek nincs joga ehez az apihoz
    private static final String FAKE_JWT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZGRyZXNzTWFuYWdlcjEiLCJhdXRoIjpbIkFkZHJlc3NNYW5hZ2VyIl0sImV4cCI6MTY5MTc3MjI5OCwiaXNzIjoiVHJhbnNwb3J0UGxhbnNBcHAifQ.A-hm-bw1jm2faJWVjQ43ypslY8x2wRkzCXXYzSvCznY";

    @Autowired
    WebTestClient webTestClient;
    String API_TRANSPORTPLANS = "/api/transportPlans";
    private String jwt;
    @Autowired
    InitDbService initDbService;
    @Autowired
    TransportPlanRepository transportPlanRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    MilestoneRepository milestoneRepository;
    @Autowired
    SectionRepository sectionRepository;

    @BeforeEach
    public void init(){
        transportPlanRepository.deleteAllInBatch();
        sectionRepository.deleteAllInBatch();
        milestoneRepository.deleteAllInBatch();
        addressRepository.deleteAllInBatch();
        initDbService.createUserIfNeeded();

        LoginDto body = new LoginDto();
        body.setUsername(TEST_USERNAME);
        body.setUsername(TEST_PASS);
        jwt = webTestClient.post()
                .uri("/api/login")
                .bodyValue(body)
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        //Ez nem akart a világért sem működni, ezért manuálisan adtam meg egy konstansban a jwt-t a tesztekhez
        //System.out.println(jwt);
        //{"timestamp":"2023-08-11T15:51:16.256+00:00","status":403,"error":"Forbidden","path":"/api/login"}
    }

    @Test
    public void delayLvl1From(){
        delayFrom(15, 99000L);
    }

    @Test
    public void delayLvl1To(){
        delayTo(15, 99000L);
    }

    @Test
    public void delayLvl2From(){
        delayFrom(30, 98000L);
    }

    @Test
    public void delayLvl2To(){
        delayTo(30, 98000L);
    }

    @Test
    public void delayLvl3From(){
        delayFrom(60, 97000L);
    }

    @Test
    public void delayLvl3To(){
        delayTo(60, 97000L);
    }

    @Test
    public void delayLvl4From(){
        delayFrom(120, 96000L);
    }

    @Test
    public void delayLvl4To(){
        delayTo(120, 96000L);
    }

    @Test
    public void notAuthorized(){
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
        TransportPlan transportPlan = transportPlanRepository.save(new TransportPlan(1L, 100000L, List.of(section1, section2, section3)));

        LoginDto body = new LoginDto();
        body.setUsername("addressManager1");
        body.setUsername("pass");
        jwt = webTestClient.post()
                .uri("/api/login")
                .bodyValue(body)
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        webTestClient.post()
                .uri(API_TRANSPORTPLANS+"/"+transportPlan.getId()+"/delay")
//                .headers(header -> header.setBearerAuth(jwt))
                .headers(header -> header.setBearerAuth(FAKE_JWT))
                .bodyValue(new DelayDto(milestone1.getId(), 10))
                .exchange()
                .expectStatus().isForbidden();
    }

    private void delayFrom(int minutes, long resultIncome){
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
        TransportPlan transportPlan = transportPlanRepository.save(new TransportPlan(1L, 100000L, List.of(section1, section2, section3)));

        long id = transportPlan.getId();
        DelayDto delay = new DelayDto();
        delay.setMinutes(minutes);
        delay.setMilestoneId(milestone1.getId());
        call(id, delay);

        TransportPlan after = transportPlanRepository.findById(transportPlan.getId()).get();
        Milestone targetMilestone = milestoneRepository.findById(milestone1.getId()).get();
        Milestone nextMilestone = milestoneRepository.findById(milestone2.getId()).get();
        Milestone nextSectionFrom = milestoneRepository.findById(milestone3.getId()).get();

        assertThat(after.getIncome()).isEqualTo(resultIncome);
        assertThat(targetMilestone.getPlannedTime()).isEqualTo(milestone1.getPlannedTime().plusMinutes(minutes));
        assertThat(nextMilestone.getPlannedTime()).isEqualTo(milestone2.getPlannedTime().plusMinutes(minutes));
        assertThat(nextSectionFrom.getPlannedTime()).isEqualTo(milestone3.getPlannedTime());
    }

    private void delayTo(int minutes, long resultIncome){
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
        TransportPlan transportPlan = transportPlanRepository.save(new TransportPlan(1L, 100000L, List.of(section1, section2, section3)));

        long id = transportPlan.getId();
        DelayDto delay = new DelayDto();
        delay.setMinutes(minutes);
        delay.setMilestoneId(milestone4.getId());
        call(id, delay);

        TransportPlan after = transportPlanRepository.findById(transportPlan.getId()).get();
        Milestone sectionFrom = milestoneRepository.findById(milestone3.getId()).get();
        Milestone targetMilestone = milestoneRepository.findById(milestone4.getId()).get();
        Milestone nextSectionFrom = milestoneRepository.findById(milestone5.getId()).get();

        assertThat(after.getIncome()).isEqualTo(resultIncome);
        assertThat(sectionFrom.getPlannedTime()).isEqualTo(milestone3.getPlannedTime());
        assertThat(targetMilestone.getPlannedTime()).isEqualTo(milestone4.getPlannedTime().plusMinutes(minutes));
        assertThat(nextSectionFrom.getPlannedTime()).isEqualTo(milestone5.getPlannedTime().plusMinutes(minutes));
    }

    private WebTestClient.ResponseSpec call(Long id, DelayDto delay){
        return webTestClient.post()
                .uri(API_TRANSPORTPLANS+"/"+id+"/delay")
//                .headers(header -> header.setBearerAuth(jwt))
                .headers(header -> header.setBearerAuth(JWT))
                .bodyValue(delay)
                .exchange()
                .expectStatus().isOk();
    }


}
