package rs.ac.metropolitan.kladionicarest.dto;

import javax.persistence.Column;

public class BetOddDTO {

    private String betOddName;
    private String betOddInfo;

    public BetOddDTO(String betOddName, String betOddInfo) {
        this.betOddName = betOddName;
        this.betOddInfo = betOddInfo;
    }

    public BetOddDTO() {
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
}
