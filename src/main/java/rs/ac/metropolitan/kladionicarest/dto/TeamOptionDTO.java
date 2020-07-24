package rs.ac.metropolitan.kladionicarest.dto;

import java.io.Serializable;

public class TeamOptionDTO implements Serializable {
    private Long teamId;
    private int teamScore;
    private int teamAtHome;

    public TeamOptionDTO(Long teamId, int teamScore, int teamAtHome) {
        this.teamId = teamId;
        this.teamScore = teamScore;
        this.teamAtHome = teamAtHome;
    }

    public TeamOptionDTO() {
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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
}
