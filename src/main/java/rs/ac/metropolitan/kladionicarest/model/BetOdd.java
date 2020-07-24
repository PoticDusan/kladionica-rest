package rs.ac.metropolitan.kladionicarest.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "betOdd")
public class BetOdd {

    @Id
    @Column(name = "betOddId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "betOddName")
    private String betOddName;

    @Column(name = "betOddInfo")
    private String betOddInfo;

    @OneToMany(mappedBy = "betOdd", cascade = CascadeType.ALL)
    private Set<MatchOdd> matchOdds;

    public BetOdd() {
    }

    public BetOdd(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBetOddName() {
        return betOddName;
    }

    public void setBetOddName(String betOddName) {
        this.betOddName = betOddName;
    }

    public String getBetOddInfo() {
        return betOddInfo;
    }

    public void setBetOddInfo(String betOddInfo) {
        this.betOddInfo = betOddInfo;
    }

    public Set<MatchOdd> getMatchOdds() {
        return matchOdds;
    }

    public void setMatchOdds(Set<MatchOdd> matchOdds) {
        this.matchOdds = matchOdds;
    }

    @Override
    public String toString() {
        return "BetOdd{" +
                "id=" + id +
                ", betOddName='" + betOddName + '\'' +
                ", betOddInfo='" + betOddInfo + '\'' +
                '}';
    }
}
