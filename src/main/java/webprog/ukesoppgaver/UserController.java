package webprog.ukesoppgaver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserRepository rep;

    @Autowired
    private HttpSession session;

    Logger logger = LoggerFactory.getLogger(CarController.class);

    @PostMapping("/login")
    public boolean login(User user) {
        if (rep.validateUser(user)) {
            session.setAttribute("LoggedIn", user);
            return true;
        } else {
            return false;
        }
    }
}
