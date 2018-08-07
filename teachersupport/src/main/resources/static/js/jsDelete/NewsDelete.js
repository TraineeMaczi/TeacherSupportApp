$(document).ready(
    function () {
        $("#deleteNewsButton").on('click', function () {
            var item = $('input[name=selectedNews]:checked', '#deleteNewsForm').val();
            event.preventDefault();
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
