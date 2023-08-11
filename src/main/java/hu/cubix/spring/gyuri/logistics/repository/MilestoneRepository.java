package hu.cubix.spring.gyuri.logistics.repository;

import hu.cubix.spring.gyuri.logistics.model.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
