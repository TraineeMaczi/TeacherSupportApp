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
            $("#listFiles").html("Please Wait");
            $.ajax({
                type: "POST",
                url: 'generate/listOfPages',
                data: {
                    "listOfPages": listOfPages
                },
                success: function() {
                    $.ajax({
                        type: "GET",
                        dataType: "json",
                        url: "generate/getListOfPages",

                        success: function (data) {
                            $("#listFiles").html("");
                            $.each(data, function (index, fileUrl) {
                                var filename = fileUrl.split('\\').pop().split('/').pop();
                                $("#listFiles").append('<a href="' + fileUrl + '"class="btn text-white bg-changableColor">' + 'Download' + '</a>');
                            });
                        }
                        ,
                        error: function (err) {
                            $("#listFiles").html(err.responseText);
                        }
                    });
                }
            });

        });
    })
