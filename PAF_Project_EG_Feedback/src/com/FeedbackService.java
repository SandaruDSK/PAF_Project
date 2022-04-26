package com;

import model.Feedback;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Feedbacks")
public class FeedbackService {
	Feedback feedbackObj = new Feedback();
	
	//Read function feedback message
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFeedbacks()
	{
		return feedbackObj.readFeedbacks();
	}
	
	//Insert function feedback message
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFeedback(@FormParam("F_Name") String F_Name,
								@FormParam("F_ContactNo") String F_ContactNo,
								@FormParam("F_Email") String F_Email,
								@FormParam("F_Message") String F_Message)
	
	{
		String output = feedbackObj.insertFeedback(F_Name, F_ContactNo, F_Email, F_Message);
	
		return output;
	}
	
	//Update function feedback message
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String feedbackData)
	{
		//Convert the input string to a JSON object
		JsonObject feedbackObject = new JsonParser().parse(feedbackData).getAsJsonObject();
		
		//Read the values from the JSON object
		String F_ID = feedbackObject.get("F_ID").getAsString();
		String F_Name = feedbackObject.get("F_Name").getAsString();
		String F_ContactNo = feedbackObject.get("F_ContactNo").getAsString();
		String F_Email = feedbackObject.get("F_Email").getAsString();
		String F_Message = feedbackObject.get("F_Message").getAsString();
		
		String output = feedbackObj.updateFeedback(F_ID, F_Name, F_ContactNo, F_Email, F_Message);
		
		return output;
	}
	
	//Delete function feedback message
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFeedback(String feedbackData)
	{
		
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser());
		
		//Read the value from the element <F_ID>
		String F_ID = doc.select("F_ID").text();
		String output = feedbackObj.deleteFeedback(F_ID);
		
		return output;
	}
}
