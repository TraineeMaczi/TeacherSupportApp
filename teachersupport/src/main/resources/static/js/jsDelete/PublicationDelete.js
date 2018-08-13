$(document).ready(
    function () {
        $("#publicationDeleteButton").on('click', function (event) {
            event.preventDefault();
            var item = $('input[name=selectPublication]:checked', '#publicationDeleteForm').val();
            $.ajax({
                type: "POST",
                url: '/publications/delete',
                data: {
                    "id": item
                },
                success: function () {
                    location.reload();
                },
                error: function (e) {
                    location.reload();
                }
            });

        });
    })
