$(document).ready(
    function () {
        $.ajax({
            type: "GET",
            url: '/givePhoto',
            success: function (result) {
                if (result.status == "success") {

                    document.getElementById("personFoto").src = result.data;

                } else {
                    document.getElementById("personFoto").src = result.data;
                }
            },
            error: function (e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }
        });
        $("#foAdd").change(function () {
            filename = this.files[0].name
            console.log(filename);
        });
        $("#cvAdd").change(function () {
            filename = this.files[0].name
            console.log(filename);
        });
        $("#addFotoId")
            .popover({
                title: 'Important !',
                content: "The size of the photo should be 375 X 300, otherwise the quality of the picture will get worse."
            })
            .blur(function () {
                $(this).popover('hide');
            });
        $("#btn1").on('click', function (event) {
            event.preventDefault();
            doAjax2('fileUploadForm', 'listFiles', 'foto');
        });
        $("#btn2").on('click', function (event) {
            event.preventDefault();
            doAjax('fileUploadForm2', 'listFiles2', 'cv');
        });
        $("#hobbyButton").on('click', function (event) {
            event.preventDefault();
            ajaxPostHobby()
        });
        $("#basicInfoButton").on('click', function (event) {
            event.preventDefault();
            ajaxPostBasicInfo()
        });
        $("#facebook")
            .popover({title: 'Important !', content: "Provide your Facebook profile link"})
            .blur(function () {
                $(this).popover('hide');
            });
        $("#usos")
            .popover({title: 'Important !', content: "Provide your Usos profile link"})
            .blur(function () {
                $(this).popover('hide');
            });
        $("#twitter")
            .popover({title: 'Important !', content: "Provide your Twitter profile link"})
            .blur(function () {
                $(this).popover('hide');
            });
    });

function doAjax(formName, listFiles, typ) {
    formName = '#' + formName;
    listFiles = '#' + listFiles;
    var form = $(formName)[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '/uploadCV',
        data: data,

        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            $(listFiles).text(data);
        },
        error: function (e) {
            $(listFiles).text(e.responseText);
        }
    });
}

function doAjax2(formName, listFiles, typ) {
    formName = '#' + formName;
    listFiles = '#' + listFiles;
    var form = $(formName)[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '/uploadFoto',
        data: data,

        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            $.ajax({
                type: "GET",
                url: '/givePhoto',
                success: function (result) {
                    if (result.status == "success") {
                        document.getElementById("personFoto").src = result.data;
                    } else {
                        document.getElementById("personFoto").src = result.data;
                    }
                    console.log(result);
                },
                error: function (e) {
                    alert("Error!")
                    console.log("ERROR: ", e);
                }
            });
        },
        error: function (e) {
        }
    });
}

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

function ajaxPostBasicInfo() {
    var formData = {
        degree: $('#degree').val(),
        workplace: $('#workplace').val(),
        profession: $('#profession').val(),
        usos: $('#usos').val(),
        twitter: $('#twitter').val(),
        facebook: $('#facebook').val(),
        phone: $('#phone').val()
    }
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
        },
        error: function (e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });

};

