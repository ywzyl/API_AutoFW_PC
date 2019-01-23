package com.dje.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.dje.base.TestBase;
import com.dje.parameters.PostParameters;
import com.dje.restclient.RestClient;
import com.dje.util.TestUtil;

import static com.dje.util.TestUtil.dtt;

public class EFPStagingCN extends TestBase{
	TestBase testBase;
	String host;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	String testCaseExcel;
	String tokenPath;
	HashMap<String, String> postheader=new HashMap<String, String>();
	HashMap<String, String> loginToken=new HashMap<String, String>();
	@BeforeClass
	public void setUp() {
		testBase=new TestBase();
		restClient=new RestClient();
		host=prop.getProperty("HOST");
		testCaseExcel=prop.getProperty("postdata");
		tokenPath=prop.getProperty("EFPV2tokenPath");
	}
	@DataProvider(name="postData")
	public Object[][] post() throws IOException{
		return dtt(testCaseExcel, 0);
	}
	@DataProvider(name="getData")
	public Object[][] get() throws IOException{
		return dtt(testCaseExcel, 1);
	}
	@DataProvider(name="deleteData")
	public Object[][] delete() throws IOException{
		return dtt(testCaseExcel, 2);
	}
	@Test(dataProvider="postData")
	public void login(String url,String email,String password) throws Exception {
		PostParameters loginParameters=new PostParameters(email,password);
		String urlJsonString=JSON.toJSONString(loginParameters);
		//发送登录请求
		closeableHttpResponse=restClient.post(host+url, urlJsonString, postheader);
		//获取登录后的token
		loginToken=TestUtil.getToken(closeableHttpResponse, tokenPath);
		System.out.println(loginToken);
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 200);		
	}
	@Test(dataProvider="getData",dependsOnMethods= {"login"})
	public void getMethod(String url) throws ClientProtocolException, IOException {
		 //将token赋值后发送get请求
		closeableHttpResponse=restClient.get(host+url, loginToken);
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	@Test(dataProvider="deleteData",dependsOnMethods="getMethod")
	public void deleteMethod(String url) throws ClientProtocolException, IOException {
		closeableHttpResponse=restClient.delete(host+url);
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 204);
	}
	
}
