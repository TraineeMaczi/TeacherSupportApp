$(document).ready(
    function () {
        $("#deleteNewsButton").on('click', function (event) {
            event.preventDefault();
            var item = $('input[name=selectedNews]:checked', '#deleteNewsForm').val();

            $.ajax({
                type: "POST",
                url: '/tshome/delete',
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
