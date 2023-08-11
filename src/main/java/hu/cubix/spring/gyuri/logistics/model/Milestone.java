package hu.cubix.spring.gyuri.logistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Milestone {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Address address;
    private LocalDateTime plannedTime;

    public Milestone() {
    }

    public Milestone(Long id, Address address, LocalDateTime plannedTime) {
        this.id = id;
        this.address = address;
        this.plannedTime = plannedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(LocalDateTime plannedTime) {
        this.plannedTime = plannedTime;
    }
}
