package com.finance.rksamples.fincalmicroservice;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {

	String actualResponse = "{\r\n" + 
			"    \"timestamp\": \"2020-05-03\",\r\n" + 
			"    \"message\": \"id-1\",\r\n" + 
			"    \"details\": \"Test123\"\r\n" + 
			"}";
	
	
	@Test
	public void jsonAssert_StrictTrue_ExactMatchExceptForSpaces() throws JSONException {
		String expectedResponse = "{\r\n" + 
				"    \"timestamp\": \"2020-05-03\",\r\n" + 
				"    \"message\": \"id-1\",\r\n" + 
				"    \"details\": \"Test123\"\r\n" + 
				"}";
		
		
		//Exact structure only should match, it won't bother about spaces between the elements. 
		JSONAssert.assertEquals(expectedResponse,  actualResponse, true);
	}
	
	
	
	@Test
	public void jsonAssert_StrictFalse() throws JSONException {
		String expectedResponse = "{\r\n" + 
				"    \"timestamp\": \"2020-05-03\",\r\n" + 
				"    \"message\": \"id-1\",\r\n" + 
				"    \"details\": \"Test123\"\r\n" + 
				"}";
		
		JSONAssert.assertEquals(expectedResponse,  actualResponse, false);
	}
	
	

	@Test
	public void jsonAssert_WithoutEscapeCharacters() throws JSONException {
		String expectedResponse = "{timestamp:2020-05-03, message:id-1, details:Test123}";
		JSONAssert.assertEquals(expectedResponse,  actualResponse, false);
	}
	
	
	
}
