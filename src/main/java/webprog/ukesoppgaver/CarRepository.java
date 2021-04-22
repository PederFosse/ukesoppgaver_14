package webprog.ukesoppgaver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(CarRepository.class); // logger

    public boolean addCar(Car car) {
        String sql = "INSERT INTO Car (pnr, name, address, plate_number, brand, type) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            db.update(
                    sql,
                    car.getPnr(),
                    car.getName(),
                    car.getAddress(),
                    car.getPlate_number(),
                    car.getBrand(),
                    car.getType()
            );
            return true; // success!
        } catch (Exception e) {
            logger.error("An error occured while trying to add a new car to database: " + e);
            return false; // An error happened!
        }
    }

    public List<Car> getCars() {
        String sql = "SELECT * FROM Car";
        List<Car> cars = db.query(sql, new BeanPropertyRowMapper(Car.class));
        return cars;
    }

    public boolean deleteByPk(int id) {
        String sql = "DELETE FROM Car WHERE id = (?)";

        try {
            db.update(sql, id);
            return true;
        } catch (Exception e) {
            logger.error("An error occured while trying to delete car with id=" + id + ": " + e);
            return false;
        }
    }

    public boolean deleteAll() {
        String sql = "DELETE FROM Car";
        try {
            db.update(sql);
            return true; // success!
        } catch (Exception e) {
            logger.error("An error occured while trying to delete all cars: " + e);
            return false; // an error occurred.
        }
    }

    public boolean editCar(Car car, int id) {
        String sql = "UPDATE Car SET pnr = (?), name = (?), address = (?), plate_number = (?), brand = (?), type = (?) WHERE id = (?)";

        try {
            db.update(
                    sql,
                    car.getPnr(),
                    car.getName(),
                    car.getAddress(),
                    car.getPlate_number(),
                    car.getBrand(),
                    car.getType(),
                    id
            );
            return true; // success!
        } catch (Exception e) {
            logger.error("An error uccured while trying to edit car with id = " + id + ": " + e);
            return false; // an error occurred.
        }
    }

    public Car getByPk(int id) {
        String sql = "SELECT * FROM Car WHERE id = (?)";

            Car foundCar = (Car) db.queryForObject(
                    sql,
                    new Object[]{id},
                    new BeanPropertyRowMapper(Car.class)
            );
            return foundCar;
    }
}
