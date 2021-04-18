package pl.BrzezinskiCRM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class WorkService {

    @Autowired
    WorkRepository workRepository;

    @Autowired
    UserRepositry userRepositry;

    Work savedWork;

    @PostMapping("/startWork")
    public ResponseEntity startWork(@RequestHeader("user_id") Long user_id) {

        Optional<User> userFromDb = userRepositry.findById(user_id);

        if(!userFromDb.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Work work = new Work(userFromDb.get(), true, 0, "0");
        savedWork = workRepository.save(work);

        return ResponseEntity.ok(savedWork);
    }

    @Transactional
    @PutMapping("/endWork")
    public ResponseEntity endWork(@RequestHeader("work_status") boolean work_status){

        Work workFromDb = workRepository.findById(savedWork.getId());

        workFromDb.setWork_status(work_status);

        return ResponseEntity.ok(workFromDb);
    }

    @Transactional
    @PutMapping("/time")
    public ResponseEntity updateTime(@RequestHeader("time") int time){

       Work workFromDb = workRepository.findById(savedWork.getId());

       workFromDb.setTime(time);

        return ResponseEntity.ok(workFromDb);
    }
}
