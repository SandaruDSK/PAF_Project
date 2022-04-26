package com;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Billing;
@Path("/Billing")
public class BillingService {
	
	Billing billingObj = new Billing();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBills()
	 {
	 return billingObj.readBills();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addBills(
			 @FormParam("B_Cus_ID") String B_Cus_ID,
			 @FormParam("B_Cus_Name") String B_Cus_Name,
			 @FormParam("B_Cus_Email") String B_Cus_Email,
			 @FormParam("B_Cus_Contact") String B_Cus_Contact,
			 @FormParam("B_Use_Points") String B_Use_Points,
			 @FormParam("B_Use_Val") String B_Use_Val,
			 @FormParam("B_Add_Charge") String B_Add_Charge,
			 @FormParam("B_Prev_Outsand") String B_Prev_Outsand,
			 @FormParam("B_Tot_Amt_Pay") String B_Tot_Amt_Pay,
			 @FormParam("B_Red_Nor") String B_Red_Nor)
	{
	 	String output = billingObj.addBills(B_Cus_ID, B_Cus_Name, B_Cus_Email,B_Cus_Contact,B_Use_Points,B_Use_Val,B_Add_Charge,B_Prev_Outsand,B_Tot_Amt_Pay,B_Red_Nor);
	 	return output;
	}

	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBills(String productsData) 
	{ 
			//Convert the input string to a JSON object 
			 JsonObject billingObject = new JsonParser().parse(productsData).getAsJsonObject(); 
			//Read the values from the JSON object
			 String B_ID = billingObject.get("B_ID").getAsString(); 
			 String B_Cus_ID =billingObject.get("B_Cus_ID").getAsString();
			 String B_Cus_Name =billingObject.get("B_Cus_Name").getAsString();
			 String B_Cus_Email = billingObject.get("B_Cus_Email").getAsString(); 
			 String B_Cus_Contact = billingObject.get("B_Cus_Contact").getAsString(); 
			 String B_Use_Points = billingObject.get("B_Use_Points").getAsString();
			 String B_Use_Val = billingObject.get("B_Use_Val").getAsString();
			 String B_Add_Charge = billingObject.get("B_Add_Charge").getAsString(); 
			 String B_Prev_Outsand = billingObject.get("B_Prev_Outsand").getAsString(); 
			 String B_Tot_Amt_Pay = billingObject.get("B_Tot_Amt_Pay").getAsString(); 
			 String B_Red_Nor = billingObject.get("B_Red_Nor").getAsString(); 
			 String output = billingObj.updateBills(B_ID, B_Cus_ID, B_Cus_Name, B_Cus_Email, B_Cus_Contact, B_Use_Points, B_Use_Val, B_Add_Charge, B_Prev_Outsand, B_Tot_Amt_Pay, B_Red_Nor); 
			return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteBills(String productsData) 
	{ 
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(productsData, "", Parser.xmlParser()); 

			//Read the value from the element <B_ID>
			 String B_ID = doc.select("B_ID").text(); 
			 String output = billingObj.deleteBills(B_ID); 
			return output;
	
	}
	
}
