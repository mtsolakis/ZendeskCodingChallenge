package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import controller.Utils;

public class JsonDataSearcherImpl implements DataSearcher {
	
	private DataSource dataSource;
	
	public JsonDataSearcherImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Returns a string array of the available categories
	 * 
	 * @return a string array of the available categories as strings
	 */
	public String[] getCategories() {
		return dataSource.getCategories();
	}
	
	/**
	 * Determines if the given category is valid to be searched upon
	 * 
	 * @param category a category as a string which needs to be validated as an available category for searching
	 * @return a boolean which represents whether or not the given category is valid for searching
	 */
	public boolean isValidCategory(String category) {
		if(category == null) {
			return false;
		}
		
		return Arrays.asList(dataSource.getCategories()).contains(category);
	}
	
	/**
	 * Determines if the given field is valid to be searched upon with the given category
	 * 
	 * @param category a category as a string in which the given field must exist in order to be considered valid for searching
	 * @param field a field as a string which needs to be validated as an available field for searching within the given category
	 * @return a boolean which represents whether or not the given field is valid for searching within the given category
	 */
	public boolean isValidField(String category, String field) {
		if(category == null || field == null) {
			return false;
		}
		
		return dataSource.getFieldsInCategory(Category.getEnum(category)).contains(field);
	}
	
	/**
	 * Creates a Map<Category, List<String>> where the key is the category and the value
	 * is a list of the fields within that category as strings in alphabetical order
	 * 
	 * @return a Map<Category, List<String>> where the key is the category and the value is a list of 
	 * the fields within that category as strings in alphabetical order
	 */
	public Map<Category, List<String>> getFieldsByCategory() {
		Map<Category, List<String>> fieldList = new HashMap<Category, List<String>>();
		
		Map<Category, HashSet<String>> fields = dataSource.getFields();
		
		for(Category key : fields.keySet()) {
			fieldList.put(key, Utils.setToAlphabetisedList(fields.get(key)));
		}

		return fieldList;
	}
	
	/**
	 * Finds and returns an ArrayList of JsonObjects which match the given category, field and value.
	 * Also adds any additional information to the JsonObjects from other data entities in other categories 
	 * which are associated with the matching JsonObjects in the given category.
	 * 
	 * @param category a category within which to search for matching data
	 * @param field a field within the given category whose value should be matched against
	 * @param value a value which should be used to compare against the value of the data entities to determine a match
	 * @return a List<JsonObject> containing the found JsonObjects which match the given parameters
	 */
	public List<JsonObject> search(Category category, String field, String value) {
		
		if(category == null || field == null || value == null || !isValidField(category.getValue(), field)) {
			return new ArrayList<JsonObject>();
		}
		
		List<JsonObject> matchedData = getMatchingData(dataSource.getData().get(category), field, value);
		
		if(category.equals(Category.USERS)) {
			addAssociatedUserData(matchedData);
		}
		else if(category.equals(Category.TICKETS)) {
			addAssociatedTicketData(matchedData);
		}
		
		return matchedData;
	}
	
	/**
	 * Gets all of the JsonObjects in the given JsonArray who have keys equal to the field parameter 
	 * and the values within those keys equal to the value parameter
	 * 
	 * @param data a JsonArray within which to look for matching JsonObjects
	 * @param field a string denoting which keys value to compare against within the JsonObject
	 * @param value a value which is used to compare against to determine a match
	 * @return an List<JsonObject> containing all the JsonObjects in the JsonArray which matched the given parameters
	 */
	private List<JsonObject> getMatchingData(JsonArray data, String field, String value) {
		List<JsonObject> matchedData = new ArrayList<JsonObject>();
		
		//For each JsonObject in the JsonArray
		for(Object obj : data) {
			JsonObject jsonObject = (JsonObject) obj;
			
			//If the JsonObject has a key equal to the field variable and value equal to the value variable
			if(hasMatchingValue(jsonObject, field, value)) {
				//Add the object to the list of found objects
				matchedData.add(jsonObject);
			}
		}
		
		return matchedData;
	}
	
	/**
	 * Determines if a JsonObject has a key equal to the field parameter and a value equal to the value parameter
	 * 
	 * @param JsonObject a JsonObject to check for a matching value in the given field (key)
	 * @param field a key as string whose value within the JsonObject will be used to compare against
	 * @param value a value which will be compared to the value in the JsonObjects key to determine a match
	 * @return a boolean representing whether or not the given JsonObject matched the given parameters
	 */
	private Boolean hasMatchingValue(JsonObject jsonObject, String field, String value) {
		
		//If the JsonObject key is equal to a field which contains an array, look for a value match within the array
		if(jsonObject.get(field) != null && jsonObject.get(field).getClass().equals(JsonArray.class)){
			return hasMatchInCollection((JsonArray)jsonObject.get(field), value);
		}
		//If a match is found, return true
		else if(getValue(jsonObject, field).equals(value)) {
			return true;
		}	
		//Otherwise return false
		return false;	
	}
	
