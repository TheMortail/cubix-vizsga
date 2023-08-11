package hu.cubix.spring.gyuri.logistics.repository;

import hu.cubix.spring.gyuri.logistics.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {
}
