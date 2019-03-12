package ee.rakendus.example.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository repository;
/*
    @GetMapping("/login")
    public User getUserByEmail() {
        User user= new User();
        repository.findByEmail();

        return user;
    }
*/
    @PostMapping("/register")
    public User postUser(@RequestBody User user) {
        User _user = repository.save(new User(user.getUsername(), user.getEmail(), user.getPassword()));
        return _user;
    }


}
