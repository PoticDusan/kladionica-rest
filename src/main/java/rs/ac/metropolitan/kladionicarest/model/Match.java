package rs.ac.metropolitan.kladionicarest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @Column(name = "matchId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId", nullable = false)
    @JsonIgnore
    private Event event;

    @Column(name = "matchDate")
    private Date matchDate;

    @Column(name = "matchTime")
    private Time matchTime;

    @Column(name = "matchStatus")
    private String matchStatus;

    @Column(name = "matchInfo")
    private String matchInfo;

    @JsonManagedReference
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MatchTeam> matchTeams = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MatchOdd> matchOdds = new ArrayList<>();

    public Match() {
    }

    public Match(Event event, Date matchDate, Time matchTime, String matchStatus, String matchInfo) {
        this.event = event;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.matchStatus = matchStatus;
        this.matchInfo = matchInfo;
    }

    public Match(Event event, Date matchDate, Time matchTime, String matchStatus, String matchInfo,
                 List<MatchTeam> matchTeams, List<MatchOdd> matchOdds) {
        this.event = event;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.matchStatus = matchStatus;
        this.matchInfo = matchInfo;
        this.matchTeams = matchTeams;
        this.matchOdds = matchOdds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public Time getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Time matchTime) {
        this.matchTime = matchTime;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(String matchInfo) {
        this.matchInfo = matchInfo;
    }

    public List<MatchTeam> getMatchTeams() {
        return matchTeams;
    }

    public void setMatchTeams(List<MatchTeam> matchTeams) {
        this.matchTeams = matchTeams;
    }

    public void addMatchTeam(Team team1, Team team2) {
        this.getMatchTeams().add(new MatchTeam(this, team1));
        this.getMatchTeams().add(new MatchTeam(this, team2));
    }

    public List<MatchOdd> getMatchOdds() {
        return matchOdds;
    }

    public void setMatchOdds(List<MatchOdd> matchOdds) {
        this.matchOdds = matchOdds;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", event=" + event +
                ", matchDate=" + matchDate +
                ", matchTime=" + matchTime +
                ", matchStatus='" + matchStatus + '\'' +
                ", matchInfo='" + matchInfo + '\'' +
                ", matchTeams=" + matchTeams +
                ", matchOdds=" + matchOdds +
                '}';
    }
}
