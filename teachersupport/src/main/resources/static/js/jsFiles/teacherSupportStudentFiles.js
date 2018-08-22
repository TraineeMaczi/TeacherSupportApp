$(document).ready(
    function () {
        $("#btn1").on('click', function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            doAjax('fileUploadForm', 'listFiles', 'resource');
        });

    });

function doAjax(formName, listFiles, typ) {

    formName = '#' + formName;
    listFiles = '#' + listFiles;

    var form = $(formName)[0];
    var data = new FormData(form);
    var id = document.getElementById('dispGroupForResource').value;
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '/upload/' + typ + id,
        data: data,

        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {
            $(listFiles).text(data);
            location.reload();
        },
        error: function (e) {
            $(listFiles).text(e.responseText);
        }
    });
}