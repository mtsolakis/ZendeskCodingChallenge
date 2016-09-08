package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonObject;

import data.Category;
import data.DataSearcher;
import display.LanguageHandler;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEngineImplTest {

	@Test
	public void testIsValidCategory() 
	{
		LanguageHandler mockedLanguageHandler = mock(LanguageHandler.class);
		DataSearcher mockedDataSearcher = mock(DataSearcher.class);
		 
	    SearchEngineImpl searchEngine = new SearchEngineImpl(mockedLanguageHandler, mockedDataSearcher);
	    
	    searchEngine.isValidCategory("category");
	    
		 verify(mockedDataSearcher).isValidCategory("category");
	}
	
	@Test
	public void testIsValidCategory_2() 
	{
		LanguageHandler mockedLanguageHandler = mock(LanguageHandler.class);
		DataSearcher mockedDataSearcher = mock(DataSearcher.class);
		 
	    SearchEngineImpl searchEngine = new SearchEngineImpl(mockedLanguageHandler, mockedDataSearcher);
	    
		assertFalse(searchEngine.isValidCategory(null));
	}

	@Test
	public void testIsValidField() 
	{
		LanguageHandler mockedLanguageHandler = mock(LanguageHandler.class);
		DataSearcher mockedDataSearcher = mock(DataSearcher.class);
		 
	    SearchEngineImpl searchEngine = new SearchEngineImpl(mockedLanguageHandler, mockedDataSearcher);
	    
	    searchEngine.isValidField("category", "field");
	    
		verify(mockedDataSearcher).isValidField("category", "field");
	}

	@Test
	public void testIsValidField_2() 
	{
		LanguageHandler mockedLanguageHandler = mock(LanguageHandler.class);
		DataSearcher mockedDataSearcher = mock(DataSearcher.class);
		 
	    SearchEngineImpl searchEngine = new SearchEngineImpl(mockedLanguageHandler, mockedDataSearcher);
	    
	    assertFalse(searchEngine.isValidField(null, null));
	}
	
	@Test
	public void testGetSearchableCategoriesString() 
	{

		LanguageHandler mockedLanguageHandler = mock(LanguageHandler.class);
		DataSearcher mockedDataSearcher = mock(DataSearcher.class);
		
		when(mockedDataSearcher.getCategories()).thenReturn(new String[] {"a", "b", "c"});
		
	    SearchEngineImpl searchEngine = new SearchEngineImpl(mockedLanguageHandler, mockedDataSearcher);
	    
	    assertEquals("a, b, c", searchEngine.getSearchableCategoriesString());
		
	}

	@Test
	public void testGetSearchableFieldsString() 
	{
		LanguageHandler mockedLanguageHandler = mock(LanguageHandler.class);
		DataSearcher mockedDataSearcher = mock(DataSearcher.class);
		
		Map<Category, List<String>> fieldsMap = new HashMap<Category, List<String>>();
		List<String> fields = new ArrayList<String>();
		fields.add("1");
		fields.add("2");	
		fieldsMap.put(Category.USERS,  fields);
		
		when(mockedDataSearcher.getFieldsByCategory()).thenReturn(fieldsMap);
		when(mockedLanguageHandler.getEndDataObjectString()).thenReturn("end");
		
		SearchEngineImpl searchEngine = new SearchEngineImpl(mockedLanguageHandler, mockedDataSearcher);
		
		String expectedResult = "Search Users by:" + System.getProperty("line.separator") + "1" + System.getProperty("line.separator") + "2" + System.getProperty("line.separator") + "end";
				
		assertEquals(expectedResult, searchEngine.getSearchableFieldsString());
	}

	@Test
	public void testPerformSearch() 
	{

		LanguageHandler mockedLanguageHandler = mock(LanguageHandler.class);
		DataSearcher mockedDataSearcher = mock(DataSearcher.class);
		
		List<JsonObject> results = new ArrayList<JsonObject>();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("_id", "1");
		jsonObject.addProperty("name", "user");
		
		results.add(jsonObject);
		
		when(mockedDataSearcher.search(Category.USERS, "_id", "1")).thenReturn(results);
		when(mockedDataSearcher.isValidCategory("Users")).thenReturn(true);
		when(mockedDataSearcher.isValidField("Users", "_id")).thenReturn(true);
		when(mockedLanguageHandler.getResultsFoundMsg()).thenReturn("start");
		when(mockedLanguageHandler.getEndDataObjectString()).thenReturn("end");
		
	    SearchEngineImpl searchEngine = new SearchEngineImpl(mockedLanguageHandler, mockedDataSearcher);
	    
	    String expectedResult = "start_id:                      1" + System.getProperty("line.separator") + "name:                     user" + System.getProperty("line.separator") + "end";
	    
	    assertEquals(expectedResult, searchEngine.performSearch("Users", "_id", "1"));
	}
	

}
