$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
 	{
 		$("#alertSuccess").hide();
 	}
 	$("#alertError").hide();
});

// SAVE 
$(document).on("click", "#btnSave", function(event)
{
	// Clear status msges
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation
	var status = validateItemForm();
	
	// If not valid
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	var type = ($("#btnSave").val() == "") ? "POST" : "PUT";
	
	// If valid
	// Generate the card and append
	
	var student = getStudentCard(
		$("#txtName").val().trim(),
		$("#txtContactNo").val().trim(),
		$("#txtEmail").val().trim(),
		$("#Message").val().trim()
	);
	
	$("#colfeedback").append(student);
	$("#alertSuccess").text("Saved successfully.");
	$("#alertSuccess").show();
	$("#formFeedback")[0].reset();
});

function validateFeedbackForm()
{
	//Validations
	//NAME
	if ($("#txtName").val().trim() == "")
	{
		return "Insert name.";
	}
	
	//CONTACT NO
	if ($("#txtContactNo").val().trim() == "")
	{
		return "Insert contact number.";
	}
	
	//EMAIL
	function isEmail(email) {
  	var regex =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; 
    	return regex.test(email);
	}
	
	if ($("#txtEmail").val().trim() == "")
	{
		return "Insert Email.";
	}
	
	else if(!isEmail($("#txtEmail").val())){
  		return "Insert valid Email";
}

	//MESSAGE
	if ($("#txtMessage").val().trim() == "")
	{
		return "Insert feedback.";
	}
		
	return true;
}


function getStudentCard(name, contactno, email,message)
{
	//var title = (gender == "Male") ? "Mr." : "Ms.";
	//var yearNumber = "";
	//switch (year)
	//{
		//case "1":
		//yearNumber = "1st";
		//break;
		
		//case "2":
		//yearNumber = "2nd";
		//break;
		
		//case "3":
		//yearNumber = "3rd";
		//break;
		
		//case "4":
		//yearNumber = "4th";
		//break;
	//}

	var student = "";
		student += "<div class=\"student card bg-light m-2\" style=\"max-width: 10rem; float: left;\">";
		student += "<div class=\"card-body\">";
		student += name + " ";
		student += "<br>";
		student += contactno + " " ;
		student += "<br>";
		student += email + " " ;
		student += "<br>";
		student += email + " " ;
		student += "<br>";
		student += message + " year";
		student += "</div>";
		student += "<input type=\"button\" value=\"Remove\" class=\"btn btn-danger remove\">";
		student += "</div>";
	return student;
}

