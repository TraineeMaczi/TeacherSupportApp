$(document).ready(
    function () {
        $("#publicationDeleteButton").on('click', function (event) {

            var item = $('input[name=selectedPubli]:checked', '#publicationDeleteForm').val();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '/publications/delete',
                data: item,
                dataType: 'json',
                success: function () {
                    location.reload();
                }
            });

        });
    })
