package torres.kathya.Jump2Digital.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import torres.kathya.Jump2Digital.security.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
