package pl.pwr.news.webapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.news.model.user.User;
import pl.pwr.news.model.user.UserRole;
import pl.pwr.news.service.message.MessageService;
import pl.pwr.news.service.user.UserService;
import pl.pwr.news.webapp.controller.user.form.RegisterRequestBody;

/**
 * Created by Rafal on 2016-02-28.
 */

@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "/register/", method = RequestMethod.POST,
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequestBody registerRequestBody) {

        if (!registerRequestBody.getPassword().equals(registerRequestBody.getConfirmPassword())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (userService.findByEmail(registerRequestBody.getMail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (userService.findByUsername(registerRequestBody.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User user = userService.createUserFromForm(registerRequestBody);
        userService.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping("/rafal")
        public void saveToKurwa(){
        User user = new User();
        user.setRole(UserRole.ADMIN);
        user.setEmail("user1@rafal.com");
        user.setUsername("user1");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("password"));
        userService.save(user);
    }

}
