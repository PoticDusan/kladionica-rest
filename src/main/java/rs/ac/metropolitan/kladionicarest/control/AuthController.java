package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.config.JwtTokenUtil;
import rs.ac.metropolitan.kladionicarest.config.Path;
import rs.ac.metropolitan.kladionicarest.model.security.TokenResponse;
import rs.ac.metropolitan.kladionicarest.model.security.UserRequest;
import rs.ac.metropolitan.kladionicarest.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(Path.API)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @PostMapping(Path.AUTH)
    public ResponseEntity<?> auth(@RequestBody UserRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping(Path.REGISTER)
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) throws Exception {
        return ResponseEntity.ok(userService.insertUser(userRequest));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(Path.LOGOUT)
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public ResponseEntity<Void> logoutUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);
        //potrebno je kreirati blacklist
        return new ResponseEntity<>(HttpStatus.OK);
    }
}