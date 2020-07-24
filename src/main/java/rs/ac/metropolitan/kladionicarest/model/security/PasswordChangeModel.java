package rs.ac.metropolitan.kladionicarest.model.security;

public class PasswordChangeModel {

    private String oldPassword;
    private String newPassword;

    public PasswordChangeModel() {
    }

    public PasswordChangeModel(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
