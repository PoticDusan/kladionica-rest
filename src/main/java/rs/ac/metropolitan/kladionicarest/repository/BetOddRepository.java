package rs.ac.metropolitan.kladionicarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.metropolitan.kladionicarest.model.BetOdd;

import java.util.List;

@Repository
public interface BetOddRepository extends JpaRepository<BetOdd, Long> {

    List<BetOdd> getBetOddsById(long id);
}
