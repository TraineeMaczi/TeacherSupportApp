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
 $("#groupUpdatePostButton").on('click',function (event) {
            event.preventDefault();
        ajaxPostUpdateGroup()
        });


//        $("#editStudGroupButton").on('click',function (event) {
//                    event.preventDefault();
//                    ajaxEditStudGroupButton()
//                });



 $("#editStudGroupButton").on('click', function () {
var item=$('input[name=groupsED]:checked', '#edDeleteGroupForm').val();
//                    alert(item);
//                       event.preventDefault();
//                      ajaxEditStudGroupButton()
$.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "/teacherSupportStudent/edit",
                        data:  item,
                        dataType: 'json',
                        success: function()
                        {
                        document.getElementById('edGroupFormGroupName').value=item;
                        document.getElementById('dispGroupForResource').value=item;
                        }

                    });
                });

$("#deleteGroupButton").on('click', function () {
var item=$('input[name=groupsED]:checked', '#edDeleteGroupForm').val();
                    alert(item);
$.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "/teacherSupportStudent/delete",
                        data:  item,
                        dataType: 'json',
//                        success: function()
//                        {
//                           alert(item);
//                        }

                    });
                });




     })

//        function ajaxEditStudGroupButton(){
//
//                    $.ajax({
//                        type: "POST",
//                        contentType: "application/json",
//                        url: "/teacherSupportStudent/select",
//                        data:  {groupNameField:$('#studGroupFormCheck option:selected').val()},
//                        dataType: 'json',
//
//                    });
//                };


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
var formData = {

            placeField:$('#placeField').val(),
            officeField: $('#officeField').val(),
            dayField: $('#dayField').val(),
            timeFromFieldH: $('#timeFromFieldH').val(),
            timeToFieldH: $('#timeToFieldH').val(),
            timeFromFieldM:$('#timeFromFieldM').val(),
            timeToFieldM:$('#timeToFieldM').val(),
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
                            } else {
                                $("#postResultDivContact").html("<strong>Error</strong>");
                            }
                            console.log(result);
                        },
                        error: function (e) {
                            alert("Error!")
                            console.log("ERROR: ", e);
                        }
                    });

                };

function ajaxPostUpdateGroup() {

            // PREPARE FORM DATA
            var formData = {

            groupNameField: $('#edGroupFormGroupName').val(),
            facultyField: $('#facultyField').val(),
            groupNrFiled: $('#groupNrFiled').val(),
            classNameField: $('#classNameField').val(),
            classDayFiled: $('#classDayFiled').val(),
            timeFromFieldH: $('#timeFromFieldH').val(),
             timeToFieldH: $('#timeToFieldH').val(),
              timeFromFieldM: $('#timeFromFieldM').val(),
              timeToFieldM: $('#timeToFieldM').val()
            }

            // DO POST
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/teacherSupportStudent/updateGroup",
                data: JSON.stringify(formData),
                dataType: 'json',
                success: function (result) {
                    if (result.status == "success") {

                        $("#postResultDivGroupUpdate").html(
                            "Success");

                    } else {
                        $("#postResultDivGroupUpdate").html("<strong>Error</strong>");
                    }
                    console.log(result);
                },
                error: function (e) {
                    alert("Error!")
                    console.log("ERROR: ", e);
                }
            });

        };

//Zarombisty kod ktory bierze wszystkie zaznaczone
//                    var checkbox_value = "";
//                    $(":checkbox").each(function () {
//                        var ischecked = $(this).is(":checked");
//                        if (ischecked) {
//                            checkbox_value += $(this).val() ;
//                        }
//                    });