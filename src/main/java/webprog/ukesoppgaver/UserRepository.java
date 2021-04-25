package webprog.ukesoppgaver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(CarRepository.class); // logger

    private String hash(String password) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(14);
        String hashedPw = bcrypt.encode(password);
        return hashedPw;
    }

    private boolean checkPassword(String password, String hash) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(14);
        return bcrypt.matches(password, hash);
    }

    // get user by primary key
    public boolean validateUser(User user) {
        String sql = "SELECT * FROM User WHERE username=?";

        String username = user.getUsername();
        String password = user.getPassword();

        User foundUser;
        try {
            foundUser = (User) db.queryForObject(
                    sql,
                    new Object[]{username},
                    new BeanPropertyRowMapper(User.class)
            );
        } catch (Exception e) {
            logger.error("HERE! error when searching for user: " + e);
            return false;
        }

        String foundPassword = foundUser.getPassword();

        return checkPassword(password, foundPassword);
    }

    public boolean createUser(User user) {
        String sql = "INSERT INTO USER (username, password) VALUES (?,?)";

        String username = user.getUsername();
        String password = user.getPassword();
        String hashedPw = hash(password);

        try {
            db.update(sql, username, hashedPw);
            return true;
        } catch (Exception e) {
            logger.error("could not create user: " + e);
            return false;
        }
    }
}
