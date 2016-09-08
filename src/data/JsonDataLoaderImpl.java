package data;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class JsonDataLoaderImpl implements DataLoader {

	private static String USERS_FILEPATH = "/Resources/Data/users.json";
	private static String TICKETS_FILEPATH = "/Resources/Data/tickets.json";
	private static String ORGANISATIONS_FILEPATH = "/Resources/Data/organizations.json";
	
	private static final Map<Category, String> CATEGORY_FILEPATHS;
	static {
	    Map<Category, String> map = new HashMap<Category, String>();
	    map.put(Category.USERS, USERS_FILEPATH);
	    map.put(Category.TICKETS, TICKETS_FILEPATH);
	    map.put(Category.ORGANISATIONS, ORGANISATIONS_FILEPATH);
	    CATEGORY_FILEPATHS = Collections.unmodifiableMap(map);
	}
	
	private JsonFileReader jsonReader;
	
	public JsonDataLoaderImpl(JsonFileReader jsonReader) {
		this.jsonReader = jsonReader;
	}

	/**
	 * Loads the data from the resources into the given data and fields objects.
	 * Populates the data parameter with a key of the category enum and a JsonArray of the 
	 * json objects loaded from the data resources.
	 * Populates the fields parameter with a key of category enum and a hashset of the fields
	 * which were found within that category.
	 * 
	 * @param data a Map<Category, JsonArray> to be populated with key value pairs of category enum and data entities found in that category 
	 * @param fields a Map<Category, HashSet<String>> to be populated with key value pairs of category enum and corresponding HashSet of field names found in that category
	 */
	public void load(Map<Category, JsonArray> data, Map<Category, HashSet<String>> fields) {
		if(data == null || fields == null) {
			throw new RuntimeException("Parameters not initialised before load attempt.");
		}
		
		for(Category key : CATEGORY_FILEPATHS.keySet()) {
			data.put(key, jsonReader.read(CATEGORY_FILEPATHS.get(key)));
			HashSet<String>currentFields = getFieldsFromJsonArray(data.get(key));
			fields.put(key, currentFields);
		}
	}
	
	/**
	 * Gets the fields (keys) from all the JsonObjects in a JsonArray and puts them in a HashSet
	 * of strings
	 * 
	 * @param jsonArray a JsonArray from which all the keys need to be retrieved as strings
	 * @return a HashSet<String> of all the keys as strings found within the JsonObjects of the JsonArray
	 */
	private HashSet<String> getFieldsFromJsonArray(JsonArray jsonArray) {
		HashSet<String>currentFields = new HashSet<String>();
		
		for (Object jsonObject : jsonArray) {
			Set<Map.Entry<String, JsonElement>> entries = ((JsonObject)jsonObject).entrySet();
			
			for (Map.Entry<String, JsonElement> entry: entries) {
				currentFields.add(entry.getKey());
			}
		}
		
		return currentFields;
	}
	
}
