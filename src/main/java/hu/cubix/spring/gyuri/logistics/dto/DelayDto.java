package hu.cubix.spring.gyuri.logistics.dto;

public class DelayDto {

    private Long milestoneId;
    private int minutes;

    public DelayDto() {
    }

    public DelayDto(Long milestoneId, int minutes) {
        this.milestoneId = milestoneId;
        this.minutes = minutes;
    }

    public Long getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(Long milestoneId) {
        this.milestoneId = milestoneId;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
