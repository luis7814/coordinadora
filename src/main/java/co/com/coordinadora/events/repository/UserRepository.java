package co.com.coordinadora.events.repository;

import co.com.coordinadora.events.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
