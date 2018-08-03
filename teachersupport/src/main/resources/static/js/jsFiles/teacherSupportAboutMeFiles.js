
$(document).ready(
    function() {
        $("#btn1").on('click', function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            doAjax('fileUploadForm', 'listFiles', 'foto');
        });
        $("#btn2").on('click', function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            doAjax('fileUploadForm2', 'listFiles2', 'cv');
        });
    });

function doAjax(formName, listFiles, typ) {


    formName='#'+formName;
    listFiles='#'+listFiles;

    var form = $(formName)[0];
    var data = new FormData(form);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '/upload/'+typ,
        data: data,

        processData: false,
        contentType: false,
        cache: false,
        success: (data) => {
        $(listFiles).text(data);
},
    error: (e) => {
        $(listFiles).text(e.responseText);
    }
});
}