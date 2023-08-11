package hu.cubix.spring.gyuri.logistics.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class TransportPlan {
    @Id
    @GeneratedValue
    private Long id;
    private Long income;
    @OneToMany
    private List<Section> sections;

    public TransportPlan() {
    }
    public TransportPlan(Long id, Long income, List<Section> sections) {
        this.id = id;
        this.income = income;
        this.sections = sections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
