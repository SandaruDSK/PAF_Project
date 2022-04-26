package com;


import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Payment;

@Path("/Payments") 
public class PaymentService {
	
	Payment paymentObj = new Payment(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readPayments() 
	 { 
		 return paymentObj.readPayments();  
	 } 
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("name") String name,
	@FormParam("ctype") String ctype,
	@FormParam("cnumber") String cnumber,
	@FormParam("exmonth") String exmonth,
	@FormParam("exyear") String exyear,
	@FormParam("cvn") String cvn,
	@FormParam("tot") String tot)
	{
	String output = paymentObj.insertPayment(name, ctype, cnumber, exmonth,exyear,cvn,tot);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentsData)
	{
	//Convert the input string to a JSON object
	JsonObject paymentObject  = new JsonParser().parse(paymentsData).getAsJsonObject();
	//Read the values from the JSON object
	String id = paymentObject .get("id").getAsString();
	String name = paymentObject .get("name").getAsString();
	String ctype = paymentObject .get("ctype").getAsString();
	String cnumber = paymentObject .get("cnumber").getAsString();
	String exmonth = paymentObject .get("exmonth").getAsString();
	String exyear = paymentObject .get("exyear").getAsString();
	String cvn = paymentObject .get("cvn").getAsString();
	String tot = paymentObject .get("tot").getAsString();
	String output = paymentObj.updatePayment(id, name, ctype, cnumber, exmonth, exyear, cvn, tot);
	return output;
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePayment(String paymentsData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentsData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <id>
	 String id = doc.select("id").text(); 
	 String output = paymentObj.deletePayment(id); 
	return output; 
	}
	
	

}