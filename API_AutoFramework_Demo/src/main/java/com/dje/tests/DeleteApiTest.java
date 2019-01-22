package com.dje.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dje.base.TestBase;
import com.dje.restclient.RestClient;

public class DeleteApiTest extends TestBase{
	TestBase testBase;
	RestClient restClient;
	String url;
	String host;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeClass
	public void setUp() {
		testBase=new TestBase();
		host=prop.getProperty("HOST");
		url=host+"/api/users/2";
	}
	@Test
	public void testDelete() throws ClientProtocolException, IOException {
		restClient=new RestClient();
		closeableHttpResponse=restClient.delete(url);
		//¶ÏÑÔ×´Ì¬Âë
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 204,"status code is not 204");
	}
}
