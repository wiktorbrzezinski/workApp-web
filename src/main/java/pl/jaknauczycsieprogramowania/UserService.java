package pl.jaknauczycsieprogramowania;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserService {

    @Autowired
    UserRepositry userRepositry;

    @Autowired
    ObjectMapper objectMapper;

    Optional<User> userFromDb;

        @CrossOrigin(origins = "http://localhost:3000")
        @PostMapping("/login")
        public ResponseEntity login (@RequestBody User user){
            userFromDb = userRepositry.findByUsername(user.getUsername());

            if (!userFromDb.isPresent() || wrongPassword(userFromDb, user)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok().build();
        }

        private boolean wrongPassword (Optional < User > userFromDb, User user){
            return !userFromDb.get().getPassword().equals(user.getPassword());
        }

        @CrossOrigin(origins = "http://localhost:3000")
        @GetMapping("/currentUser")
        public ResponseEntity getRole() throws JsonProcessingException {

            if(userFromDb.isPresent()) {
                String role = userFromDb.get().getRole();
                long id = userFromDb.get().getId();
                Map<String, String> user_info = new HashMap<>();
                user_info.put("roles", role);
                user_info.put("id", Long.toString(id));
                return ResponseEntity.ok(objectMapper.writeValueAsString(user_info));
            }else{
                return ResponseEntity.ok("Nieznany błąd");
            }
        }

}
