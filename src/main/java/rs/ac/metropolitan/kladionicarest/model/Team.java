package rs.ac.metropolitan.kladionicarest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team implements Serializable {

    @Id
    @Column(name = "teamId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teamName")
    private String teamName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sportId", nullable = false)
    @JsonIgnore
    private Sport sport;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<MatchTeam> matchTeams;

    public Team() {
    }

    public Team(String teamName, Sport sport) {
        this.id = id;
        this.teamName = teamName;
        this.sport = sport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }


    public Set<MatchTeam> getMatchTeams() {
        return matchTeams;
    }

    public void setMatchTeams(Set<MatchTeam> matchTeams) {
        this.matchTeams = matchTeams;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", sport=" + sport.getId() +
                '}';
    }
}
