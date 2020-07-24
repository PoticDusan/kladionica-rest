package rs.ac.metropolitan.kladionicarest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


public class MatchDTO {

    private Long id;
    private Long eventId;
    private java.sql.Date matchDate;
    private java.sql.Time matchTime;
    private String matchStatus;
    private String matchInfo;

    @JsonProperty("teams")
    private List<TeamOptionDTO> teamOptionDTOS = null;

    @JsonProperty("betOdds")
    private List<BetOptionDTO> betOptionDTOS = null;

    public MatchDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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

    public List<BetOptionDTO> getBetOptionDTOS() {
        return betOptionDTOS;
    }

    public void setBetOptionDTOS(List<BetOptionDTO> betOptionDTOS) {
        this.betOptionDTOS = betOptionDTOS;
    }

    public List<TeamOptionDTO> getTeamOptionDTOS() {
        return teamOptionDTOS;
    }

    public void setTeamOptionDTOS(List<TeamOptionDTO> teamoptionDTOS) {
        this.teamOptionDTOS = teamoptionDTOS;
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", matchDate=" + matchDate +
                ", matchTime=" + matchTime +
                ", matchStatus='" + matchStatus + '\'' +
                ", matchInfo='" + matchInfo + '\'' +
                '}';
    }
}
