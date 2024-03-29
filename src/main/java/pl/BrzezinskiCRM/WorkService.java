package pl.BrzezinskiCRM;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class WorkService {

    @Autowired
    WorkRepository workRepository;

    @Autowired
    UserRepositry userRepositry;

    @Autowired
    ObjectMapper objectMapper;

    Work savedWork;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String date = simpleDateFormat.format(new Date());

    @PostMapping("/startWork")
    public ResponseEntity startWork(@RequestHeader("user_id") Long user_id, @RequestBody String location) {

        Optional<User> userFromDb = userRepositry.findById(user_id);

        if(!userFromDb.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Work work = new Work(userFromDb.get(), true, 0, location, date);
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

    @Transactional
    @GetMapping("/getWork")
    public ResponseEntity getWork() {

        List<Work> workFromDb =  workRepository.findByDate(date);

        return ResponseEntity.ok(workFromDb);
    }
}
