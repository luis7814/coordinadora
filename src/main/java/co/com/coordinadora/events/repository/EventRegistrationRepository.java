package co.com.coordinadora.events.repository;

import co.com.coordinadora.events.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, String> {
}
