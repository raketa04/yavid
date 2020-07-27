package by.yavid.Controllers.Administrate;


import by.yavid.Entity.Workbase.Administrate.User;
import by.yavid.Service.Administrate.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRESTController {

    private UserService userService;

    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("user")
    @JsonView(User.getUser.class)
    @ResponseBody
    public ResponseEntity<User> getUserByUserName(@RequestParam String username) {
        User user = userService.getUserByUserName(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("user/all")
    @JsonView(User.getUser.class)
    public ResponseEntity<List<User>> getAllCity() {
        List<User> list = userService.getAllUser();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
