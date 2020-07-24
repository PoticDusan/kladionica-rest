package rs.ac.metropolitan.kladionicarest.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.metropolitan.kladionicarest.model.Role;
import rs.ac.metropolitan.kladionicarest.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleRESTController {
    @Autowired
    private RoleRepository roleRepository;

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleRepository.findAll();
        if (roles.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping(value = "{roleId}")
    public ResponseEntity<Role> getRole(@PathVariable("roleId") long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (!role.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(role.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addRole(@RequestBody Role role) {
        roleRepository.saveAndFlush(role);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable("roleId") long id, @RequestBody Role role) {
        role.setId(id);
        roleRepository.saveAndFlush(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping(value = "{roleId}")
    public ResponseEntity<Role> deleteRole(@PathVariable("roleId") long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (!role.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleRepository.delete(role.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
