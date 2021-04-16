// form validation
function validateForm(car) {

    pnrOk = checkValidPnr(car.pnr);
    nameOk = checkValidName(car.name);
    addressOk = checkValidAddress(car.address);
    plateNumberOk = checkValidPlateNumber(car.plate_number);
    brandOk = checkValidBrand(car.brand);
    typeOk = checkValidType(car.type);

    return pnrOk && nameOk && addressOk && plateNumberOk && brandOk && typeOk;
}

function checkValidPnr(pnr) {
    const regexpPnr = /^[0-9]{11}$/;
    const pnrOk = regexpPnr.test(pnr);
    if (!pnrOk) {
        $("#invalid-pnr").html("Personnr 11 siffer");
    } else {
        $("#invalid-pnr").html("");
    }
    return pnrOk;
}

function checkValidName(name) {
    const regexpName = /^[a-zA-ZæøåÆØÅ. \-]{2,30}$/;
    const nameOk = regexpName.test(name);
    if (!nameOk) {
        $("#invalid-name").html("Navn, 2-30 bokstaver");
    } else {
        $("#invalid-name").html("");
    }
    return nameOk;
}

function checkValidAddress(address) {
    const regexpAddress = /^[0-9a-zA-ZæøåÆØÅ. \-]{2,50}$/;
    const addressOk = regexpAddress.test(address);
    if (!addressOk) {
        $("#invalid-address").html("Adresse 2-50 bokstaver/tall/symboler");
    } else {
        $("#invalid-address").html("");
    }
    return addressOk;
}

function checkValidPlateNumber(plateNumber) {
    const regexpPlateNumber = /^[A-Z]{2}[0-9]{5}$/;
    const plateNumberOk = regexpPlateNumber.test(plateNumber);
    if (!plateNumberOk) {
        $("#invalid-plateNumber").html("kjennetegn, 2 bokstaver + 5 tall");
    } else {
        $("#invalid-plateNumber").html("");
    }
    return plateNumberOk;
}

function checkValidBrand(brand) {
    const regexpBrand = /^[0-9a-zA-ZæøåÆØÅ. \-]{2,30}$/;
    const brandOk = regexpBrand.test(brand);
    if (!brandOk) {
        $("#invalid-brand").html("Merke, 2-30 bokstaver/tall/symboler");
    } else {
        $("#invalid-brand").html("");
    }
    return brandOk;
}

function checkValidType(type) {
    const regexpType = /^[a-zA-ZæøåÆØÅ. \-]{2,30}$/;
    const typeOk = regexpType.test(type);
    if (!typeOk) {
        $("#invalid-type").html("Type, 2-30 bokstaver eller symboler");
    } else {
        $("#invalid-type").html("");
    }
    return typeOk;
}