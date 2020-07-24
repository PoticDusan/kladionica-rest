package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.config.Path;
import rs.ac.metropolitan.kladionicarest.dto.UserDTO;
import rs.ac.metropolitan.kladionicarest.model.security.PasswordChangeModel;
import rs.ac.metropolitan.kladionicarest.model.User;
import rs.ac.metropolitan.kladionicarest.service.UserService;

import java.util.List;

@RestController
@RequestMapping(Path.API)
public class UserRESTController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(Path.USERS)
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping(Path.USER_ID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PutMapping(Path.USER_ID)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @PostMapping(Path.USER_PASSWORD)
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeModel passwordChangeModel) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(userService.changePassword(user, passwordChangeModel));
    }

}