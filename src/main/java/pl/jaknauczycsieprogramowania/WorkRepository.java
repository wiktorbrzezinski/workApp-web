package pl.jaknauczycsieprogramowania;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work, Long> {

    Work findById(int id);
}
