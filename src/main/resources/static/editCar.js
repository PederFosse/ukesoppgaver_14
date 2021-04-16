$(function () {
    renderCarInfo(); // retrieve the car we want to update
})

function editCar(event) {
    // prevent default form behaviour
    event.preventDefault();

    // get form
    const form = $('#car-form')[0];

    // get car object from form
    const car = createCarObject();

    // validate form and return if not valid
    if (!validateForm(car)) { return; }

    // get id for the car to be updated
    const carId = getCarId();

    // update car in database
    $.post(`/cars/${carId}/edit`, car, () => {
        // let user know car was updated
        alert(`Bil med kjennetegn: ${car.plate_number} ble oppdatert!`);

        // reset form and fetch new car info
        renderCarInfo();
    });
}

function renderCarInfo() {
    // retrieve current car from database
    const id = getCarId();
    $.get(`/cars/${id}`, car => {
        // update values in the form for the user
        $('#pnr').val(car.pnr);
        $('#name').val(car.name);
        $('#address').val(car.address);
        $('#plate_number').val(car.plate_number);
        $('#brand').val(car.brand);
        $('#type').val(car.type);
    });
}


function getCarId() {
    const queryString = window.location.search; // get params from the url
    const urlParams = new URLSearchParams(queryString); // make searchparams object
    const carId = urlParams.get("carId"); // retrieve the carId from the params
    return carId;
}