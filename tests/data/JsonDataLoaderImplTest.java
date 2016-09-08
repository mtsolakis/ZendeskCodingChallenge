package data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Test;
import org.mockito.Matchers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonDataLoaderImplTest {

	@Test
	public void testLoad() {
		
		JsonFileReader mockedJsonReader = mock(JsonFileReader.class);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("_id", "1");
		
		JsonArray jsonArray = new JsonArray();
		jsonArray.add(jsonObject);
		
		when(mockedJsonReader.read(Matchers.anyString())).thenReturn(jsonArray);
		
	    JsonDataLoaderImpl jsonDataLoader = new JsonDataLoaderImpl(mockedJsonReader);

	    Map<Category, JsonArray> data = new HashMap<Category, JsonArray>();
	    Map<Category, HashSet<String>> fields = new HashMap<Category, HashSet<String>>();
		
	    Map<Category, JsonArray> expectedData = new HashMap<Category, JsonArray>();
	    Map<Category, HashSet<String>> expectedFields = new HashMap<Category, HashSet<String>>();
	    
	    expectedData.put(Category.USERS, jsonArray);
	    expectedData.put(Category.TICKETS, jsonArray);
	    expectedData.put(Category.ORGANISATIONS, jsonArray);

	    HashSet<String> set = new HashSet<String>();
	    set.add("_id");
	    
	    expectedFields.put(Category.USERS, set);
	    expectedFields.put(Category.TICKETS, set);
	    expectedFields.put(Category.ORGANISATIONS, set);
	    
	    jsonDataLoader.load(data, fields);
	    
	    assertEquals(expectedData, data);
	    assertEquals(expectedFields, fields);
	}

}
