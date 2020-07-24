package rs.ac.metropolitan.kladionicarest.dto;

import javax.persistence.Column;

public class WalletDTO {
    private double walletBalance;
    private String walletStatus;

    public WalletDTO(double walletBalance, String walletStatus) {
        this.walletBalance = walletBalance;
        this.walletStatus = walletStatus;
    }

    public WalletDTO() {
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getWalletStatus() {
        return walletStatus;
    }

    public void setWalletStatus(String walletStatus) {
        this.walletStatus = walletStatus;
    }
}
