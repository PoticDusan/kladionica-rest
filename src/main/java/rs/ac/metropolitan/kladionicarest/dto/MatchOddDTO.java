package rs.ac.metropolitan.kladionicarest.dto;

import java.io.Serializable;

public class MatchOddDTO implements Serializable {

    private Long matchId;
    private Long betOddId;
    private double matchOddValue;
    private boolean matchOddEnabled;

    public MatchOddDTO() {
    }

    public MatchOddDTO(Long matchId, Long betOddId, double matchOddValue, boolean matchOddEnabled) {
        this.matchId = matchId;
        this.betOddId = betOddId;
        this.matchOddValue = matchOddValue;
        this.matchOddEnabled = matchOddEnabled;
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

    public double getMatchOddValue() {
        return matchOddValue;
    }

    public void setMatchOddValue(double matchOddValue) {
        this.matchOddValue = matchOddValue;
    }

    public boolean isMatchOddEnabled() {
        return matchOddEnabled;
    }

    public void setMatchOddEnabled(boolean matchOddEnabled) {
        this.matchOddEnabled = matchOddEnabled;
    }

    @Override
    public String toString() {
        return "MatchOddDTO{" +
                "matchId=" + matchId +
                ", betOddId=" + betOddId +
                ", matchOddValue=" + matchOddValue +
                ", matchOddEnabled=" + matchOddEnabled +
                '}';
    }
}
