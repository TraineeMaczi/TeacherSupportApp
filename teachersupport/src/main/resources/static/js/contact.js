$(document).ready(
    function () {


        $("#contactPostButton").on('click', function (event) {
            event.preventDefault();
            ajaxPostContact()
        });
        $("#deleteContactInfoButton").on('click', function () {
            var item = $('input[name=checkedContactInfo]:checked', '#dispContactForm').val();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/teacherSupportContact/deleteContactInfo",
                data: item,
                dataType: 'json',
                success: function () {
                    location.reload();
                }

            });
        });

    })

function ajaxPostContact() {
    var formData = {
        placeField: $('#placeField').val(),
        officeField: $('#officeField').val(),
        dayField: $('#dayField').val(),
        timeFromFieldH: $('#timeFromFieldH').val(),
        timeToFieldH: $('#timeToFieldH').val(),
        timeFromFieldM: $('#timeFromFieldM').val(),
        timeToFieldM: $('#timeToFieldM').val(),
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/teacherSupportContact/contact/new",
        data: JSON.stringify(formData),
        dataType: 'json',
        success: function (result) {
            if (result.status == "success") {
                $("#postResultDivContact").html(
                    "Success");
                // location.reload();
            } else {
                $("#postResultDivContact").html("Invalid Input !");
            }
            console.log(result);
        },
        error: function (e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });

};

