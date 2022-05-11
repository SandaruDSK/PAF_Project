<%@page import="model.Feedback" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Student details</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/bootstrap.min.js"></script>
		<script src="Components/main.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="col-8">
					<h1 class="m-3">Feedback details</h1>
					
					<form id="formFeedback" name="formItem" method="post" action="Feedback.jsp">
						<!-- NAME -->
						<div class="form-group">
   							<label for="Name" id="lblName">Name</label>
   							<input type="text" class="form-control" id="F_Name" name="F_Name" placeholder="">
 						</div>
						
						<!-- CONTACT NO -->
						<div class="form-group">
   							<label for="ContactNo" id="lblContactNo">Contact No</label>
   							<input type="tel" class="form-control" id="F_ContactNo" name="F_ContactNo" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}">
 						</div>
 							
						<!-- EMAIL -->
						<div class="form-group">
   							<label for="Email" id="lblEmail">Email</label>
   							<input type="email" class="form-control" id="F_Email" name="F_Email" placeholder="name@example.com">
 						</div>
						
						<!-- MESSAGE -->
						<div class="form-group">
   							<label for="Message" id="Message">Message</label>
   							<textarea class="form-control" id="F_Message" name="F_Message" rows="4"></textarea>
 						</div>
						
						<input id="btnSave" name="btnSave" type="button" value="Send Message" class="btn btn-outline-primary"> 
						<input type="hidden" id="hidFeedbackIDSave" name="hidFeedbackIDSave" value="">
					</form>
					
					<br>
					
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>
					
					<div id ="divFeedbackGrid">
						<%
							Feedback feedbackObj = new Feedback();
							out.print(feedbackObj.readFeedbacks());
						%>
					</div>
				</div>								
			</div>
		</div>
	</body>
</html>