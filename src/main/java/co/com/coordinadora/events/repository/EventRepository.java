package co.com.coordinadora.events.repository;

import co.com.coordinadora.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
