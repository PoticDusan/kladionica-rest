package rs.ac.metropolitan.kladionicarest.dto;

import java.io.Serializable;

public class MatchTeamDTO implements Serializable {

    private Long matchId;
    private Long teamId;
    private int teamScore;

    public MatchTeamDTO() {
    }

    public MatchTeamDTO(Long matchId, Long teamId, int teamScore) {
        this.matchId = matchId;
        this.teamId = teamId;
        this.teamScore = teamScore;
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

    public int getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    @Override
    public String toString() {
        return "MatchTeamDTO{" +
                "matchId=" + matchId +
                ", teamId=" + teamId +
                ", teamScore=" + teamScore +
                '}';
    }
}
