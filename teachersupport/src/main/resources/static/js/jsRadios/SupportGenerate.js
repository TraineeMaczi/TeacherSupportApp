$(document).ready(
    function () {

        $("#generateButton").on('click', function () {
            var listOfPages = '';
            if ($("#Home").is(":checked"))
                listOfPages = listOfPages + ($("#Home").val() + ',');
            if ($("#AboutMe").is(":checked"))
                listOfPages = listOfPages + ($("#AboutMe").val() + ',');
            if ($("#Publications").is(":checked"))
                listOfPages = listOfPages + ($("#Publications").val() + ',');
            if ($("#Student").is(":checked"))
                listOfPages = listOfPages + ($("#Student").val() + ',');
            if ($("#Contact").is(":checked"))
                listOfPages = listOfPages + ($("#Contact").val() + ',');
            $.ajax({
                type: "POST",
                url: 'generate/listOfPages',
                data: {
                    "listOfPages": listOfPages
                },
                success: function () {

                    location.reload();
                },
                error: function (e) {
                    alert("ERRROERR");
                    location.reload();
                }
            });

        });
    })
