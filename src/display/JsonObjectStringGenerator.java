package display;

import com.google.gson.JsonObject;

public interface JsonObjectStringGenerator {

	/**
	 * Creates a string from a JsonObject in a human readable format
	 * 
	 * @param jsonObject a jsonObject for which a string needs to be generated, displaying its contents
	 * @return a string containing the contents of the given JsonObject
	 */
	public String write(JsonObject jsonObject);
	
}
