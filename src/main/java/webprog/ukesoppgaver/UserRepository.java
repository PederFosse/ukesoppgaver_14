package webprog.ukesoppgaver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(CarRepository.class); // logger

    // get user by primary key
    public boolean validateUser(User user) {
        String sql = "SELECT * FROM User WHERE username=?";

        String usr = user.getUsername();
        String pwd = user.getPassword();

        System.out.println(usr + "\t" + pwd);
        User foundUser;
        try {
            foundUser = (User) db.queryForObject(
                    sql,
                    new Object[]{usr},
                    new BeanPropertyRowMapper(User.class)
            );
        } catch (Exception e) {
            logger.error("error when searching for user: " + e);
            return false;
        }

        String foundUsr = foundUser.getUsername();
        String foundPwd = foundUser.getPassword();

        return usr.equals(foundUsr) && pwd.equals(foundPwd);
    }
}
