$(document).ready(
    function () {

        $("#btnChangeName").on('click', function (event) {
            event.preventDefault();
            doAjax();
        });
        $("#btnChangeMail").on('click', function (event) {
            event.preventDefault();
            doAjax2();
        });


    })
function doAjax() {
    event.preventDefault();
    var name = $('#nameId').val();
    var surname = $('#surnameId').val();
    $.ajax({
        type: "POST",
        url: '/change/name/'+name+'/'+surname
});
}
function doAjax2() {
    event.preventDefault();
    var emailId = $('#emailId').val();
    var emailConfirm = $('#emailConfirmId').val();
    $.ajax({
        type: "POST",
        url: '/change/name/'+emailId+'/'+emailConfirm
    });
}