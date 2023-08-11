package hu.cubix.spring.gyuri.logistics.repository;

import hu.cubix.spring.gyuri.logistics.model.TPUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TPUserRepository extends JpaRepository<TPUser, String> {
}
