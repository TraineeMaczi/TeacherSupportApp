$(document).ready(
    function () {

        $("#hobbyButton").on('click', function (event) {
            event.preventDefault();
            ajaxPostHobby()
        });

        $("#basicInfoButton").on('click',function (event) {
            event.preventDefault();
            ajaxPostBasicInfo()
        });

 $("#contactPostButton").on('click',function (event) {
            event.preventDefault();
            ajaxPostContact()
        });
    })

        function ajaxPostBasicInfo() {

            // PREPARE FORM DATA
            var formData = {
                degree: $('#degree').val(),
                workplace: $('#workplace').val(),
                profession: $('#profession').val(),
                usos: $('#usos').val(),
                twitter: $('#twitter').val(),
                facebook: $('#facebook').val(),
                phone: $('#phone').val()
            }

            // DO POST
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/teacherSupportAboutMe/BasicInfo/new",
                data: JSON.stringify(formData),
                dataType: 'json',
                success: function (result) {
                    if (result.status == "success") {

                        $("#postResultDivBasicInfo").html(
                            "Success");


                    } else {
                        $("#postResultDivBasicInfo").html("<strong>Error</strong>");
                    }
                    console.log(result);
                },
                error: function (e) {
                    alert("Error!")
                    console.log("ERROR: ", e);
                }
            });

        };

        function ajaxPostHobby() {

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/teacherSupportAboutMe/hobby/new",
                data: $('#hobbyContentID').val(),
                dataType: 'json',
                success: function (result) {
                    if (result.status == "success") {
                        $("#postResultDivHobby").html(
                            "Success");
                    } else {
                        $("#postResultDivHobby").html("<strong>Error</strong>");
                    }
                    console.log(result);
                },
                error: function (e) {
                    alert("Error!")
                    console.log("ERROR: ", e);
                }
            });

        };
         function ajaxPostContact() {


        private String placeField;
            private String officeField;
            private String dayField;
            private String timeFromFieldH;
            private String timeToFieldH;
            private String timeFromFieldM;
            private String timeToFieldM;
            private String timeField;





                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "/teacherSupportContact/contact/new",
                        data: $('#hobbyContentID').val(),
                        dataType: 'json',
                        success: function (result) {
                            if (result.status == "success") {
                                $("#postResultDivHobby").html(
                                    "Success");
                            } else {
                                $("#postResultDivHobby").html("<strong>Error</strong>");
                            }
                            console.log(result);
                        },
                        error: function (e) {
                            alert("Error!")
                            console.log("ERROR: ", e);
                        }
                    });

                };

