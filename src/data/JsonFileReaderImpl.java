package data;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonFileReaderImpl implements JsonFileReader {

	/**
	 * Reads a json file from the specified path into memory as a JsonArray of JsonObjects
	 * 
	 * @param filePath a path to a file from which the Json needs to be read
	 * @return a JsonArray of the JsonObjects retrieved from the file at the given path
	 * @throws FileNotFoundException 
	 */
	public JsonArray read(String filePath) throws RuntimeException {
        try {  
    		if(filePath == null) {
    			throw new FileNotFoundException();
    		}
        	
        	//Get the files full path on the machine
        	String fullPath = System.getProperty("user.dir") + filePath;
        	
        	//Read the file into a JsonArray object
        	JsonReader reader = new JsonReader(new FileReader(fullPath));
        	JsonParser parser = new JsonParser();
        	JsonArray jsonArray = (JsonArray) parser.parse(reader);

            return jsonArray;
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
        catch (JsonSyntaxException e) {
            e.printStackTrace();  
            throw new RuntimeException(e);
            
        }
	}

}
