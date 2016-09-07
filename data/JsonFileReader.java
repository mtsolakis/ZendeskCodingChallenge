package data;

import com.google.gson.JsonArray;

public interface JsonFileReader {

	/**
	 * Reads a json file from the specified path into memory as a JsonArray of JsonObjects
	 * 
	 * @param filePath a path to a file from which the Json needs to be read
	 * @return a JsonArray of the JsonObjects retrieved from the file at the given path
	 */
	public JsonArray read(String filePath);
}
