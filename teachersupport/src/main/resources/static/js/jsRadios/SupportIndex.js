$(document).ready(
    function () {

        var photo = [];
        var id = [];
        $.ajax({
            type: "GET",
            url: '/index/giveMePhoto',
            success: function (result) {
                if (result.status == "success") {

                    photo = result.data;

                } else {
                    photo = "img/logo.jpg";
                }
                console.log(result);

                $.ajax({
                    type: "GET",
                    url: '/index/giveMeId',
                    success: function (result) {
                        if (result.status == "success") {

                            id = result.data;

                        } else {
                            id = result.data;
                        }
                        console.log(result);
                        var i = 0;
                        while (id[i]) {
                            document.getElementById(id[i]).src = photo[i];
                            i++;
                        }
                    },
                    error: function (e) {
                        alert("Error!")
                        console.log("ERROR: ", e);
                    }

                });

            },
            error: function (e) {
                photo = "img/logo.jpg";
                alert("Error!")
                console.log("ERROR: ", e);
            }

        });

        $("#confirmFacultyButton").on('click', function () {
            var item = $('input[name=selectedFaculty]:checked', '#selectFaculty').val();

            $.ajax({
                type: "POST",
                url: 'index/confirmFaculty',
                data: {
                    "facultyName": item
                },
                success: function () {

                    location.reload();
                },
                error: function (e) {
                    location.reload();
                }
            });

        });
    })
