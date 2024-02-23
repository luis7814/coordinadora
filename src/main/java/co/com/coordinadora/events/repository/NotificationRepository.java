package co.com.coordinadora.events.repository;

import co.com.coordinadora.events.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, String> {
}
