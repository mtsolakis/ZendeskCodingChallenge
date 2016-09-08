package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.gson.JsonObject;

public class UtilsTest {

	@Test
	public void testSetToAlphabetisedList() {
		List<String> expected = new ArrayList<String>();
		expected.add("a");
		expected.add("b");
		expected.add("c");
		
		Set<String> set = new HashSet<String>();
		set.add("b");
		set.add("c");
		set.add("a");
		
		assertEquals(expected, Utils.setToAlphabetisedList(set));
		
	}
	
	@Test
	public void testSetToAlphabetisedList_2() {
		List<String> expected = new ArrayList<String>();
		
		Set<String> set = new HashSet<String>();
		
		assertEquals(expected, Utils.setToAlphabetisedList(set));
		
	}
	
	@Test
	public void testGetJsonElementAsString() {
		String expected = "1";
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("a", 1);
		
		assertEquals(expected, Utils.getJsonElementAsString(jsonObject.get("a")));
		
	}
	
	@Test
	public void testGetJsonElementAsString_2() {
		String expected = "hello";
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("a", "hello");
		
		assertEquals(expected, Utils.getJsonElementAsString(jsonObject.get("a")));
		
	}
	
	

}
