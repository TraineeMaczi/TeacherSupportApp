$(document).ready(
    		function() {

    			// SUBMIT FORM
    			$("#basicInfoForm").submit(function(event) {
    				// Prevent the form from submitting via the browser.
    				event.preventDefault();
    				ajaxPost();
    			});

    			function ajaxPost() {

    				// PREPARE FORM DATA
    				var formData = {
    					 degree: $('#degree').val(),
                                        workplace: $('#workplace').val(),
                                        profession: $('#profession').val(),
                                        usos: $('#usos').val(),
                                        twitter: $('#twitter').val(),
                                        facebook: $('#facebook').val()
    				}

    				// DO POST
    				$.ajax({
    					type : "POST",
    					contentType : "application/json",
    					url :  "/teacherSupportAboutMe/BasicInfo/new",
    					data : JSON.stringify(formData),
    					dataType : 'json',
    					success : function(result) {
    						if (result.status == "success") {
    							$("#postResultDivBasicInfo").html(
    									"Success");

    						} else {
    							$("#postResultDivBasicInfo").html("<strong>Error</strong>");
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