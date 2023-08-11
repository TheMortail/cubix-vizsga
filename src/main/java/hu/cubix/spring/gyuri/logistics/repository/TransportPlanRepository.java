package hu.cubix.spring.gyuri.logistics.repository;

import hu.cubix.spring.gyuri.logistics.model.TransportPlan;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long> {
}
