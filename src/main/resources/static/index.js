$(function () {
    getCars(); // retrieve and update cars table when page has loaded
})

// retrieve cars from the database and print them to the webpage
function getCars() {
    $.get("/cars", (data) => {
        const formattedCars = formatCars(data); // format data
        $('#car-tbody').html(formattedCars); // print formatted cars to the car table
    })
}

// Format all cars
function formatCars(cars) {
    let formatted_cars = '';
    for (let car of cars) {
        formatted_cars += `
            <tr>
                <td>${car.pnr}</td>
                <td>${car.name}</td>
                <td>${car.address}</td>
                <td>${car.plate_number}</td>
                <td>${car.brand}</td>
                <td>${car.type}</td>
                <td>
                    <button class="btn btn-primary pe-2" onclick="redirectToEditCar(${car.id})">Endre</button>
                    <button class="btn btn-danger" onclick="deleteCar(${car.id});">Slett</button>
                </td>
            </tr>
        `
    }
    return formatted_cars;
}

function deleteCar(id) {
    $.get(`/cars/${id}`, car => {
        if(confirm("Er du sikker på at du vil slette bil: " + car.plate_number)) {
            $.post(`cars/${id}/delete`, () => getCars());
        }
    })
}

function redirectToEditCar(id) {
    window.location.href="/editcar.html?carId=" + id;
}

function deleteAllCars() {
    $.post("/cars/delete", () => getCars());
}

// create car object
function createCarObject () {
    return {
        pnr: $('#pnr').val(),
        name: $('#name').val(),
        address: $('#address').val(),
        plate_number: $('#plate_number').val(),
        brand: $('#brand').val(),
        type: $('#type').val(),
    }
}