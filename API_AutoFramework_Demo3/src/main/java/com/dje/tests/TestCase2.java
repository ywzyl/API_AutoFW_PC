package com.dje.tests;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.alibaba.fastjson.JSON;
import com.dje.base.TestBase;
import com.dje.parameters.PostParameters;
import com.dje.restclient.RestClient;
import static com.dje.util.TestUtil.dtt;

public class TestCase2 extends TestBase{
	TestBase testBase;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	String host;
	String testCaseExcel;
	HashMap<String, String> postheader=new HashMap<String, String>();
	@BeforeClass
	public void setUp() {
		testBase=new TestBase();
		restClient=new RestClient();
		postheader.put("Content-Type", "application/json");
		host=prop.getProperty("HOST");
		testCaseExcel=prop.getProperty("postdata");
	}
	@DataProvider(name="postData")
	public Object[][] post() throws IOException{
		return dtt(testCaseExcel, 0);
	}
	@DataProvider(name="get")
	public Object[][] get() throws IOException{
		return dtt(testCaseExcel, 1);		
	}
	@DataProvider(name="delete")
	public Object[][] delete() throws IOException{
		return dtt(testCaseExcel, 2);
	}
	@Test(dataProvider="postData")
	public void login(String loginUrl,String email,String password) throws ClientProtocolException, IOException {
		 //使用构造函数将传入的用户名密码初始化成登录请求参数
		PostParameters loginParameters=new PostParameters(email,password);
		//将登录请求对象序列化成json对象
		String userJsonString=JSON.toJSONString(loginParameters);
		//发送登录请求
		closeableHttpResponse=restClient.post(host+loginUrl, userJsonString, postheader);
		//从返回结果中获取状态码
		int status=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(status, 200);
		Reporter.log("状态码"+status,true);
		Reporter.log("接口地址： "+loginUrl);
	}
	@Test(dataProvider="get")
	public void getApi(String url) throws ClientProtocolException, IOException {
		closeableHttpResponse=restClient.get(host+url);
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Reporter.log("状态码"+statusCode,true);
		Reporter.log("接口地址："+url);
	}
	@Test(dataProvider="delete")
	public void deleteApi(String url) throws ClientProtocolException, IOException {
		closeableHttpResponse=restClient.delete(host+url);
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 204);
		Reporter.log("状态码"+statusCode,true);
		Reporter.log("接口地址："+url);
	}
	@AfterClass
	public void tearDown() {
		System.out.println("测试结束");
	}
}
