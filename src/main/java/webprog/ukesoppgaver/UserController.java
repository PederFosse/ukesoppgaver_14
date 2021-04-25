package webprog.ukesoppgaver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserRepository rep;

    @Autowired
    private HttpSession session;

    Logger logger = LoggerFactory.getLogger(CarController.class);

    private boolean validateUser(User user) {
        String regexUsername = "[a-zA-ZæøåÆØÅ. \\-]{2,30}";
        String regexPassword = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}";

        boolean usernameOk = user.getUsername().matches(regexUsername);
        boolean passwordOk = user.getPassword().matches(regexPassword);

        if (usernameOk && passwordOk) {
            return true;
        } else {
            logger.error("uservalidation failed");
            return false;
        }
    }

    @GetMapping("/users/isLoggedIn")
    public boolean isLoggedIn() {
        if (session.getAttribute("LoggedIn") != null) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("users/new")
    public void createUser(User user, HttpServletResponse res) throws IOException {
        if (!validateUser(user)) {
            res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "invalid username or password");
        } else {
            if (!rep.createUser(user)) {
                res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "could not create user, try again later");
            }
        }
    }

    @PostMapping("users/login")
    public void login(User user, HttpServletResponse res) throws IOException {
        if (rep.validateUser(user)) {
            session.setAttribute("LoggedIn", user);
        } else {
            res.sendError(HttpStatus.FORBIDDEN.value(), "Invalid username or password");
        }
    }

    @PostMapping("users/logout")
    public void logout(User user) {
        session.removeAttribute("LoggedIn");
    }
}
