function login(event) {
    event.preventDefault();

    const user = getUserObj();

    $.post("/users/login", user, () => {
        console.log("success");
        // login success
        window.location.href="/";
    })
        .fail(() => {
            const form = $("#loginForm")[0];

            $("#login-fail").html("Incorrect username or password");

            form.reset();
        })
}

function createUser(event) {
    event.preventDefault();

    const user = getUserObj();

    if (!validateUser(user)) { return; }

    $.post("/users/new", user, () => {
        window.location.href="/";
    })
}

// fetch user from DOM
function getUserObj() { return {username: $("#username").val(), password: $("#password").val()}}