// add a new car to the register
function addCar(event) {
    // prevent default form behaviour
    event.preventDefault();

    // get form
    const form = $('#car-form')[0];

    // create car object
    const car = createCarObject();

    // validate form and return if not valid
    if (!validateForm(car)) { return; }

    // POST car to the server
    $.post("/cars", car, () => {
        alert(`Bil med kjennetegn: ${car.plate_number} ble lagt til!`); // confirm to user that car was added

        // reset form
        form.reset();
    })
}

