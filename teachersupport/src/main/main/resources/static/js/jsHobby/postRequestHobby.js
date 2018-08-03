$(document).ready(
		function() {

			// SUBMIT FORM
			$("#newHobbyInfo").submit(function(event) {
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					hobbyField : $("#hobbyField").val(),
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "saveHobby", //! tu jestem
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$("#postResultDiv").html(
									"" + result.data.bookName
											+ "Post Successfully! <br>"
											+ "---> Congrats !!" + "</p>");
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});

			}

})