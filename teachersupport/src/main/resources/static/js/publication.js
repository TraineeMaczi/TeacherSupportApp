$(document).ready(
    function () {
        $("#publicationDeleteButton").on('click', function (event) {

            var item = $('input[name=selectedPubli]:checked', '#publicationDeleteForm').val();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '/publication/delete',
                data: item,
                dataType: 'json',
                success: function () {
                    location.reload();
                }
            });
        });
        $("#editPubliButton").on('click', function () {
            var item = $('input[name=selectedPubli]:checked', '#publicationDeleteForm').val();
            document.getElementById('editPubliModalContentOld').value = item;
            document.getElementById('editPubliModalContentNew').value = item;
        });
    })
