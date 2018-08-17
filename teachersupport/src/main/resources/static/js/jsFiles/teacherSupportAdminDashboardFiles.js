
$(document).ready(
    function() {

        var photo=[];
        var id=[];
        $.ajax({
            type: "GET",
            url: '/index/giveMePhoto',
            success: function (result) {
                if (result.status == "success") {

                    photo=result.data;

                } else {
                    photo="img/logo.jpg" ;
                }
                console.log(result);

                $.ajax({
                    type: "GET",
                    url: '/index/giveMeId',
                    success: function (result) {
                        if (result.status == "success") {

                            id=result.data;

                        } else {
                            id=result.data;
                        }
                        console.log(result);
                        var i=0;
                        while(id[i])
                        {
                            document.getElementById(id[i]).src=photo[i];
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
                photo="img/logo.jpg";
                alert("Error!")
                console.log("ERROR: ", e);
            }

        });

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
    var facultyName= $('#facultyName').val()
    var form = $(formName)[0];
    var data = new FormData(form);
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: '/addFaculty/'+facultyName,
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
        success: (data) => {
        $('#listFiles2').text(data);
},
    error: (e) => {
        $('#listFiles2').text(e.responseText);
    }
});
}