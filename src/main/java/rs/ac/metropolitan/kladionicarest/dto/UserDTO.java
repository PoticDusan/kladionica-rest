package rs.ac.metropolitan.kladionicarest.dto;

import rs.ac.metropolitan.kladionicarest.util.ERole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserDTO {
    private String username;

    @Enumerated(EnumType.STRING)
    private ERole role;

    public UserDTO(String username, ERole role) {
        this.username = username;
        this.role = role;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
}
