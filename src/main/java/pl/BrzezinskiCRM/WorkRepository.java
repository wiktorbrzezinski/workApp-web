package pl.BrzezinskiCRM;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WorkRepository extends JpaRepository<Work, Long> {

    Work findById(int id);

    List<Work> findByDate(String date);
}
