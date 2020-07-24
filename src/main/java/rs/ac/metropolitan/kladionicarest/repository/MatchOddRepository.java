package rs.ac.metropolitan.kladionicarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.metropolitan.kladionicarest.model.MatchOdd;
import rs.ac.metropolitan.kladionicarest.model.keys.MatchOddKey;

import java.util.List;

@Repository
public interface MatchOddRepository extends JpaRepository<MatchOdd, MatchOddKey> {

    List<MatchOdd> getAllByMatchId(@Param("matchId") Long matchId);

    void deleteMatchOddsByMatchId(Long matchId);
}
