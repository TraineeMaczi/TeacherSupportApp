$(document).ready(
    function () {
        $("#deleteNewsButton").on('click', function (event) {

            var item = $('input[name=selectedNews]:checked', '#deleteNewsForm').val();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '/tshome/delete',
                data:item,
                dataType: 'json',
                success: function ()
                {
                                    location.reload();
                                }

            });

        });
    })
