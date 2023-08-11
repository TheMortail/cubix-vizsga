package hu.cubix.spring.gyuri.logistics.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Section {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Milestone fromMilestone;
    @OneToOne
    private Milestone toMilestone;
    private int number;

    public Section() {
    }

    public Section(Long id, Milestone fromMilestone, Milestone toMilestone, int number) {
        this.id = id;
        this.fromMilestone = fromMilestone;
        this.toMilestone = toMilestone;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Milestone getFromMilestone() {
        return fromMilestone;
    }

    public void setFromMilestone(Milestone fromMilestone) {
        this.fromMilestone = fromMilestone;
    }

    public Milestone getToMilestone() {
        return toMilestone;
    }

    public void setToMilestone(Milestone toMilestone) {
        this.toMilestone = toMilestone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
