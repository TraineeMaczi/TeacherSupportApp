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

        $("#uploadFacultyFoto").on('click', function (event) {
            event.preventDefault();
            doAjax('fileUploadForm', 'listFiles', 'facultyFoto');
        });
        $("#newUserFromFile").on('click', function (event) {
            event.preventDefault();
            doAjax2();
        });
        $("#buttonDelete").on('click', function (event) {
            event.preventDefault();
            deleteUser(event);
        });
        $("#fotoAdd").change(function () {
            filename = this.files[lo0].name
            console.log(filename);
        });

        $("#cvAdd").change(function () {
            filename = this.files[0].name
            console.log(filename);
        });

        $("#facultyFotoAdd").change(function () {
            filename = this.files[0].name
            console.log(filename);
        });
        $("#deleteFacultyButton").on('click', function () {
            var item = $('input[name=facultyDe]:checked', '#edDeleteFacultyForm').val();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/teacherSupportAdminDashboard/deleteFacultyAdminAction",
                data: item,
                dataType: 'json',
                success: function () {
                    location.reload();
                }

            });
        });
    }
);

function doAjax(formName, listFiles, typ) {


    formName = '#' + formName;
    listFiles = '#' + listFiles;
    var facultyName = $('#facultyName').val()
    var form = $(formName)[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '/addFaculty/' + facultyName,
        data: data,

        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
           location.reload();
        },
        error: function (e) {
            $('#resultOfAddingFaculty').text(e.responseText);
        }
    });
}

function doAjax2() {
    var form = $('#fileUpload')[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '/teacherSupportAdminDashboard/newUserAdminActionFromFile',
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
           location.reload();
        },
        error: function (e) {
            $('#listFiles2').text(e.responseText);
        }
    });
}
function deleteUser() {

    var userId = $('input[name=userToDelete]:checked', '#UsersForm').val();
    $.ajax({
        type: "POST",
        url: '/teacherSupportAdminDashboard/deleteUser',
        data: {
            "userId": userId
        },
        success: function () {

            location.reload();
        },
        error: function (e) {
            location.reload();
        }
    });
}