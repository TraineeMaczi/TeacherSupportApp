$(document).ready(
    function () {
        $("#publicationDeleteButton").on('click', function () {
            var item = $('input[name=selectPublication]:checked', '#publicationDeleteForm').val();
            event.preventDefault();
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
