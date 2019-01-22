package com.dje.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dje.base.TestBase;
import com.dje.data.Users;
import com.dje.restclient.RestClient;
import com.dje.util.TestUtil;

public class PostApiTest extends TestBase{
	TestBase testBase;
	String url;
	String host;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeClass
	public void setUp() {
		testBase =new TestBase();
		host=prop.getProperty("HOST");
		url=host+"/api/users";
	}
	@Test
	public void postApiTest() throws ClientProtocolException, IOException {
		restClient=new RestClient();
		//׼������ͷ��Ϣ
		HashMap<String, String> headermap=new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		//����ת����Json�ַ���
		Users users=new Users("yw","tester");
		String userJsonString=JSON.toJSONString(users);
		closeableHttpResponse=restClient.post(url, userJsonString, headermap);
		//��֤״̬���ǲ���200
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201,"status code is not 201");
		//������Ӧjson������name��job�ǲ����ڴ����
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity());
		JSONObject responseJson=JSON.parseObject(responseString);
		String name=TestUtil.getValueByJPath(responseJson, "name");
		String job=TestUtil.getValueByJPath(responseJson, "job");
		Assert.assertEquals(name, "yw","name is not the same");
		Assert.assertEquals(job, "tester","name is not the same");
	}
}
