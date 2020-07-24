package rs.ac.metropolitan.kladionicarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.metropolitan.kladionicarest.model.MatchTeam;
import rs.ac.metropolitan.kladionicarest.model.keys.MatchTeamKey;

import java.util.List;

@Repository
public interface MatchTeamRepository extends JpaRepository<MatchTeam, MatchTeamKey> {

    List<MatchTeam> getAllByMatchId(@Param("matchId") Long matchId);

    List<MatchTeam> getAllByTeamId(@Param("teamId") Long teamId);

    void deleteMatchTeamsByMatchId(Long matchId);
}
