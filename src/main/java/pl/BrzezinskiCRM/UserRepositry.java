package pl.BrzezinskiCRM;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositry extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
