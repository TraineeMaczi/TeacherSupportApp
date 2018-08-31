$(document).ready(
    function () {

        $("#btnChangeName").on('click', function (event) {
            event.preventDefault();
            changeName();
        });
        $("#btnChangeMail").on('click', function (event) {
            event.preventDefault();
            changeEmail();
        });
        $("#btnChangePassword").on('click', function (event) {
            event.preventDefault();
            changePassword();
        });

    })
function changeName() {
    var name = $('#nameId').val();
    var surname = $('#surnameId').val();
    $.ajax({
        type: "POST",
        url: '/change/name/'+name+'/'+surname,
        success: function (result) {
            if (result == "success") {
                location.reload();
                $("#postResultChangeName").html(
                    result);


            } else {
                $("#postResultChangeName").html(result);
            }
            console.log(result);
        },
        error: function (e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
});
}
function changeEmail() {
    var email = $('#emailId').val();
    var confirmEmail = $('#emailConfirmId').val();
    $.ajax({
        type: "POST",
        url: '/change/email',
        data: {
            "email": email,
            "confirmEmail": confirmEmail
        },

        success: function (result) {
            if (result == "success") {
                location.reload();
                $("#postResultDivBasicInfo").html(
                    result);


            } else {
                $("#postResultDivBasicInfo").html(result);
            }
            console.log(result);
        },
        error: function (e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });
}
function changePassword() {
    var password = $('#password').val();
    var confirmPassword = $('#confirmPassword').val();
    $.ajax({
        type: "POST",
        url: '/change/password',
        data: {
            "password": password,
            "confirmPassword": confirmPassword
        },

        success: function (result) {
            if (result == "success") {
                location.reload();

                $("#postResultChangePassword").html(
                    result);


            } else {
                location.reload();
                $("#postResultChangePassword").html(result);
            }
            console.log(result);
        },
        error: function (e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });
}