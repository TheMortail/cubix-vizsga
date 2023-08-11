package hu.cubix.spring.gyuri.logistics.repository;

import hu.cubix.spring.gyuri.logistics.model.TransportPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long> {
}
