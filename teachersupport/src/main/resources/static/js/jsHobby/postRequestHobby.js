$(document).ready(
    		function() {

    			// SUBMIT FORM
    			$("#hobbyForm").submit(function(event) {
    				// Prevent the form from submitting via the browser.
    				event.preventDefault();
    				ajaxPost();
    			});

    			function ajaxPost() {

    				$.ajax({
    					type : "POST",
    					contentType : "application/json",
    					url :  "/teacherSupportAboutMe/hobby/new",
    					data : $('#hobbyContentID').val(),
    					dataType : 'json',
    					success : function(result) {
    						if (result.status == "success") {
    							$("#postResultDivHobby").html(
    									"Success");
    						} else {
    							$("#postResultDivHobby").html("<strong>Error</strong>");
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