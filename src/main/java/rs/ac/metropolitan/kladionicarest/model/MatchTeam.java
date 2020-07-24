package rs.ac.metropolitan.kladionicarest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import rs.ac.metropolitan.kladionicarest.model.keys.MatchTeamKey;

import javax.persistence.*;

@Entity
public class MatchTeam {

    @EmbeddedId
    private MatchTeamKey id = new MatchTeamKey();

    @JsonBackReference
    @ManyToOne
    @MapsId("matchId")
    @JoinColumn(name = "matchId")
    private Match match;

    @JsonBackReference
    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "teamId")
    private Team team;

    @Column(name = "teamScore")
    private int teamScore;

    @Column(name = "teamAtHome")
    private int teamAtHome;

    public MatchTeam() {
    }

    public MatchTeam(Match match, Team team) {
        this.match = match;
        this.team = team;
        this.id.setMatchId(match.getId());
        this.id.setTeamId(team.getId());
    }

    public MatchTeam(Match match, Team team, int teamScore, int teamAtHome) {
        this.match = match;
        this.team = team;
        this.teamScore = teamScore;
        this.teamAtHome = teamAtHome;
    }

    public MatchTeamKey getId() {
        return id;
    }

    public void setId(MatchTeamKey id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public int getTeamAtHome() {
        return teamAtHome;
    }

    public void setTeamAtHome(int teamAtHome) {
        this.teamAtHome = teamAtHome;
    }

    @Override
    public String toString() {
        return "MatchTeam{" +
                ", match=" + match.getId() +
                ", team=" + team.getId() +
                ", teamScore=" + teamScore +
                ", teamAtHome=" + teamAtHome +
                '}';
    }
}
