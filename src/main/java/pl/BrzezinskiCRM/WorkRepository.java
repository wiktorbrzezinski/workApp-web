package pl.BrzezinskiCRM;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {

    Work findById(int id);
}
