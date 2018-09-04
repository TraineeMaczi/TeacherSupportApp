$(document).ready(
    function () {
        $("#deleteNewsButton").on('click', function (event) {

            var item = $('input[name=selectedNews]:checked', '#deleteNewsForm').val();

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '/tshome/delete',
                data: item,
                dataType: 'json',
                success: function () {
                    location.reload();
                }

            });

        });
        $("#editNewsButton").on('click', function () {
            var item = $('input[name=selectedNews]:checked', '#deleteNewsForm').val();
            document.getElementById('editNewsModalContentOld').value = item;
            document.getElementById('editNewsModalContentNew').value = item;
        });
    })
