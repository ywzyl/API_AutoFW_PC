package com.dje.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dje.base.TestBase;
import com.dje.restclient.RestClient;
import com.dje.util.TestUtil;

public class GetApiTest extends TestBase{
	TestBase testBase;
	String url;
	String host;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeClass
	public void setUp() {
		testBase = new TestBase();
        host = prop.getProperty("HOST");
        url = host+"/api/users";   
        //System.out.println(url);
	}	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient=new RestClient();
		closeableHttpResponse=restClient.get(url);
		//����״̬���ǲ���200
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"response status code is not 200");
		//����Ӧ���ݴ洢���ַ�������
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		//����Json���󣬰������ַ������л���Json���� 
		JSONObject responseJson=JSON.parseObject(responseString);
		//System.out.println("respon json from API-->" + responseJson); 
        
        //json���ݽ���
		String s=TestUtil.getValueByJPath(responseJson, "data[1]/first_name");
		System.out.println(s);
		Assert.assertEquals(s, "Janet","first name is not Janet");
	}
}
