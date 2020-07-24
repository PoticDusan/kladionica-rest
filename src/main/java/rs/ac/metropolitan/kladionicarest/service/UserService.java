package rs.ac.metropolitan.kladionicarest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.metropolitan.kladionicarest.dto.UserDTO;
import rs.ac.metropolitan.kladionicarest.model.Role;
import rs.ac.metropolitan.kladionicarest.model.User;
import rs.ac.metropolitan.kladionicarest.model.security.PasswordChangeModel;
import rs.ac.metropolitan.kladionicarest.model.security.UserRequest;
import rs.ac.metropolitan.kladionicarest.repository.RoleRepository;
import rs.ac.metropolitan.kladionicarest.repository.UserRepository;
import rs.ac.metropolitan.kladionicarest.security.LoginUserDetails;
import rs.ac.metropolitan.kladionicarest.util.ERole;

import java.util.List;
import java.util.Optional;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public User getUserByUsername(String username) {
        Optional<User> u = userRepository.findByUsername(username);
        if (!u.isPresent()) return null;
        return u.get();
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        Optional<User> u = userRepository.findByUsername(s);
        if (!u.isPresent()) throw new UsernameNotFoundException("Could not find user with username " + s);
        return new LoginUserDetails(u.get());
    }

    public User insertUser(UserRequest userRequest) {
        Optional<User> ou = userRepository.findByUsername(userRequest.getUsername());
        if (ou.isPresent()) return null;
        User u = new User();
        u.setUsername(userRequest.getUsername());
        u.setEmail(userRequest.getEmail());
        u.setPassword(bcryptEncoder.encode(userRequest.getPassword()));
        Role role = roleRepository.findRoleByRoleName(ERole.USER);
        u.setRole(role);
        return userRepository.saveAndFlush(u);
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User u = getUser(id);
        if (u == null) return null;
        if (userDTO.getUsername() != null) u.setUsername(userDTO.getUsername());
        Role role = roleRepository.findRoleByRoleName(userDTO.getRole());
        if (role != null) u.setRole(role);
        return userRepository.saveAndFlush(u);
    }

    public User changePassword(User user, PasswordChangeModel passwordChangeModel) {
        if (!bcryptEncoder.matches(passwordChangeModel.getOldPassword(), user.getPassword())) return null;
        user.setPassword(bcryptEncoder.encode(passwordChangeModel.getNewPassword()));
        return userRepository.save(user);
    }
}

