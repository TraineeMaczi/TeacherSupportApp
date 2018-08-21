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
                console.log(result);
            },
            error: function (e) {
                alert("Error!")
                console.log("ERROR: ", e);
            }

        });
        $("#btn1").on('click', function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            doAjax2('fileUploadForm', 'listFiles', 'foto');
        });
        $("#btn2").on('click', function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            doAjax('fileUploadForm2', 'listFiles2', 'cv');
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
            $(listFiles).text(data);
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
            $(listFiles).text(e.responseText);
        }
    });
}