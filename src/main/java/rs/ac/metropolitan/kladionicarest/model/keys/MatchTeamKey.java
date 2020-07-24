package rs.ac.metropolitan.kladionicarest.model.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatchTeamKey implements Serializable {

    @Column(name = "matchId")
    private Long matchId;

    @Column(name = "teamId")
    private Long teamId;

    public MatchTeamKey() {
    }

    public MatchTeamKey(Long matchId, Long teamId) {
        this.matchId = matchId;
        this.teamId = teamId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchTeamKey that = (MatchTeamKey) o;
        return Objects.equals(matchId, that.matchId) &&
                Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, teamId);
    }

    @Override
    public String toString() {
        return "MatchTeamKey{" +
                "matchId=" + matchId +
                ", teamId=" + teamId +
                '}';
    }
}
