package display;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import controller.Utils;

public class JsonObjectStringGeneratorImpl implements JsonObjectStringGenerator {
	
	private static Integer MIN_WHITESPACE_GAP_LENGTH = 25;
	
	
	/**
	 * Creates a string containing the contents of a JsonObject in a human readable format
	 * which can then be displayed to the user
	 * 
	 * @param jsonObject a jsonObject for which a string needs to be generated, displaying its contents
	 * @return a string containing the contents of the given JsonObject in a human readable format
	 */
	public String write(JsonObject jsonObject) {
		if(jsonObject == null) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		
		//Gets the keys in the json object
		Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
		HashSet<String> keys = new HashSet<String>();
		
		for (Map.Entry<String, JsonElement> entry: entries) {
			keys.add(entry.getKey());
		}
	
		
		//Sorts the keys into alphabetical order
		List<String> alphabetisedKeys = Utils.setToAlphabetisedList(keys);
		
		//Appends the string builder with the key value pairs of the json object
		for(String key : alphabetisedKeys) {
			String value = Utils.getJsonElementAsString(jsonObject.get(key));
			
			char[] whitespace = new char[Math.max(MIN_WHITESPACE_GAP_LENGTH - key.length(), 1)];
			Arrays.fill(whitespace, ' ');
			String whitespaceString = new String(whitespace);
			
			sb.append(key);
			sb.append(":");
			sb.append(whitespaceString);
			sb.append(value);
			sb.append(System.getProperty("line.separator"));
		}
		
		return sb.toString();
	}
	
}
