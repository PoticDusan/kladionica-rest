package rs.ac.metropolitan.kladionicarest.dto;

import java.io.Serializable;

public class BetOptionDTO implements Serializable {

    private Long betOddId;
    private double matchOddValue;
    private boolean matchOddEnabled;

    public BetOptionDTO() {
    }

    public BetOptionDTO(Long betOddId, double matchOddValue, boolean matchOddEnabled) {
        this.betOddId = betOddId;
        this.matchOddValue = matchOddValue;
        this.matchOddEnabled = matchOddEnabled;
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
        return "BetOptionDTO{" +
                "betOddId=" + betOddId +
                ", matchOddValue=" + matchOddValue +
                ", matchOddEnabled=" + matchOddEnabled +
                '}';
    }
}
