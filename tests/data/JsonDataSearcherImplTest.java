package data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Matchers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonDataSearcherImplTest {

	@Test
	public void testGetCategories() {
		
		DataSource mockedDataSource = mock(DataSource.class);
		
		JsonDataSearcherImpl jsonDataSearcherImpl = new JsonDataSearcherImpl(mockedDataSource);	
		jsonDataSearcherImpl.getCategories();

		verify(mockedDataSource).getCategories();

	}
	
	@Test
	public void testIsValidCategory() {
		DataSource mockedDataSource = mock(DataSource.class);
		
		String[] categories = new String[] {"a", "b", "c"};
		
		when(mockedDataSource.getCategories()).thenReturn(categories);
		
		JsonDataSearcherImpl jsonDataSearcherImpl = new JsonDataSearcherImpl(mockedDataSource);
		
		assertTrue(jsonDataSearcherImpl.isValidCategory("a"));
		
		assertFalse(jsonDataSearcherImpl.isValidCategory("d"));
	}
	
	@Test
	public void testIsValidCategory_2() {
		DataSource mockedDataSource = mock(DataSource.class);
		
		JsonDataSearcherImpl jsonDataSearcherImpl = new JsonDataSearcherImpl(mockedDataSource);

		assertFalse(jsonDataSearcherImpl.isValidCategory(null));
	}
	
	@Test
	public void testIsValidField() {
DataSource mockedDataSource = mock(DataSource.class);
		
		HashSet<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		
		when(mockedDataSource.getFieldsInCategory(Matchers.any(Category.class))).thenReturn(set);
		
		JsonDataSearcherImpl jsonDataSearcherImpl = new JsonDataSearcherImpl(mockedDataSource);
		
		assertTrue(jsonDataSearcherImpl.isValidField("Users", "a"));
		assertFalse(jsonDataSearcherImpl.isValidField("Users", "c"));
	}
	
	@Test
	public void testIsValidField_2() {
		DataSource mockedDataSource = mock(DataSource.class);
		
		JsonDataSearcherImpl jsonDataSearcherImpl = new JsonDataSearcherImpl(mockedDataSource);

		assertFalse(jsonDataSearcherImpl.isValidField(null, null));
	}
	
	@Test
	public void testGetFieldsByCategory() {
		DataSource mockedDataSource = mock(DataSource.class);
		
		JsonDataSearcherImpl jsonDataSearcherImpl = new JsonDataSearcherImpl(mockedDataSource);
		
		Map<Category, HashSet<String>> mockedFields = new HashMap<Category, HashSet<String>>();
		HashSet<String> set = new HashSet<String>();
		set.add("b");
		set.add("a");
		
		mockedFields.put(Category.USERS, set);
		
		when(mockedDataSource.getFields()).thenReturn(mockedFields);
		
		
		List<String> expectedFields = new ArrayList<String>();
		expectedFields.add("a");
		expectedFields.add("b");
		
		Map<Category, List<String>> expected = new HashMap<Category, List<String>>();
		expected.put(Category.USERS, expectedFields);
		
		assertEquals(expected, jsonDataSearcherImpl.getFieldsByCategory());
		
	}

	@Test
	public void testSearch() {
		DataSource mockedDataSource = mock(DataSource.class);
		
		JsonDataSearcherImpl jsonDataSearcherImpl = new JsonDataSearcherImpl(mockedDataSource);
		
		List<JsonObject> expected = new ArrayList<JsonObject>();
		
		JsonObject mockedJsonObject = new JsonObject();
		mockedJsonObject.addProperty("_id", "1");
		
		expected.add(mockedJsonObject);
		
		JsonArray mockedJsonArray = new JsonArray();
		mockedJsonArray.add(mockedJsonObject);

		Map<Category, JsonArray> mockedData = new HashMap<Category, JsonArray>();
		mockedData.put(Category.ORGANISATIONS, mockedJsonArray);
		
		when(mockedDataSource.getData()).thenReturn(mockedData);
		
		HashSet<String> set = new HashSet<String>();
		set.add("_id");
		
		when(mockedDataSource.getFieldsInCategory(Category.ORGANISATIONS)).thenReturn(set);
		
		assertEquals(expected, jsonDataSearcherImpl.search(Category.ORGANISATIONS, "_id", "1"));

	}

}
