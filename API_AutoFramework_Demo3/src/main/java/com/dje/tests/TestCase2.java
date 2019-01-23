package com.dje.tests;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
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
		//ʹ�ù��캯����������û��������ʼ���ɵ�¼�������
		PostParameters loginParameters=new PostParameters(email,password);
		//����¼����������л���json����
		String userJsonString=JSON.toJSONString(loginParameters);
		//���͵�¼����
		closeableHttpResponse=restClient.post(host+loginUrl, userJsonString, postheader);
		//�ӷ��ؽ���л�ȡ״̬��
		int status=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(status, 200);
	}
	@Test(dataProvider="get")
	public void getApi(String url) throws ClientProtocolException, IOException {
		closeableHttpResponse=restClient.get(host+url);
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	@Test(dataProvider="delete")
	public void deleteApi(String url) throws ClientProtocolException, IOException {
		closeableHttpResponse=restClient.delete(host+url);
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	@AfterClass
	public void tearDown() {
		System.out.println("���Խ���");
	}
}
