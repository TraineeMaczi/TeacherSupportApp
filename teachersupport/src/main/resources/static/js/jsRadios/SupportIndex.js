
$(document).ready(

    function () {

        document.getElementById('9').src="img/logo.jpg";
        $("#confirmFacultyButton").on('click', function () {
            var item = $('input[name=selectedFaculty]:checked', '#selectFaculty').val();
            event.preventDefault();

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
