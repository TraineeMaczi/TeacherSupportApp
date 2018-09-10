$(document).ready(
    function () {
        $("#btn1").on('click', function (event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            doAjax('fileUploadForm', 'listFiles', 'resource');
        });
        $("#files").change(function () {
            filename = this.files[0].name
            console.log(filename);
        });
        $("#edGroupFormGroupName")
            .popover({
                title: 'Important !',
                content: "This is the first thing,the student will see,so choose a clear name."
            })
            .blur(function () {
                $(this).popover('hide');
            });

        $("#newGroupNameId")
            .popover({
                title: 'Important !',
                content: "This is the first thing,the student will see,so choose a clear name."
            })
            .blur(function () {
                $(this).popover('hide');
            });


        $("#groupUpdatePostButton").on('click', function (event) {
            event.preventDefault();
            ajaxPostUpdateGroup()
        });
        $("#addRemoteResourceButton").on('click', function (event) {
            event.preventDefault();
            ajaxPostAddRemoteResource();
        });


        $("#editStudGroupButton").on('click', function () {
            var item = $('input[name=groupsED]:checked', '#edDeleteGroupForm').val();
            //                    alert(item);
            //                       event.preventDefault();
            //                      ajaxEditStudGroupButton()
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/teacherSupportStudent/edit",
                data: item,
                dataType: 'json',
                success: function () {
                    location.reload();
                }

            });

        });

        $("#deleteGroupButton").on('click', function () {
            var item = $('input[name=groupsED]:checked', '#edDeleteGroupForm').val();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/teacherSupportStudent/delete",
                data: item,
                dataType: 'json',
                success: function () {
                    location.reload();
                }

            });
        });


        $("#deleteLocalResourceButton").on('click', function () {
            var item = $('input[name=localResourceChecked]:checked', '#localResourceForm').val();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/teacherSupportStudent/deleteLocalResource",
                data: item,
                dataType: 'json',
                success: function () {
                    location.reload();
                }

            });
        });
        $("#deleteRemoteResourceButton").on('click', function () {
            var item = $('input[name=remoteResourceChecked]:checked', '#remoteResourceForm').val();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/teacherSupportStudent/deleteRemoteResource",
                data: item,
                dataType: 'json',
                success: function () {
                    location.reload();
                }

            });
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
                $("#postResultDivGroupUpdate").html("Invalid Input !");
            }
            console.log(result);
        },
        error: function (e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });

};

function ajaxPostAddRemoteResource() {

    // PREPARE FORM DATA
    var formData = {

        name: $('#newRemoteResourceResourceName').val(),
        link: $('#newRemoteResourceResourceLink').val(),
    }
// DO POST
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/teacherSupportStudent/remoteResourceAdd",
        data: JSON.stringify(formData),
        dataType: 'json',
        success: function (result) {
            location.reload();
        },
        error: function (e) {
            alert("Fail add remote resource");
        }
    });

};