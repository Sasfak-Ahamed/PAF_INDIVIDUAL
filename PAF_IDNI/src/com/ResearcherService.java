package com;
import model.Researcher;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Researchers")

public class ResearcherService {
	Researcher resObj = new Researcher();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearchers()
	 {
	 return resObj.readResearchers();
	 }


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearcher(@FormParam("ProId") String ProId,
	 @FormParam("DeveloperName") String DeveloperName,
	 @FormParam("ProDesc") String ProDesc)
	{
	 String output = resObj.insertResearcher(ProId, DeveloperName, ProDesc);
	return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearcher(String researcherData)
	{
	//Convert the input string to a JSON object
	 JsonObject researcherObject = new JsonParser().parse(researcherData).getAsJsonObject();
	//Read the values from the JSON object
	 String ID = researcherObject.get("itemID").getAsString();
	 String ProId = researcherObject.get("itemCode").getAsString();
	 String DeveloperName = researcherObject.get("itemName").getAsString();
	 String ProDesc = researcherObject.get("itemPrice").getAsString();
	 String output = resObj.updateResearcher(ID, ProId, DeveloperName,ProDesc);
	return output;
	}


	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearcher(String researcherData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(researcherData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String ID = doc.select("ID").text();
	 String output = resObj.deleteResearcher(ID);
	return output;
	}

}
