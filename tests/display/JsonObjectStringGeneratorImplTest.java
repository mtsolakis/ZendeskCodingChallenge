package display;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonObject;

public class JsonObjectStringGeneratorImplTest {

	@Test
	public void testWriteNull() {
		JsonObjectStringGeneratorImpl jsonObjectStringGenerator = new JsonObjectStringGeneratorImpl();
	
		assertEquals("", jsonObjectStringGenerator.write(null));
	}
	
	@Test
	public void testWriteObject() {
		JsonObjectStringGeneratorImpl jsonObjectStringGenerator = new JsonObjectStringGeneratorImpl();
	
		JsonObject jsonObject = new JsonObject();
		
		jsonObject.addProperty("a", 1);
		jsonObject.addProperty("a", "hello");
		jsonObject.addProperty("c", true);
		
		String expected = "a:                        hello" + System.getProperty("line.separator") + "c:                        true" + System.getProperty("line.separator");
		
		assertEquals(expected, jsonObjectStringGenerator.write(jsonObject));
	}

}
