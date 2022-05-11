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
	
	// If valid------------------------
	$("#formFeedback").submit();
	
	var type = ($("#btnSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
    {
	     url : "FeedbackAPI",
	     type : type,
	     data : $("#formFeedback").serialize(),
	     dataType : "text",
	     complete : function(response, status)
	     {
	     onUserSaveComplete(response.responseText, status);
	     }
    });

});

function onFeedbackSaveComplete(response, status)
{
	if (status == "success")
 	{
  		var resultSet = JSON.parse(response);

  		if (resultSet.status.trim() == "success")
  		{
	
    		$("#alertSuccess").text("Successfully saved.");
    		$("#alertSuccess").show();
    
    		$("#divFeedbackGrid").html(resultSet.data);
  		} 

		else if (resultSet.status.trim() == "error")
  		{
    		$("#alertError").text(resultSet.data);
    		$("#alertError").show();
  		}
   		} else if (status == "error")
   		{
     		$("#alertError").text("Error while saving.");
     		$("#alertError").show();
   		} else
   		{
     		$("#alertError").text("Unknown error while saving..");
     		$("#alertError").show();
   		}

    $("#hidFeedbackIDSave").val("");
    $("#formFeedback")[0].reset();
}


// UPDATE
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidFeedbackIDSave").val($(this).closest("tr").find('#hidFeedbackIDUpdate').val());
	$("#F_Name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#F_ContactNo").val($(this).closest("tr").find('td:eq(1)').text());
	$("#F_Email").val($(this).closest("tr").find('td:eq(2)').text());
	$("#F_Message").val($(this).closest("tr").find('td:eq(3)').text());
});

$(document).on("click", ".btnRemove", function(event)
{
   $.ajax(
   {
     url : "FeedbackAPI",
     type : "DELETE",
     data : "F_ID=" + $(this).data("itemid"),
     dataType : "text",
     complete : function(response, status)
     {
     	onUserDeleteComplete(response.responseText, status);
     }
    });
});

function onUserDeleteComplete(response, status)
{
	if (status == "success")
 	{
	
 		var resultSet = JSON.parse(response);
	
 		if (resultSet.status.trim() == "success")
 		{
 			$("#alertSuccess").text("Successfully deleted.");
 			$("#alertSuccess").show();
 			$("#divFeedbackGrid").html(resultSet.data);
		
 		} else if (resultSet.status.trim() == "error")
 		{
 			$("#alertError").text(resultSet.data);
 			$("#alertError").show();
 		}
 } else if (status == "error")
 	{
 		$("#alertError").text("Error while deleting.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while deleting..");
 		$("#alertError").show();
 	}
}
// VALIDATE FEEDBACK FORM
function validateFeedbackForm()
{
	//Validations
	//NAME
	if ($("#F_Name").val().trim() == "")
	{
		return "Insert name.";
	}
	
	//CONTACT NO
	if ($("#F_ContactNo").val().trim() == "")
	{
		return "Insert contact number.";
	}
	
	//EMAIL
	function isEmail(email) {
  	var regex =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; 
    	return regex.test(email);
	}
	
	if ($("#F_Email").val().trim() == "")
	{
		return "Insert Email.";
	}
	
	else if(!isEmail($("#F_Email").val())){
  		return "Insert valid Email";
	}

	//MESSAGE
	if ($("#F_Message").val().trim() == "")
	{
		return "Insert feedback.";
	}
		
	return true;
}

/*
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
}*/