	/**
	 * Looks for a match of the value parameter within a JsonArray 
	 * Will recursively search any JsonArrays found within the given JsonArray for a match
	 * 
	 * @param JsonArray a JsonArray to be searched within for a matching value
	 * @param value a value to search within the JsonArray for
	 * @return a boolean representing whether or not the value was found
	 */
	private Boolean hasMatchInCollection(JsonArray jsonArray, String value) {
		Boolean matchFound = false;
		
		//For each object in the JsonArray
		for(JsonElement element : jsonArray) {
			
			//If match is found, no need to continue searching
			if(matchFound) return true;
			
			//If object is another JsonArray, recursively search for matching value in nested arrays
			if(element.getClass().equals(JsonArray.class)) {
				matchFound = matchFound || hasMatchInCollection((JsonArray) element, value);
			}
			//Otherwise check for match
			else {		
				matchFound = matchFound || Utils.getJsonElementAsString(element).equals(value);
			}
		}
		
		return matchFound;
	}
		
	/**
	 * Adds the additional data required to display about a user which is in a search result
	 * This includes user's tickets and organisation name
	 * 
	 * @param matchedUsers a List<JsonObject> containing users found in a search which need 
	 * additional information added before they are displayed
	 */
	private void addAssociatedUserData(List<JsonObject> matchedUsers) {
		for(JsonObject matchedUser : matchedUsers) {
			//Adds data about user's tickets
			addTicketsToUser(matchedUser);
			
			//Adds data about user's organisation
			addOrganisationToJsonObject(matchedUser);
		}
	}
	
	//Adds the additional data required about a ticket object
	/**
	 * Adds the additional data required to display about a ticket which is in a search result
	 * This includes ticket's submitter's name and organisation name
	 * 
	 * @param matchedTickets a List<JsonObject> containing tickets found in a search which need 
	 * additional information added before they are displayed
	 */
	private void addAssociatedTicketData(List<JsonObject> matchedTickets) {
		for(JsonObject matchedTicket : matchedTickets) {
			//Adds data about ticket's user
			addUserToTicket(matchedTicket);
			
			//Adds data about ticket's organisation
			addOrganisationToJsonObject(matchedTicket);
		}
	}
		
	/**
	 * Adds relevant ticket information to a user's JsonObject
	 * 
	 * @param user a user whose tickets need to be retrieved and added before it is displayed
	 */
	private void addTicketsToUser(JsonObject user) {
		JsonArray tickets = dataSource.getDataInCategory(Category.TICKETS);
		String submitterId = getValue(user, "_id");
		
		List<JsonObject> usersTickets = getMatchingData(tickets, "submitter_id", submitterId);
		
		Integer i = 0;
		for(JsonObject ticket : usersTickets) {
			user.addProperty("ticket_" + Integer.toString(i), getValue(ticket, "subject"));
			i++;
		}
	}
	
	/**
	 * Adds relevant organisation information to a ticket or user's JsonObject
	 * 
	 * @param JsonObject a ticket or user whose organisation name needs to be retrieved and added before it is displayed
	 */
	private void addOrganisationToJsonObject(JsonObject jsonObject) {
		JsonArray organisations = dataSource.getDataInCategory(Category.ORGANISATIONS);
		String organisationId = getValue(jsonObject, "organization_id");
		
		List<JsonObject> objectsOrganisation = getMatchingData(organisations, "_id", organisationId);
		
		if(objectsOrganisation.size() > 0) {
			jsonObject.addProperty("organization_name", getValue(objectsOrganisation.get(0), "name"));
		}
	}
	
	/**
	 * Adds relevant user information to a ticket's JsonObject
	 * 
	 * @param ticket a ticket JsonObjectwhose user's name needs to be retrieved and added before it is displayed
	 */
	private void addUserToTicket(JsonObject ticket) {
		JsonArray users = dataSource.getDataInCategory(Category.USERS);
		String userId = getValue(ticket, "submitter_id");
		
		List<JsonObject> ticketsUser = getMatchingData(users, "_id", userId);
		
		if(ticketsUser.size() > 0) {
			ticket.addProperty("user_name", getValue(ticketsUser.get(0), "name"));
		}
	}
	

	/**
	 * Retrieves a value from a JsonObject for the given key. 
	 * Returns an empty string if the key is not present in the object.
	 * 
	 * @param JsonObject a JsonObject from which a value is needed from the given key
	 * @param key a key whose value needs to be retrieved from the given JsonObject
	 * @return the value of the given key in the given JsonObject or an empty string if the key is not present
	 */
	private String getValue(JsonObject jsonObject, String key) {
		 if(jsonObject.get(key) == null) {
			 return "";
		 }
		 		 
		 return Utils.getJsonElementAsString(jsonObject.get(key));

	}
}
