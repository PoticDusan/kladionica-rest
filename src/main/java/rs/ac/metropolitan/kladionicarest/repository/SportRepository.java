package rs.ac.metropolitan.kladionicarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.metropolitan.kladionicarest.model.Sport;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
}
