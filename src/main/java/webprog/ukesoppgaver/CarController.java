package webprog.ukesoppgaver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarRepository rep;

    @Autowired
    private HttpSession session;

    Logger logger = LoggerFactory.getLogger(CarController.class);

    private boolean validateCar(Car car) {
        String regexPnr = "[0-9]{11}";
        String regexName = "[a-zA-ZæøåÆØÅ. \\-]{2,30}";
        String regexAddress = "[0-9a-zA-ZæøåÆØÅ. \\-]{2,50}";
        String regexPlateNumber = "[A-Z]{2}[0-9]{5}";
        String regexBrand = "[0-9a-zA-ZæøåÆØÅ. \\-]{2,30}";
        String regexType = "[0-9a-zA-ZæøåÆØÅ. \\-]{2,30}";

        boolean pnrOk = car.getPnr().matches(regexPnr);
        boolean nameOk = car.getName().matches(regexName);
        boolean addressOk = car.getAddress().matches(regexAddress);
        boolean plateNumberOk = car.getPlate_number().matches(regexPlateNumber);
        boolean brandOk = car.getBrand().matches(regexBrand);
        boolean typeOk = car.getType().matches(regexType);

        if (pnrOk && nameOk && addressOk && plateNumberOk && brandOk && typeOk) {
            return true;
        } else {
            logger.error("validate car failed");
            return false;
        }
    }

    // Create a new car
    @PostMapping("/cars")
    public void addCar(Car car, HttpServletResponse res) throws IOException {
        if (session.getAttribute("LoggedIn") != null) { // check if user is logged in
            if (!validateCar(car)) {
                res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Could not validate car.");
            } else {
                if (!rep.addCar(car)) {
                    res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Could not add car to database, try again later.");
                }
            }
        } else {
            // user is not logged in
            res.sendError(HttpStatus.FORBIDDEN.value(), "not allowed to add a new car");
        }

    }

    // Get car by id
    @GetMapping("/cars/{id}")
    public Car getByPk(@PathVariable("id") int id) {
        return rep.getByPk(id);
    }

    // Get all cars
    @GetMapping("/cars")
    public List<Car> getCars() {
        return rep.getCars();
    }

    // Delete all cars
    @PostMapping("/cars/delete")
    public void deleteAll(HttpServletResponse res) throws IOException {
        if (session.getAttribute("LoggedIn") != null) { // check if user is logged in
            if (!rep.deleteAll()) {
                res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Could not delete all cars. Try again later.");
            }
        } else {
            // user is not logged in
            res.sendError(HttpStatus.FORBIDDEN.value(), "not allowed to delete all cars");
        }
    }

    // Delete car by id
    @PostMapping("/cars/{id}/delete")
    public void deleteByPk(HttpServletResponse res, @PathVariable("id") int id) throws IOException {
        if (session.getAttribute("LoggedIn") != null) { // check if user is logged in
            if (!rep.deleteByPk(id)) {
                res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Could not delete car with id: " + id + ". Try again later");
            }
        } else {
            // user is not logged in
            res.sendError(HttpStatus.FORBIDDEN.value(), "not allowed to delete car");
        }
    }

    // Edit car by id
    @PostMapping("/cars/{id}/edit")
    public void editCar(Car car, @PathVariable("id") int id, HttpServletResponse res) throws IOException {
        if (session.getAttribute("LoggedIn") != null) { // check if user is logged in
            if (validateCar(car)) {
                res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Could not validate car.");
            } else {
                if (!rep.editCar(car, id)) {
                    res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Could not update car with id: " + id + ". Try again later");
                }
            }
        } else {
            // user is not logged in
            res.sendError(HttpStatus.FORBIDDEN.value(), "not allowed to edit car");
        }
    }
}
