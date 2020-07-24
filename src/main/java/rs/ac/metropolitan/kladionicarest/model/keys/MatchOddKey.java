package rs.ac.metropolitan.kladionicarest.model.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatchOddKey implements Serializable {

    @Column(name = "matchId")
    private Long matchId;

    @Column(name = "betOddId")
    private Long betOddId;

    public MatchOddKey() {
    }

    public MatchOddKey(Long matchId, Long betOddId) {
        this.matchId = matchId;
        this.betOddId = betOddId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getBetOddId() {
        return betOddId;
    }

    public void setBetOddId(Long betOddId) {
        this.betOddId = betOddId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchOddKey that = (MatchOddKey) o;
        return Objects.equals(matchId, that.matchId) &&
                Objects.equals(betOddId, that.betOddId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, betOddId);
    }

    @Override
    public String toString() {
        return "MatchOddKey{" +
                "matchId=" + matchId +
                ", betOddId=" + betOddId +
                '}';
    }
}
