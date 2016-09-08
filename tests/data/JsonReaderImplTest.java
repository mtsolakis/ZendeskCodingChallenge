package data;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.JsonArray;

public class JsonReaderImplTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testReadValidFile() {
		JsonFileReaderImpl jsonFileReader = new JsonFileReaderImpl();
		
		JsonArray jsonArray = jsonFileReader.read("/Resources/Test Data/small_json_file.json");
		
		assertEquals(3, jsonArray.size());
		assertEquals(2, jsonArray.get(1).getAsJsonObject().get("_id"));
		

	}
	
	@Test
	public void testReadNoFile() {
		JsonFileReaderImpl jsonFileReader = new JsonFileReaderImpl();
		
		exception.expect(FileNotFoundException.class);
		jsonFileReader.read("/Resources/Test Data/non_existent_file.json");
	}

}
