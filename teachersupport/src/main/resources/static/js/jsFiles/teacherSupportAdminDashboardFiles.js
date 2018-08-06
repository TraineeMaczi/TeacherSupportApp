
$(document).ready(
    function() {
        $("#btn1").on('click',function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            doAjax('fileUploadForm', 'listFiles', 'facultyFoto');
        });
        $("#newUserFromFile").on('click',function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            doAjax2();
        });
    }
    );

function doAjax(formName, listFiles, typ) {

    event.preventDefault();
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
function doAjax2() {

    event.preventDefault();
    var form = $('#UsersForm')[1];
    var data = new FormData(form);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '/teacherSupportAdminDashboard/newUserAdminActionFromFile',
        data: data,


        processData: false,
        contentType: false,
        cache: false,
        success: (data) => {
        $('#listFiles2').text(data);
},
    error: (e) => {
        $('#listFiles2').text(e.responseText);
    }
});
}