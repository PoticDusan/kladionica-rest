package rs.ac.metropolitan.kladionicarest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import rs.ac.metropolitan.kladionicarest.model.keys.MatchOddKey;

import javax.persistence.*;

@Entity
public class MatchOdd {

    @EmbeddedId
    private MatchOddKey id = new MatchOddKey();

    @JsonBackReference
    @ManyToOne
    @MapsId("matchId")
    @JoinColumn(name = "matchId")
    private Match match;

    @JsonBackReference
    @ManyToOne
    @MapsId("betOddId")
    @JoinColumn(name = "betOddId")
    private BetOdd betOdd;

    @Column(name = "matchOddValue")
    private double matchOddValue;

    @Column(name = "matchOddEnabled")
    private boolean matchOddEnabled;

    public MatchOdd() {
    }

    public MatchOdd(MatchOddKey id, double matchOddValue, boolean matchOddEnabled) {
        this.id = id;
        this.matchOddValue = matchOddValue;
        this.matchOddEnabled = matchOddEnabled;
    }

    public MatchOdd(Match match, BetOdd betOdd) {
        this.match = match;
        this.betOdd = betOdd;
        this.id.setMatchId(match.getId());
        this.id.setBetOddId(betOdd.getId());
    }

    public MatchOdd(Match match, BetOdd betOdd, double matchOddValue, boolean matchOddEnabled) {
        this.match = match;
        this.betOdd = betOdd;
        this.id.setMatchId(match.getId());
        this.id.setBetOddId(betOdd.getId());
        this.matchOddValue = matchOddValue;
        this.matchOddEnabled = matchOddEnabled;
    }

    public MatchOddKey getId() {
        return id;
    }

    public void setId(MatchOddKey id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public BetOdd getBetOdd() {
        return betOdd;
    }

    public void setBetOdd(BetOdd betOdd) {
        this.betOdd = betOdd;
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
        return "MatchOdd{" +
                ", match=" + match.getId() +
                ", betOdd=" + betOdd.getId() +
                ", matchOddValue=" + matchOddValue +
                ", matchOddEnabled=" + matchOddEnabled +
                '}';
    }
}
